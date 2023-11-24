import java.time.LocalDate;
        import java.util.ArrayList;
        import java.util.List;

public class Swimmer extends Member {
    public List<Result> results;
    public List<Result> practice;
    public Team team;

    public Swimmer(String name, String surName, String email, int phoneNumber, String dateOfBirth, String dateJoined, boolean isActive, boolean isCompetitor, String memberID) {
        super(name, surName, email, phoneNumber, dateOfBirth, dateJoined, isActive, isCompetitor, memberID);
        this.results = new ArrayList<>();
        this.practice = new ArrayList<>();
        this.team = null;
    }
    public void setTeam(Team team) {
        this.team = team;
    }
    public Team getTeam() {
        return team;

    }
}