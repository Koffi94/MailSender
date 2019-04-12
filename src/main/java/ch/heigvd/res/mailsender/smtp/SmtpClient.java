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
    private String login, password;

    public SmtpClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
        this.login = "";
        this.password = "";
    }

    public SmtpClient(String serverAddress, int port, String login, String password) {
        this.serverAddress = serverAddress;
        this.port = port;
        this.login = login;
        this.password = password;
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
            while(!lineReader.equals("250 Ok") && !lineReader.equals("250 STARTTLS")) {
                lineReader = reader.readLine();
                LOG.info(lineReader);
            }

            // AUTH if needed
            if(!login.equals("") && !password.equals("")){
                writer.printf("AUTH LOGIN\r\n");
                lineReader = reader.readLine();
                if(!lineReader.startsWith("334")) {
                    throw new IOException("AUTH LOGIN error :" + lineReader);
                }
                LOG.info(lineReader);

                writer.printf(login + "\r\n");
                lineReader = reader.readLine();
                if(!lineReader.startsWith("334")) {
                    throw new IOException("LOGIN error :" + lineReader);
                }
                LOG.info(lineReader);

                writer.printf(password + "\r\n");
                lineReader = reader.readLine();
                if(!lineReader.startsWith("235")) {
                    throw new IOException("PASSWORD error :" + lineReader);
                }
                LOG.info(lineReader);
            }

            // MAIL FROM
            writer.printf("MAIL FROM: <" + mail.from() + ">\r\n");
            lineReader = reader.readLine();
            if(!lineReader.startsWith("250")) {
                throw new IOException("Bad answer from server after MAIL FROM: " + lineReader);
            }
            LOG.info(lineReader);


            // RCPT TO
                writer.printf("RCPT TO: <" + mail.to() + ">\r\n");
                lineReader = reader.readLine();
                LOG.info(lineReader);

                if(!lineReader.startsWith("250")) {
                    throw new IOException("Bad answer from server after RCPT TO: " + lineReader);
                }


            // DATA
            writer.printf("DATA\r\n");
            lineReader = reader.readLine();
             if(!lineReader.startsWith("354")) {
                throw new IOException("Bad answer from server after DATA: " + lineReader);
            }
            LOG.info(lineReader);

            writer.printf(mail.getMessage());


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
