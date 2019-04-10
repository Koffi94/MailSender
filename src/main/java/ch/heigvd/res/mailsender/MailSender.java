package ch.heigvd.res.mailsender;

import ch.heigvd.res.mailsender.config.ConfigManager;
import ch.heigvd.res.mailsender.core.Group;
import ch.heigvd.res.mailsender.core.Mail;
import ch.heigvd.res.mailsender.core.Person;
import ch.heigvd.res.mailsender.core.Prank;
import ch.heigvd.res.mailsender.smtp.SmtpClient;

import java.util.ArrayList;
import java.util.List;

public class MailSender {
    public static void main(String[] args) {
        ConfigManager config = new ConfigManager();

        Person sender = new Person(config.getSender());

        List<Person> personReceiver = new ArrayList<Person>();
        List<Group>  groupReceiver = new ArrayList<Group>();
        int count = 0;

        for(int i = 0; i <= config.getReceivers().size(); ++i){
            if((i % config.getNbGroups() == 0 && count != 0 && count != config.getNbGroups() -1) || ){

            }
            personReceiver.add(new Person(config.getReceivers().get(i)));
            count++;
        }


        //Mail fakeMail = new Mail(sender, receiver, message);

        SmtpClient smtpService = new SmtpClient(config.getSmtpServerAddress(), config.getSmtpServerPort());

        smtpService.sendMail(fakeMail);

    }
}
