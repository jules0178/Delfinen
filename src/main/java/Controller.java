import javax.imageio.IIOException;
import java.io.IOException;

public class Controller {
    private final Database memberDatabase;
    private final Team juniorTeam;
    private final Team seniorTeam;

    public Controller() throws IOException {
        this.memberDatabase = new Database();
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
            System.out.println("Medlem med ID:" + memberID + ". Medlem er slettet med succes!\n");
        }
    }
}