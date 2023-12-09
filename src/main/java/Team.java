import java.util.ArrayList;
import java.util.List;
public class Team {
    private String name;
    private List<Swimmer> swimmerList;
    public Team(String name) {
        this.name= name;
        this.swimmerList = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void addMember(Swimmer member) {
        swimmerList.add(member);
    }
    public void removeMember(Swimmer member) {
        swimmerList.remove(member);
    }
    public List<Swimmer>getMembers() {
        return swimmerList;
    }
}
