import java.time.LocalDate;
import java.time.Period;
public class Member  {
     private String name;
        private String surName;
        private int age;
        private String email;
        private int phoneNumber;
        private String dateOfBirth;
        private String dateJoined;
        private int annualFee;
        private boolean isActive;
        private String memberID;
        private boolean isCompetitor;


        public Member (String name,String surName, String email, int phoneNumber, String dateOfBirth, String dateJoined, boolean isActive, boolean isCompetitor) {
            this.name = name;
            this.surName = surName;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.dateOfBirth = dateOfBirth;
            this.dateJoined = dateJoined;
            this.isActive = isActive;
            this.isCompetitor = isCompetitor;

        }
    public int calculateAge() {
        LocalDate birthDate = LocalDate.parse(dateOfBirth, Database.MemberDatabase.DATE_FORMATTER);
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }
public String toString() {
            return "Fornavn: " + name + "\n" +
                    "Efternavn: " + surName + "\n" +
                    "Alder: " + age + "\n" +
                    "Email: " + email + "\n" +
                    "Telefon nummer: " + phoneNumber + "\n" +
                    "Blev medlem d. " + dateJoined + "\n" +
                    "Er det et aktivt medlem? " + isActive + "\n" +
                    "Er det en konkurrance svømmer? " + isCompetitor + "\n" +
                    "Medlems ID: " + memberID;

}
    }




