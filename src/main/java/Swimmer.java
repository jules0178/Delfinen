import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Swimmer extends Member {

    public List<Result> results;

    public List<Result> practice;

    public Swimmer(String name, String surName, String email, int phoneNumber, String dateOfBirth, String dateJoined, boolean isActive, boolean isCompetitor, String memberID) {
        super(name, surName, email, phoneNumber, dateOfBirth, dateJoined, isActive, isCompetitor, memberID);
        this.results = new ArrayList<>();
        this.practice = new ArrayList<>();

    }
    public List<Result> getPractice() {
        return practice;
    }

    public void setPractice(List<Result> practice) {
        this.practice = practice;
    }
    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public CompetitionTime findBestTime(Result.SwimStyle style) {
        List<Result> results = this.getResults();
        CompetitionTime personalBest = null;

        for (Result result : results) {
            if (result.getStyle() == style) {
                CompetitionTime currentTime = result.getTime();
                if (currentTime != null && (personalBest == null || personalBest.compareTo(currentTime) > 0)) {
                    personalBest = currentTime;
                }
            }
        }
        return personalBest;
    }

}