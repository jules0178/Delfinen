import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final Database database;
    private final Team juniorTeam;
    private final Team seniorTeam;

    public Controller() throws IOException {
        this.database = new Database();
        this.juniorTeam = new Team("Juniors");
        this.seniorTeam = new Team("Seniors");
        assignSwimmersToTeams();
        assignResultsToSwimmers();
    }
    public Team getJuniorTeam() {
        return juniorTeam;
    }

    public Team getSeniorTeam() {
        return seniorTeam;
    }

    public Member findMemberByID(String memberID) {
        return database.findMemberByID(memberID);
    }
    private void assignSwimmersToTeams() {
        for (Member member : database.getMembersArrayList()) {
            if (member instanceof Swimmer && member.getIsActive()) {
                Swimmer swimmer = (Swimmer) member;
                int age = swimmer.calculateAge();
                if (age < 18) {
                    swimmer.setTeam(juniorTeam);
                    juniorTeam.addMember(swimmer);
                } else {
                    swimmer.setTeam(seniorTeam);
                    seniorTeam.addMember(swimmer);
                }
            }
        }
    }
    private void assignResultsToSwimmers() {
        List<Result> results = database.getResultList();

        for (Member member : database.getMembersArrayList()) {
            if (member instanceof Swimmer) {
                Swimmer swimmer = (Swimmer) member;
                for (Result result : results) {
                    if (result.getMemberID().equals(swimmer.getMemberID())) {
                        swimmer.addResult(result);
                    }
                }
            }
        }
    }
    public void addResult(String memberID, Result result) {
        Member member = database.findMemberByID(memberID);

        if (member instanceof Swimmer) {
            Swimmer swimmer = (Swimmer) member;
            List<Result> results = swimmer.getResults();

            if (result.getMemberID().equalsIgnoreCase(memberID)) {
                results.add(result);
            }
            database.addResult(memberID, result.getEventName(), result.getDate(), result.getStyle(), result.getTime(), result.isPractice());
        } else {
            System.out.println("Error: Member with ID " + memberID + " is not a swimmer. Cannot add result.");
        }
    }

    public void addMember(String name, String surName, String email, int phoneNumber, String dateOfBirth, String dateJoined, boolean isActive, boolean isCompetitor, boolean isPaid) {
        database.addMember(name, surName, email, phoneNumber, dateOfBirth, dateJoined, isActive, isCompetitor, isPaid);
    }

    public int getAnnualFee(Member member) {
        return database.getAnnualFee(member);
    }
    public int expectedAnnualIncome() {
        return database.expectedAnnualIncome();
    }

    public String searchMember(String searchMember) {
        return database.searchMember(searchMember);
    }

    public void membersInDebt (){
        database.membersInDebt();
    }
    public String showMembers() {
        return database.showMembers();
    }

    public void saveMembers() {
        database.saveMembers();
    }
    public void saveResults() {
        database.saveResults();
    }
    public ArrayList<Result> getResultList() {
        if (!database.getResultList().isEmpty()){
        return database.getResultList(); }
        return null;
    }
    public void deleteMember(String memberID){
        Member memberToDelete = database.findMemberByID(memberID);
        if (memberToDelete !=null){
            database.removeMember(memberToDelete);
            System.out.println();
            System.out.println("Medlem med ID:" + memberID + ". Medlem er slettet.\n");
        }
    }
}