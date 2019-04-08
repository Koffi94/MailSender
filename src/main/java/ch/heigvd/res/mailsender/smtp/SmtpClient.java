package ch.heigvd.res.mailpranker.smtp;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class SmtpClient {

    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private String serverAddress;
    private int port;

    public SmtpClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }



}
