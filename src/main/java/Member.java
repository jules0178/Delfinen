import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Member  {
    private String name;
    private String surName;
    private int age;
    private String email;
    private int phoneNumber;
    //TODO refaktoreres til LocalDate;
    private String dateOfBirth;
    //TODO refaktoreres til LocalDate;
    private String dateJoined;
    private int annualFee;
    protected boolean isActive;
    private String memberID;
    private boolean isCompetitor;
    private boolean isPaid;




    public Member (String name,String surName, String email, int phoneNumber, String dateOfBirth, String dateJoined, boolean isActive, boolean isCompetitor, String memberID, boolean isPaid) {
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.dateJoined = dateJoined;
        this.isActive = isActive;
        this.isCompetitor = isCompetitor;
        this.memberID = memberID;
        this.isPaid = isPaid;

    }
    public int getAnnualFee() {
        return annualFee;
    }

    public String getMemberID() {
        return memberID;
    }

    public int getAge() {
        return age;
    }

    public String getName(){
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getEmail() {
        return email;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public boolean getIsCompetitor() {
        return isCompetitor;
    }
    public boolean getIsPaid() {
        return isPaid;
    }

    public int getAnnualFee(Member member) {
        int seniorFee = 1600;
        int juniorFee = 1000;
        int passiveFee = 500;
        int fee = 0;
        int age = member.calculateAge();
        if (!member.isActive) {
            fee = passiveFee;
        }
        else if (age < 18) {
            fee = juniorFee;
        } else if (age > 18 && age < 60) {
            fee = seniorFee;

        } else {
            fee = seniorFee/100 * 75;
        }
      return fee;
    }



    public int calculateAge() {
        String[] dateFormats = {"dd/MM/yyyy", "d/M/yyyy"};

        for (String format : dateFormats) {
            try {
                LocalDate birthDate = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern(format));
                LocalDate currentDate = LocalDate.now();
                Period period = Period.between(birthDate, currentDate);
                return period.getYears();
            } catch (DateTimeParseException e) {
            }
        }
        System.out.println("Kunne ikke beregne alder, da forkert format blev indtastet");
        return -1;
    }



    public String toString() {
        return "Fornavn: " + name + "\n" +
                "Efternavn: " + surName + "\n" +
                "Alder: " + calculateAge() + "\n" +
                "Fødselsdag: " + dateOfBirth + "\n" +
                "Email: " + email + "\n" +
                "Telefon nummer: " + phoneNumber + "\n" +
                "Blev medlem d. " + dateJoined + "\n" +
                "Er det et aktivt medlem? " + isActive + "\n" +
                "Er det en konkurrence svømmer? " + isCompetitor + "\n" +
                "Medlems ID: " + memberID + "\n" +
                "Har betalt kontingent: " + isPaid;


    }
}