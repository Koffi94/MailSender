package ch.heigvd.res.mailsender.core;

import java.util.List;

public class Group {
    private List<Person> groupOfPeople;

    public Group(List<Person> groupOfPeople) {
        this.groupOfPeople = groupOfPeople;
    }

    public List<Person> getGroupOfPeople() {
        return groupOfPeople;
    }
}
