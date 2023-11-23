import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
public class Database {

    public class MemberDatabase {
        public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }
    private ArrayList<Member> membersArrayList = new ArrayList<>(1);

    public void addMember(String name,String surName, int age, String email, int phoneNumber, String dateOfBirth, String dateJoined,int annualFee, boolean isActive, String memberID, boolean isCompetitor) {
        membersArrayList.add(0, new Member(name, surName, age, email, phoneNumber, dateOfBirth, dateJoined, annualFee, isActive, memberID, isCompetitor));
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
