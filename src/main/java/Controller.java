public class Controller {
    private final Database memberDatabase;
    public Controller() {
        this.memberDatabase = new Database();
    }

    public void addMember(String name,String surName, int age, String email, int phoneNumber, String dateOfBirth, String dateJoined,int annualFee, boolean isActive, String memberID, boolean isCompetitor) {
        memberDatabase.addMember(name, surName, age, email, phoneNumber, dateOfBirth, dateJoined, annualFee, isActive, memberID, isCompetitor);
    }
}
