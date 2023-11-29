import javax.imageio.IIOException;
import java.io.IOException;
import java.util.List;

public class Controller {
    private final Database memberDatabase;
    private final Database resultsDatabase;
    private final Team juniorTeam;
    private final Team seniorTeam;

    public Controller() throws IOException {
        this.memberDatabase = new Database();
        this.resultsDatabase = new Database();
        this.juniorTeam = new Team("Juniors");
        this.seniorTeam = new Team("Seniors");
        assignSwimmersToTeams();
    }

    private void assignSwimmersToTeams() {
        for (Member member : memberDatabase.getMembersArrayList()) {
            if (member instanceof Swimmer) {
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
    public void assignResults(String memberID) {
        Swimmer swimmer = (Swimmer) memberDatabase.findMemberByID(memberID);
        List<Result> results = swimmer.getResults();
        List<Result> practice = swimmer.getPractice();

        for (Result res : resultsDatabase.getResultList()) {
            if(res.getMemberID().equalsIgnoreCase(memberID) && !res.isPractice()){
                results.add(res);
            } else if (res.getMemberID().equalsIgnoreCase(memberID) && res.isPractice()) {
                practice.add(res);
            }

        }
    }

    public void addResult(String memberID, Result result) {
        Swimmer swimmer = (Swimmer) memberDatabase.findMemberByID(memberID);
        List<Result> results = swimmer.getResults();
        List<Result> practice = swimmer.getPractice();

            if (result.getMemberID().equalsIgnoreCase(memberID) && !result.isPractice()) {
                results.add(result);
            } else if (result.getMemberID().equalsIgnoreCase(memberID) && result.isPractice()) {
                practice.add(result);
            }
        }



    public void addMember(String name, String surName, String email, int phoneNumber, String dateOfBirth, String dateJoined, boolean isActive, boolean isCompetitor) {
        memberDatabase.addMember(name, surName, email, phoneNumber, dateOfBirth, dateJoined, isActive, isCompetitor);
    }

    public String showMembers() {
        return memberDatabase.showMembers();
    }

    public void saveMembers() {
        memberDatabase.saveMembers();
    }
    public void deleteMember(String memberID){
        Member memberToDelete = memberDatabase.findMemberByID(memberID);
        if (memberToDelete !=null){
            memberDatabase.removeMember(memberToDelete);
            System.out.println();
            System.out.println("Medlem med ID:" + memberID + ". Medlem er slettet.\n");
        }
    }
}