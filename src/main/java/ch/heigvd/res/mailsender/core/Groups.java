package ch.heigvd.res.mailsender.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Set all groups
 */
public class Groups {
    private List<Group> groupOfPeople;

    public Groups() {
        groupOfPeople = new ArrayList<Group>();
    }

    /**
     * Method who assign people to groups
     * @param receivers receivers users
     * @param nbGroups Groups number
     */
    public void addGroupOfPeople(ArrayList<Person> receivers, int nbGroups){

        int nbPersons = receivers.size();
        int nbInGroup = receivers.size() / nbGroups;
        ArrayList<Person> tmpReceiver = receivers;

        for(int i = 0; i < nbGroups; ++i){

            Group tmpGroup = new Group();

            // Check if it's the last group
            if(i == nbGroups -1)
                nbInGroup = nbInGroup + nbPersons % nbGroups;

            // Add people in the group
            for(int j = 0; j < nbInGroup; ++j){
                tmpGroup.addPersonInGroup(tmpReceiver.get(0));
                tmpReceiver.remove(0);
            }
            groupOfPeople.add(tmpGroup);
        }
    }

    public List<Group> getGroupOfPeople() {
        return groupOfPeople;
    }

}
