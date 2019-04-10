package ch.heigvd.res.mailsender.core;

import java.util.ArrayList;
import java.util.List;

public class Mail {
    Person personFrom;
    Group groupTo;
    Prank message;

    public Mail(Person person, Group group, Prank message){
        this.personFrom = person;
        this.groupTo = group;
        this.message = message;
    }

    public String from(){
        return personFrom.getEmail();
    }

    public List<String> to(){
        List<String> listGroup = new ArrayList<String>();
        for(Person personTmp : groupTo.getGroupOfPeople()){
            listGroup.add(personTmp.getEmail());
        }

        return listGroup;
    }

    public String getMessage(){
        return message.getPrankMessage();
    }
}
