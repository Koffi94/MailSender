package ch.heigvd.res.mailsender.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

// inspired by https://www.mkyong.com/java/java-properties-file-examples/

public class ConfigManager {

    private Properties properties;
    private String SmtpServerAddress;
    private int SmtpServerPort;
    private ArrayList<String> senders;
    private ArrayList<String> receivers;
    private ArrayList<String> messages;

    public ConfigManager() {
        properties = new Properties();
        senders = new ArrayList();
        receivers = new ArrayList();
        messages = new ArrayList();

        try {
            parsePropertyFile("config.properties");
            parseTextFile("messages.utf8",messages);
            parseTextFile("senders.utf8", senders);
            parseTextFile("receivers.utf8", receivers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parsePropertyFile(String urlFile) throws IOException {
        FileInputStream input = new FileInputStream(urlFile);

        // load a properties file
        properties.load(input);

        // get the property value
        SmtpServerAddress = properties.getProperty("SmtpServerAddress");
        SmtpServerPort = Integer.parseInt(properties.getProperty("SmtpServerPort"));
    }

    // inspired by https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
    private void parseTextFile(String urlFile, ArrayList<String> list) throws IOException {
        if(!list.isEmpty()) {
            list.clear();
        }

        File file = new File(urlFile);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            list.add(st);
        }
    }
}
