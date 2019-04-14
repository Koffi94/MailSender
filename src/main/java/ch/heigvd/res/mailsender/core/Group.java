package ch.heigvd.res.mailsender.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Define group
 */
public class Group extends Groups{
    private List<Person> people;

    public Group(){
        people = new ArrayList<Person>();
    }

    public void addPersonInGroup(Person person){
        people.add(person);
    }

    public List<Person> getGroupOfPeople() {
        return people;
    }
}
