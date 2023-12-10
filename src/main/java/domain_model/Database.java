package domain_model;

import data_source.Filehandler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Database {
    Filehandler filehandler = new Filehandler();
    private final ArrayList<Member> membersArrayList = new ArrayList<>();
    private final ArrayList<Result> resultList = new ArrayList<>();
    private final ArrayList<Member> searchMatches = new ArrayList<>();

    public Database() throws IOException {
        setMembersArrayList(filehandler.loadData());
        setResultList(filehandler.loadResults());
    }
    public ArrayList<Member> getMembersArrayList() {
        return membersArrayList;
    }
    public ArrayList<Result> getResultList() {
        return resultList;
    }
    public ArrayList<Member> getSearchMatches() {
        return searchMatches;
    }
    public void membersInDebt () {

        for (Member member : getMembersArrayList()) {
            int fee = getAnnualFee(member);
            boolean isPaid = member.getIsPaid();
            if (!isPaid) {
                System.out.println("domain_model.Member ID: " + member.getMemberID() +"\n" + "Navn: " + member.getName() + "\n" +
                        "mangler at betale: " + fee + ",00 kr.");

            }
        }
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
    public int expectedAnnualIncome() {
        ArrayList <Member> subs = getMembersArrayList();
        int total = 0;
        for (Member m : subs)  {
            total += m.getAnnualFee(m);

        }
        return total;
    }

    public String generateMemberID(String name, String surName) {
        String prefix = name.substring(0, 2).toLowerCase() + surName.substring(0, 2).toLowerCase();
        int count = 1;
        for (Member member : membersArrayList) {
            if (member.getMemberID().startsWith(prefix)) {
                count++;
            }
        }
        String countString = String.format("%04d", count);

        return prefix + countString;
    }

    //TODO Vi skal finde en måde at tage højde for inherited class frem for med boolean
    public void addMember(String name, String surName, String email, int phoneNumber, String dateOfBirth, String dateJoined, boolean isActive, boolean isCompetitor, boolean isPaid) {
        String memberID = generateMemberID(name, surName);

        if (isCompetitor) {
            Swimmer newSwimmer = new Swimmer(name, surName, email, phoneNumber, dateOfBirth, dateJoined, isActive, isCompetitor, memberID, isPaid);
            membersArrayList.add(0, newSwimmer);
        } else {
            membersArrayList.add(0, new Member(name, surName, email, phoneNumber, dateOfBirth, dateJoined, isActive, isCompetitor, memberID, isPaid));
        }
    }

    public String searchMember(String searchMember) {
        searchMatches.clear();
        StringBuilder stringBuilder = new StringBuilder();

        for (Member member : membersArrayList) {
            if (member.getName().toLowerCase().contains(searchMember.toLowerCase())
                    || member.getMemberID().toLowerCase().contains(searchMember.toLowerCase())) {
                stringBuilder.append("Fornavn: " + member.getName() + "\n" +
                        "Efternavn: " + member.getSurName() + "\n" +
                        "Medlems ID: " + member.getMemberID() + "\n");
                searchMatches.add(member);
                stringBuilder.append("--------------------\n");
            }
        }

        if (searchMatches.isEmpty()) {
            return "Ingen medlemmer fundet.";
        } else {
            return "Medlemmer fundet:\n" + stringBuilder;
        }
    }


    public void setMembersArrayList(ArrayList<Member> liste) {
        membersArrayList.addAll(liste);
    }

    public void saveMembers() {
        filehandler.saveMembers(membersArrayList);
    }

    public void addResult(String memberID, String eventName, LocalDate date, Result.SwimStyle swimStyle, CompetitionTime time, boolean isPractice) {
        resultList.add(new Result(memberID, eventName, date, swimStyle, time, isPractice));
    }

    public void setResultList(ArrayList<Result> list) {
        resultList.addAll(list);
    }

    public void saveResults() {
        filehandler.saveResults(resultList);
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
                stringBuilder.append("\n").append(member).append("\n--------------------\n");
            }
            return stringBuilder.toString();
        }
    }
    public Member findMemberByID(String memberID) {
        for (Member member : membersArrayList) {
            if (member.getMemberID().equalsIgnoreCase(memberID)) {
                return member;
            }
        }
        return null;
    }

    public void removeMember(Member member) {
        membersArrayList.remove(member);

    }
}