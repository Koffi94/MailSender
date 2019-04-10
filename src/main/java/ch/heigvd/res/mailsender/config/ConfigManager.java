package ch.heigvd.res.mailsender.config;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Set configuration with files
 * inspired by https://www.mkyong.com/java/java-properties-file-examples/
 */
public class ConfigManager {

    // Information from files
    private Properties properties;
    private String SmtpServerAddress;
    private int SmtpServerPort;
    private int nbGroups;
    private String sender;
    private ArrayList<String> receivers;
    private ArrayList<String> messages;

    // Files directory
    public static String CONFIG_DIRECTORY = "./src/main/java/ch/heigvd/res/mailsender/config/";

    /**
     * Set all variable with all information from files
     */
    public ConfigManager() {
        // Initialise variable
        properties = new Properties();
        sender = "";
        receivers = new ArrayList();
        messages = new ArrayList();

        try {
            parsePropertyFile(CONFIG_DIRECTORY + "config.properties");
            setMessages(CONFIG_DIRECTORY + "messages.utf8", messages);
            sender = setSender(CONFIG_DIRECTORY + "sender.utf8");
            parseReceiverFile(CONFIG_DIRECTORY + "receivers.utf8", receivers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method who will parse configuration file
     * @param urlFile Directory file
     * @throws IOException
     */
    private void parsePropertyFile(String urlFile) throws IOException {
        File file = new File(urlFile);
        BufferedReader input = new BufferedReader(new FileReader(file));

        // Load a properties file
        properties.load(input);

        // Set the property value
        SmtpServerAddress = properties.getProperty("SmtpServerAddress");
        SmtpServerPort = Integer.parseInt(properties.getProperty("SmtpServerPort"));
        nbGroups = Integer.parseInt(properties.getProperty("Groups"));
    }

    /**
     * Method who will set the sender
     * @param urlFile Directory file
     * @return Return the sender
     * @throws IOException
     */
    private String setSender(String urlFile) throws IOException{
        File file = new File(urlFile);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String value = br.readLine()  + "\r\n";

        return value;
    }

    /**
     * Method who will set the multiple message prank
     * @param urlFile Directory file
     * @param Messages All messages
     * @throws IOException
     */
    private void setMessages(String urlFile, ArrayList<String> Messages) throws IOException{
        if(!Messages.isEmpty()) {
            Messages.clear();
        }

        File file = new File(urlFile);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st, tmp = "";
        while ((st = br.readLine()) != null) {
            // set the next test if it's ---
            if(st.equals("---")){
                Messages.add(tmp);
                tmp = "";
            } else {
                tmp += st + "\r\n";
            }
        }
    }

    /**
     * Method who will parse receiver file
     * @param urlFile Directory file
     * @param receivers List with all receivers
     * @throws IOException
     * inspired by https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
     */
    private void parseReceiverFile(String urlFile, ArrayList<String> receivers) throws IOException {
        if(!receivers.isEmpty()) {
            receivers.clear();
        }

        File file = new File(urlFile);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            receivers.add(st);
        }
    }

    public String getSender() {
        return sender;
    }

    public ArrayList<String> getReceivers() {
        return receivers;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public String getSmtpServerAddress() {
        return SmtpServerAddress;
    }

    public int getSmtpServerPort() {
        return SmtpServerPort;
    }

    public int getNbGroups() {
        return nbGroups;
    }
}

