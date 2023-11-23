import java.util.ArrayList;
public class Database {
    private ArrayList<Member> membersArrayList = new ArrayList<>(1);

    public void addMember(String name,String surName, int age, String email, int phoneNumber, String dateOfBirth, String dateJoined,int annualFee, boolean isActive, String memberID, boolean isCompetitor) {
        membersArrayList.add(0, new Member(name, surName, age, email, phoneNumber, dateOfBirth, dateJoined, annualFee, isActive, memberID, isCompetitor));
    }
}
