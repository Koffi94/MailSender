package ch.heigvd.res.mailsender.core;

public class Person {
    private String email;

    public Person(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return email;
    }
}
