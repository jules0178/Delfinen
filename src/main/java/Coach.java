import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Coach extends Member {
    public Coach(String name, String surName, String email, int phoneNumber, String dateOfBirth, String dateJoined, boolean isActive, boolean isCompetitor, String memberID) {
        super(name, surName, email, phoneNumber, dateOfBirth, dateJoined, isActive, isCompetitor, memberID);
    }

    public void displayCompetitionStats(Swimmer swimmer){
        List<Result> resultsList = swimmer.getResults();
        for (Result results : resultsList) {
            System.out.println(results);
        }
    }
    public void displayPracticeStats(Swimmer swimmer){
        List<Result> practiceList = swimmer.getPractice();
        for (Result practice : practiceList) {
            System.out.println(practice);

        }
    }
    public void displayTopFive(List<Swimmer> team, Result.SwimStyle style) {
        List<SwimmerBestTime> swimmerTimes = new ArrayList<>();

        for (Swimmer swimmer : team) {
            CompetitionTime bestTime = swimmer.findBestTime(style);
            if (bestTime != null) {
                swimmerTimes.add(new SwimmerBestTime(swimmer, bestTime));
            }
        }

        swimmerTimes.sort(Comparator.comparing(SwimmerBestTime::getBestTime));

        for (int i = 0; i < Math.min(5, swimmerTimes.size()); i++) {
            SwimmerBestTime entry = swimmerTimes.get(i);
            System.out.println(" - Bedste tid opnået af " + entry.getSwimmer().getName() + " : " + entry.getBestTime());
        }
    }
}