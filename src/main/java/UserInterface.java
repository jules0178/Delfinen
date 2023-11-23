import java.util.*;

public class UserInterface {
    Scanner input = new Scanner(System.in);
    private final Controller controller;
    boolean uiIsRunning = true;

    public UserInterface() {
        this.controller = new Controller();
    }


    public void startProgram() {

        controller.addMember("Laura", "Madsen", "lauramadsen@gmail.com", 62376453, "15/02/2003", "23/11/2023", true, true);

        while (uiIsRunning) {
            showMainMenu();
            switch (input.nextInt()) {
                case 1 -> addMember();
                case 2 -> showMembers();
                case 9 -> exitProgram();

            }
        }
    }

    private void showMainMenu() {
        System.out.println("""
                Velkommen til svømmeklubben Delfinen!
                1. Tilføj nyt medlem
                2. Vis alle medlemmer
                9. Afslut programmet""");
    }

    private void addMember() {
        System.out.println("Hvad er fornavnet på det nye medlem?");
        input.nextLine();
        String name = input.nextLine();
        System.out.println("Hvad er deres efternavn?");
        String surName = input.nextLine();
        System.out.println("Hvad er deres email?");
        String email = input.nextLine();
        System.out.println("Hvad er deres telefon nr.?");
        int phoneNumber = input.nextInt();
        System.out.println("Hvornår har de fødselsdag? (dd/mm/yyyy)");
        input.nextLine();
        String dateOfBirth = input.nextLine();
        System.out.println("Hvornår er de blevet et medlem? (dd/mm/yyyy)");
        String dateJoined = input.nextLine();
        System.out.println("Er det et aktivt medlem? (j/n)");
        boolean isActive = input.next().equalsIgnoreCase("j");
        System.out.println("Er det en konkurrance svømmer?(j/n)");
        boolean isCompetitor = input.next().equalsIgnoreCase("j");
        controller.addMember(name, surName, email, phoneNumber, dateOfBirth, dateJoined, isActive, isCompetitor);
    }

    private void showMembers() {
        System.out.println(controller.showMembers());
    }

    private void exitProgram() {
        uiIsRunning = false;
        System.out.println("Slukker program. Hav en god dag!");
    }

}
