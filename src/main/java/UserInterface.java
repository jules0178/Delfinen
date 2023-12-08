import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserInterface {
    Scanner input = new Scanner(System.in);
    private final Controller controller;
    boolean uiIsRunning = true;

    public UserInterface() throws IOException {
        this.controller = new Controller();
    }

    public void startProgram() {
        while (uiIsRunning) {
            showMainMenu();

            switch (takeUserInput()) {
                case 1 -> chairmanMenu();
                case 2 -> treasurerMenu();
                case 3 -> coachMenu();
                case 9 -> exitProgram();
                default -> System.out.println("Ugyldigt input. Vælg et gyldigt tal fra menuen");
            }
        }
    }
    private void showMainMenu() {

        System.out.println("""
                Velkommen til svømmeklubben Delfinen!
                1. Formanden
                2. Kasereren
                3. Træneren
                9. Afslut""");
    }
    private void chairmanMenu() {
        boolean chairmanMenuRunning = true;

        while (chairmanMenuRunning) {
            System.out.println("""
                Velkommen til SVØMMEKLUBBEN DELFINEN.
                1. Tilføj nyt medlem
                2. Vis liste over alle medlemmer
                3. Rediger oplysninger for et medlem
                4. Slet et medlem
                5. Søg på medlemmer
                9. Gå tilbage til hovedmenuen""");

            switch (takeUserInput()) {
                case 1 -> addMember();
                case 2 -> showMembers();
                case 3 -> editMember();
                case 4 -> deleteMember();
                case 5 -> searchMember();
                case 9 -> chairmanMenuRunning = false;
                default -> System.out.println("Ugyldigt input. Vælg et gyldigt tal fra menuen");
            }
        }
    }

    private void treasurerMenu() {
        boolean treasurerMenuRunning = true;

        while (treasurerMenuRunning) {
            System.out.println("""
                    Velkommen til SVØMMEKLUBBEN DELFINEN
                    1. Se forventet indkomst i år (Funktion ikke oprettet endnu)
                    2. Se kontingent for enkelt medlem
                    9. Gå tilbage til hovedmenuen""");

            switch (takeUserInput()) {
                case 2 -> selectMember();
                case 9 -> treasurerMenuRunning = false;
                default -> System.out.println("Ugyldigt input. Vælg et gyldigt tal fra menuen");
            }
        }

    }

    private void coachMenu() {
        boolean coachMenuRunning = true;

        while (coachMenuRunning) {
            System.out.println("""
                    Velkommen til SVØMMEKLUBBEN DELFINEN
                    1. Se top 5 svømmere (Funktion ikke oprettet endnu)
                    2. Tilføj stævne resultat til en svømmer.
                    9. Gå tilbage til hovedmenuen""");

            switch (takeUserInput()) {
                case 2 ->  addNewResult();
                case 9 -> coachMenuRunning = false;
                default -> System.out.println("Ugyldigt input. Vælg et gyldigt tal fra menuen");
            }
        }
    }

    private void addNewResult() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        System.out.println("Indtast medlemsID for svømmeren");
        String medlemsID = input.nextLine();

        System.out.println("Indtast titel på stævnet");
        String eventName = input.nextLine();

        System.out.println("Indtast dato for stævnet i formatet dd/MM/åååå");
        LocalDate date = LocalDate.parse(input.nextLine(), formatter);

        System.out.println("Vælg disciplin");
        Result.SwimStyle styleChoice = null;

        for (Result.SwimStyle swimStyle : Result.SwimStyle.values()) {
            System.out.println(swimStyle.getDiscipline());
        }
        String userInput = input.nextLine().toUpperCase();

        try {
            styleChoice = Result.SwimStyle.valueOf(userInput);
            System.out.println("Du har valgt: " + styleChoice.getDiscipline());
        } catch (IllegalArgumentException e) {
            System.out.println("Disciplinen findes ikke");
            return; //TODO is the above println, and this return line needed?
        }

        System.out.println("Indtast minutter");
        int minutes = input.nextInt();

        input.nextLine();

        System.out.println("Indtast sekunder");
        int seconds = input.nextInt();

        input.nextLine();

        System.out.println("Indtast hundrededele ");
        int hundredths = input.nextInt();

        input.nextLine();

        CompetitionTime time = new CompetitionTime(minutes, seconds, hundredths);
        controller.addResult(medlemsID, new Result(medlemsID, eventName, date, styleChoice, time, false));
        saveResults();
    }

    private void selectMember() {
        System.out.println("Indtast medlemsID");
        String selectedMember = input.nextLine();

        controller.findMemberByID(selectedMember);
    }


    private void addMember() {
        System.out.println("Hvad er fornavnet på det nye medlem?");
        String name = input.nextLine();

        System.out.println("Hvad er deres efternavn?");
        String surName = input.nextLine();

        System.out.println("Hvad er deres email?");
        String email = input.nextLine();

        System.out.println("Hvad er deres telefon nr.?");
        int phoneNumber = input.nextInt();
        input.nextLine(); //linje til scanner bug

        System.out.println("Hvornår har de fødselsdag? (dd/mm/yyyy)");
        String dateOfBirth = input.nextLine();

        System.out.println("Hvornår er de blevet et medlem? (dd/mm/yyyy)");
        String dateJoined = input.nextLine();

        System.out.println("Er det et aktivt medlem? (j/n)");
        boolean isActive = input.next().equalsIgnoreCase("j");

        System.out.println("Er det en konkurrence svømmer?(j/n)");
        boolean isCompetitor = input.next().equalsIgnoreCase("j");

        controller.addMember(name, surName, email, phoneNumber, dateOfBirth, dateJoined, isActive, isCompetitor);
        saveMembers();
    }

    private void searchMember() {
        System.out.println("Søg efter navn eller ID: ");
        System.out.println(controller.searchMember(input.nextLine()));
    }

    private void showMembers() {
        System.out.println(controller.showMembers());
    }
    private void editMember() {
        boolean editing = true;

        while (editing) {
            System.out.println("Indtast medlemsID for det medlem, som du vil redigere:");
            String memberID = input.nextLine();
            Member memberToEdit = controller.findMemberByID(memberID);

            if (memberToEdit != null) {
                boolean memberEdited = false;

                while (true) {
                System.out.println("hvad vil du redigere?");
                System.out.println("1. Navn");
                System.out.println("2. Efternavn");
                System.out.println("3. Email");
                System.out.println("4. Telefonnummer");
                System.out.println("5. Fødselsdag");
                System.out.println("6. Aktivt medlem? (j/n)");
                System.out.println("7. Konkurrence svømmer? (j/n)");
                System.out.println("9. Vælg at gå tilbage til menuen igen");

                int choice = takeUserInput();

                switch (choice) {
                    case 1 -> {
                        System.out.println("Indtast nyt fornavn:");
                        String newName = input.nextLine();
                        memberToEdit.setName(newName);
                        memberEdited = true;
                    }
                    case 2 -> {
                        System.out.println("Indtast nyt efternavn:");
                        String newSurName = input.nextLine();
                        memberToEdit.setSurName(newSurName);
                        memberEdited = true;
                    }
                    case 3 -> {
                        System.out.println("Indtast ny email:");
                        String newEmail = input.nextLine();
                        memberToEdit.setEmail(newEmail);
                        memberEdited = true;
                    }
                    case 4 -> {
                        System.out.println("Indtast nyt telefon nummer:");
                        int newPhoneNumber = input.nextInt();
                        input.nextLine();
                        memberToEdit.setPhoneNumber(newPhoneNumber);
                        memberEdited = true;
                    }
                    case 5 -> {
                        System.out.println("Indtast ny fødselsdag (dd/mm/yyyy):");
                        String newDateOfBirth = input.nextLine();
                        memberToEdit.setDateOfBirth(newDateOfBirth);
                        memberEdited = true;
                    }
                    case 6 -> {
                        System.out.println("Er det et aktivt medlem? (j/n)");
                        boolean isActive = input.next().equalsIgnoreCase("j");
                        input.nextLine();
                        memberToEdit.setIsActive(isActive);
                        memberEdited = true;
                    }
                    case 7 -> {
                        System.out.println("Er det en konkurrence svømmer? (j/n)");
                        boolean isCompetitor = input.next().equalsIgnoreCase("j");
                        input.nextLine();
                        memberToEdit.setIsCompetitor(isCompetitor);
                        memberEdited = true;
                    }
                    case 9 -> {
                        System.out.println("Du valgte at gå tilbage til menuen.");
                        editing = false;
                    }
                    default -> System.out.println("Ugyldigt valg.");
                }

                if (memberEdited) {
                    System.out.println("Medlemmet er redigeret!");
                    System.out.println();

                    System.out.println("Er der mere du vil redigere? (ja/nej)");
                    String continueEditingInput = input.nextLine();

                    if (continueEditingInput.equalsIgnoreCase("nej")) {
                        System.out.println("Ingen yderligere ændringer blev foretaget. Afslutter redigering.");
                        System.out.println();
                        editing = false;
                        break;
                    } else if (!continueEditingInput.equalsIgnoreCase("ja")) {
                        System.out.println("Ugyldigt valg. Afslutter redigering.");
                        System.out.println();
                        editing = false;
                        break;
                    }
                } else {
                    System.out.println("Ingen ændringer blev foretaget.");
                    System.out.println();
                    break;
                }
            }
        } else {
            System.out.println("Medlemmet med ID: " + memberID + " blev ikke fundet.");
        }

        if (editing) {
            System.out.println("Du blev sendt tilbage til menuen.");
            System.out.println();
            editing = false;
        }
    }
}



    private void saveMembers() {
        controller.saveMembers();
        System.out.println("Alle ændringer er blevet gemt");
    }
    private void saveResults() {
        controller.saveResults();
        System.out.println("Alle resultater er gemt");
    }
    private void deleteMember(){
        System.out.println();
        System.out.println("Indtast medlemmets ID, som du vil slette:");
        String memberID = input.nextLine();

        System.out.println("Er du sikker på, at du vil slette dette medlem? (ja/nej) = (j/n)");
        String confirmation = input.nextLine();

        if (confirmation.equalsIgnoreCase("ja") || confirmation.equalsIgnoreCase("j")) {
            controller.deleteMember(memberID);
        } else {
            System.out.println("Handling afbrudt.");
        }
    }


    private int takeUserInput() {
        String inputString = input.nextLine();
        int inputInt = 0;

        try {
            inputInt = Integer.parseInt(inputString);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Try again:");
            inputInt = takeUserInput();
        }
        return inputInt;
    }

    private void exitProgram() {
        saveMembers();
        uiIsRunning = false;
        System.out.println("Programmet afsluttes. Hav en god dag!");
    }
}