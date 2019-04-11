package ch.heigvd.res.mailsender;

import ch.heigvd.res.mailsender.config.ConfigManager;
import ch.heigvd.res.mailsender.core.Groups;
import ch.heigvd.res.mailsender.core.Mail;
import ch.heigvd.res.mailsender.core.Person;
import ch.heigvd.res.mailsender.smtp.SmtpClient;

import java.util.Random;

public class MailSender {
    public static void main(String[] args) {
        Random number = new Random();
        ConfigManager config = new ConfigManager();
        Groups groupeReceiver = new Groups();
        SmtpClient smtpClient = new SmtpClient(config.getSmtpServerAddress(), config.getSmtpServerPort());


        groupeReceiver.addGroupOfPeople(config.getReceivers(),config.getNbGroups());

        for(int i = 0; i < config.getNbGroups(); ++i){
            Mail newPrank = new Mail(new Person(config.getSender()),  groupeReceiver.getGroupOfPeople().get(i), config.getMessages().get(number.nextInt(config.getMessages().size())));
            smtpClient.sendMail(newPrank);
        }

    }
}
