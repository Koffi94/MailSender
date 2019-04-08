package ch.heigvd.res.mailsender.core;

import java.util.ArrayList;

public class Prank {
    ArrayList<String> prankMessage;

    public Prank(ArrayList<String> prankMessage) {
        this.prankMessage = prankMessage;
    }

    public ArrayList<String> getPrankMessage() {
        return prankMessage;
    }
}
