package ch.heigvd.res.mailsender;

import ch.heigvd.res.mailsender.config.ConfigManager;
import ch.heigvd.res.mailsender.core.Groups;
import ch.heigvd.res.mailsender.core.Mail;
import ch.heigvd.res.mailsender.core.Person;
import ch.heigvd.res.mailsender.smtp.SmtpClient;

import java.util.Random;
import java.util.Scanner;

public class MailSender {
    public static void main(String[] args) {
        Scanner inputLogin = new Scanner(System.in);
        Random number = new Random();
        ConfigManager config = new ConfigManager();
        Groups groupeReceiver = new Groups();
        SmtpClient smtpClient;

        if(config.isLogin()){
            System.out.print("Username: ");
            String login = inputLogin.nextLine();
            System.out.print("Password: ");
            String password = inputLogin.nextLine();
            smtpClient = new SmtpClient(config.getSmtpServerAddress(), config.getSmtpServerPort(), login, password);
        } else{
            smtpClient = new SmtpClient(config.getSmtpServerAddress(), config.getSmtpServerPort());
        }

        groupeReceiver.addGroupOfPeople(config.getReceivers(),config.getNbGroups());

        for(int i = 0; i < config.getNbGroups(); ++i){
            Mail newPrank = new Mail(new Person(config.getSender().get(number.nextInt(config.getSender().size()))),  groupeReceiver.getGroupOfPeople().get(i), config.getMessages().get(number.nextInt(config.getMessages().size())));
            smtpClient.sendMail(newPrank);
        }

    }
}
