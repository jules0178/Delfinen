import java.util.ArrayList;
import java.util.List;
public class Team {
    private String name;
    private List<Member> members;
    public Team(String name) {
        this.name= name;
        this.members = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void addMember(Member member) {
        members.add(member);
    }
    public void removeMember(Member member) {
        members.remove(member);
    }
    public List<Member>getMembers() {
        return members;
    }
}
