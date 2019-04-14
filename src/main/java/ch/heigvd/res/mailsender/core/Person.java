package ch.heigvd.res.mailsender.core;

/**
 * Define the person
 */
public class Person {
    private String email;

    public Person(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return email;
    }
}
