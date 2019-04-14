package ch.heigvd.res.mailsender.core;


/**
 * Define the Mail
 */
public class Mail {
    Person personFrom;
    Person personTo;
    String message;

    public Mail(Person personFrom, Person personTo, String message){
        this.personFrom = personFrom;
        this.personTo = personTo;
        this.message = message;
    }

    public Person from() {
        return personFrom;
    }

    public Person to() {
        return personTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
