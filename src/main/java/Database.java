import java.util.ArrayList;
import java.time.format.DateTimeFormatter;


public class Database {

    public class MemberDatabase {
    }
    private final ArrayList<Member> membersArrayList = new ArrayList<>(1);

    //method to automatically assign an ID to new members
    public String generateMemberID(String name, String surName) {
        String prefix = name.substring(0, 2).toLowerCase() + surName.substring(0, 2).toLowerCase();
        int count = 1;

        // Find the count for the current prefix
        for (Member member : membersArrayList) {
            if (member.getMemberID().startsWith(prefix)) {
                count++;
            }
        }
        // Format the count to a four-digit string
        String countString = String.format("%04d", count);

        return prefix + countString;
    }
    public void addMember(String name,String surName, String email, int phoneNumber, String dateOfBirth, String dateJoined, boolean isActive, boolean isCompetitor) {
        String memberID = generateMemberID(name, surName);
        membersArrayList.add(0, new Member(name, surName, email, phoneNumber, dateOfBirth, dateJoined, isActive, isCompetitor, memberID));
    }
    public String showMembers() {
        StringBuilder stringBuilder = new StringBuilder();
        if (membersArrayList.isEmpty()) {
            return "Der er ikke nogen medlemmer i databasen,";
        } else {
            stringBuilder.append("""
                    ~~~~~~~~~~~~~~~~~~~~~
                    Medlemmer i databasen
                    ~~~~~~~~~~~~~~~~~~~~~
                    """);
            for (Member member : membersArrayList) {
                stringBuilder.append("\n" + member + "\n--------------------");
            }
            return stringBuilder.toString();
        }

    }

}