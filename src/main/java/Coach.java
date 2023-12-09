import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Coach extends Member {
    public Coach(String name, String surName, String email, int phoneNumber, String dateOfBirth, String dateJoined, boolean isActive, boolean isCompetitor, String memberID, boolean isPaid) {
        super(name, surName, email, phoneNumber, dateOfBirth, dateJoined, isActive, isCompetitor, memberID, isPaid);
    }


    public void displayCompetitionStats(Swimmer swimmer){
        List<Result> resultsList = swimmer.getResults();
        for (Result results : resultsList) {
            System.out.println(results);
        }
    }


}