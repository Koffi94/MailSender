# Laboratory - Usurpation Identity with SMTP

## Laboratory Description ##

In the laboratory, we create an application who will send prank emails to different groups of user email with a "fake email account".

For example, if you want to send to 5 people an email and told him that they won 1 million dollar with email "winner@loto.com" YOU CAN !

## Setting up a mock SMTP Server with Docker



## Instruction for configuration

We have 5 files who is important

- MailSender.jar ( or MailSender-1.0-SNAPSHOT-launcher.jar if you compile with maven): this is the application and you can run it from the terminal like this : 

  ```bash
  java -jar MailSender.jar
  ```

- config.properties :  we will have all configuration information

  - SmtpServerAddres: Define the address of SMTP server
  - SmtpServerPort : Define the port
  - Groups : Define the number of groups
  - Login : Define if we need a login

- sender.utg8 : We will have all emails that will send the fake emails

- receivers.utf8 : We will have all emails that will receive the fake emails

- messages.utf8 : We will have all the message that we want to send to people

  - All messages begin with Subject:

  - All messages are separated with 3 - like this 

     ![messages.utf8](figures/f1.png)

Finaly, when we are running the java application ALL this files must be in the same folder !!

# Description of implementation





