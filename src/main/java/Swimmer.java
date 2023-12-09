import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Swimmer extends Member {

    public List<Result> results;
    public Team team;


    public Swimmer(String name, String surName, String email, int phoneNumber, String dateOfBirth, String dateJoined, boolean isActive, boolean isCompetitor, String memberID, boolean isPaid) {
        super(name, surName, email, phoneNumber, dateOfBirth, dateJoined, isActive, isCompetitor, memberID, isPaid);
        this.results = new ArrayList<>();
        this.team = null;
    }
    public void setTeam(Team team) {
        this.team = team;
    }
    public Team getTeam() {
        return team;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
    public void addResult(Result result) {
        this.results.add(result);
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