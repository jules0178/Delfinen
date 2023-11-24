import javax.imageio.IIOException;
import java.io.IOException;

public class Controller {
    private final Database memberDatabase;

    public Controller() throws IOException {
        this.memberDatabase = new Database();
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

}