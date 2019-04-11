package ch.heigvd.res.mailsender.core;

/**
 * Define the Mail
 */
public class Mail {
    Person personFrom;
    Group groupsTo;
    String message;

    public Mail(Person person, Group group, String message){
        this.personFrom = person;;
        this.groupsTo = group;
        this.message = message;
    }

    public Person from() {
        return personFrom;
    }

    public Group to() {
        return groupsTo;
    }

    public String getMessage() {
        return message;
    }
}
