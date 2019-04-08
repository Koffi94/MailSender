package ch.heigvd.res.mailsender.smtp;

import ch.heigvd.res.mailsender.core.Mail;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmtpClient {

    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());
    private Socket clientSocket;
    private PrintWriter writer;
    private BufferedReader reader;
    private String serverAddress;
    private int port;

    public SmtpClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }


    public void sendMail(Mail mail) {
        try {
            // Connexion
            clientSocket = new Socket(serverAddress, port);
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            LOG.info("Client successfully connected to the server");

            // Format the email
            String lineReader = "";
            lineReader = reader.readLine();
            if(!lineReader.startsWith("220")) {
                throw new IOException("SMTP server didn't greet us :" + lineReader);
            }
            LOG.info(lineReader);

            // EHLO
            writer.printf("EHLO SmtpClient\r\n");
            lineReader = reader.readLine();
            while(lineReader.startsWith("250")) {
                reader.readLine();
                LOG.info(lineReader);
            }


            // MAIL FROM
            writer.printf("MAIL FROM: " + mail.from() + "\r\n");
            lineReader = reader.readLine();
            if(!lineReader.startsWith("250")) {
                throw new IOException("Bad answer from server after MAIL FROM: " + lineReader);
            }
            LOG.info(lineReader);


            // RCPT TO
            for(String dest : mail.to()) {
                writer.printf("RCPT TO: " + dest + "\r\n");
                lineReader = reader.readLine();
                if(!lineReader.startsWith("250")) {
                    throw new IOException("Bad answer from server after RCPT TO: " + dest + lineReader);
                }
                LOG.info(lineReader);
            }

            // DATA
            writer.printf("DATA\r\n");
            lineReader = reader.readLine();
            if(!lineReader.startsWith("354")) {
                throw new IOException("Bad answer from server after DATA: " + lineReader);
            }
            LOG.info(lineReader);

            for(String line : mail.getPrankMessage()) {
                writer.println(line);
            }

            // End message and quit
            writer.printf(".\r\n");
            lineReader = reader.readLine();
            if(!lineReader.startsWith("250")) {
                throw new IOException("Bad answer from server after end message text: " + lineReader);
            }
            LOG.info(lineReader);

            writer.printf("QUIT\r\n");
            lineReader = reader.readLine();
            if(!lineReader.startsWith("221")) {
                throw new IOException("Bad answer from server after QUIT: " + lineReader);
            }
            LOG.info(lineReader);

        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally { // Close input and output streams
            try {
                reader.close();
            } catch (Exception ex) {
                Logger.getLogger(SmtpClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                writer.close();
            } catch (Exception ex) {
                Logger.getLogger(SmtpClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                clientSocket.close();
            } catch (Exception ex) {
                Logger.getLogger(SmtpClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
