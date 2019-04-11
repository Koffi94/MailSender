package ch.heigvd.res.mailsender.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Define group
 */
public class Group{
    private List<Person> groupOfPeople;

    public Group(){
        groupOfPeople = new ArrayList<Person>();
    }

    public void addPersonInGroup(Person person){
        groupOfPeople.add(person);
    }

    public List<Person> getGroupOfPeople() {
        return groupOfPeople;
    }
}
