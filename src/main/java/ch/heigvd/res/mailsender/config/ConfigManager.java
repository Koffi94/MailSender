package ch.heigvd.res.mailsender.config;

import ch.heigvd.res.mailsender.core.Person;

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
    private ArrayList<Person>sender;
    private ArrayList<Person> receivers;
    private ArrayList<String> messagesPrank;
    private boolean login;

    // Files directory
    public static String CONFIG_DIRECTORY = "./application/";

    /**
     * Set all variable with all information from files
     */
    public ConfigManager() {
        // Initialise variable
        properties = new Properties();
        sender = new ArrayList();
        receivers = new ArrayList();
        messagesPrank = new ArrayList();

        try {
            parsePropertyFile(CONFIG_DIRECTORY + "config.properties");
            setMessages(CONFIG_DIRECTORY + "messages.utf8");
            parseUsers(CONFIG_DIRECTORY + "sender.utf8");
            parseUsers(CONFIG_DIRECTORY + "receivers.utf8");

            if(receivers.size() / nbGroups < 3 || receivers.size() == 0 || sender.size() == 0 || messagesPrank.size() == 0){
                throw new RuntimeException("You have a problem with your file(s) \n - You need sender(s)\n - You need minimum 3 people per group \n - You need message(s)");

            }
        } catch(IOException e){
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
        login = Boolean.parseBoolean(properties.getProperty("Login"));
    }

    /**
     * Method who will set the different persone
     * @param urlFile Directory file
     * @throws IOException
     */
    private void parseUsers(String urlFile) throws IOException{
        File file = new File(urlFile);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            if(urlFile.contains("sender")){
                sender.add(new Person(st));
            } else {
                receivers.add(new Person(st));
            }

        }
    }

    /**
     * Method who will set the multiple message prank
     * @param urlFile Directory file
     * @throws IOException
     */
    private void setMessages(String urlFile) throws IOException{
        File file = new File(urlFile);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st, tmp = "";
        while ((st = br.readLine()) != null) {
            // set the next test if it's ---
            if(st.equals("---")){
                messagesPrank.add(tmp);
                tmp = "";
            } else {
                tmp += st + "\r\n";
            }
        }
    }

    public ArrayList<Person> getSender() {
        return sender;
    }

    public ArrayList<Person> getReceivers() {
        return receivers;
    }

    public ArrayList<String> getMessages() {
        return messagesPrank;
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

    public boolean isLogin() {
        return login;
    }
}

