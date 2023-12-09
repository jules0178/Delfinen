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
                Velkommen til SVØMMEKLUBBEN DELFINEN
                ------------------------------------------------------
                1. Formanden
                2. Kasereren
                3. Træneren
                9. Afslut""");
    }

    private void chairmanMenu() {
        boolean chairmanMenuRunning = true;
        while (chairmanMenuRunning) {
            System.out.println("""
                    Velkommen til SVØMMEKLUBBEN DELFINEN
                    ------------------------------------------------------
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
                    ------------------------------------------------------
                    1. Se Kontingent for medlem
                    2. Se medlemmer i restance
                    3. Se forventet indkomst i år
                    9. Gå tilbage til hovedmenuen""");

            switch (takeUserInput()) {
                case 1 -> selectMember();
                case 2 -> membersInDebt();
                case 3 -> {
                    int total = controller.expectedAnnualIncome();
                    System.out.println("Den forventet indtægt for dette år er: " + total + ",00 kr.");
                    int debt = controller.totalDebt();
                    System.out.println();
                    System.out.println("Medlemmer skylder pr d.d.: " + debt + ",00 kr.");
                    int real = total-debt;
                    System.out.println();
                    System.out.println("Nettoindtægt pr. d.d.: " + real + ",00 kr.");
                    System.out.println();
                }
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
                    ----------------------------------------------------
                    1. Se top fem svømmere for junior eller senior hold.
                    2. Tilføj stævne resultat til svømmer.
                    3. Tilføj trænings result til svømmer.
                    4. Se en svømmers resultater.
                    9. Gå tilbage til hovedmenuen""");

            switch (takeUserInput()) {
                case 1 -> promptDisplayTopFive();
                case 2 -> addNewResult();
                case 3 -> addNewPracticeResult();
                case 4 -> displayResultPrompt();
                case 9 -> coachMenuRunning = false;
                default -> System.out.println("Ugyldigt input. Vælg et gyldigt tal fra menuen");
            }
        }
    }

    private void promptDisplayTopFive() {
        Team team = null;
        while (team == null) {
            System.out.println("""
                    Vælg junior eller senior hold.
                    1. Junior.
                    2. Senior.
                    """);

            switch (takeUserInput()) {
                case 1 -> team = controller.getJuniorTeam();
                case 2 -> team = controller.getSeniorTeam();
                default -> System.out.println("Indtast venligst et gyldigt tal.");
            }
        }

        Result.SwimStyle styleChoice = selectStyle();
        List<Swimmer> swimmers = new ArrayList<>();
        for (Member member : team.getMembers()) {
            if (member instanceof Swimmer) {
                swimmers.add((Swimmer) member);
            }
        }
        System.out.println("Antal svømmere på holdet: " + swimmers.size());

        displayTopFive(swimmers, styleChoice);
    }

    public void displayTopFive(List<Swimmer> team, Result.SwimStyle style) {
        List<SwimmerBestTime> swimmerTimes = new ArrayList<>();

        for (Swimmer swimmer : team) {
            CompetitionTime bestTime = swimmer.findBestTime(style);
            if (bestTime != null) {
                swimmerTimes.add(new SwimmerBestTime(swimmer, bestTime));
            }
        }
        swimmerTimes.sort(Comparator.comparing(SwimmerBestTime::getBestTime));

        for (int i = 0; i < Math.min(5, swimmerTimes.size()); i++) {
            SwimmerBestTime entry = swimmerTimes.get(i);
            System.out.println("Bedste tid opnået af " + entry.getSwimmer().getName() + ": " + entry.getBestTime() + " i " + style.getDiscipline());
        }
    }

    private void addNewPracticeResult() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Indtast medlemsID for svømmeren");
        String medlemsID = input.nextLine();

        System.out.println("Indtast dato for træningsresultat i format: dd/MM/åååå");
        LocalDate date = LocalDate.parse(input.nextLine(), formatter);

        Result.SwimStyle styleChoice = selectStyle();

        CompetitionTime time = promptCompetitionTime();

        String eventName = "Træning";

        controller.addResult(medlemsID, new Result(medlemsID, eventName, date, styleChoice, time, true));
        saveResults();
    }

    private void addNewResult() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Indtast medlemsID for svømmeren");
        String medlemsID = input.nextLine();

        System.out.println("Indtast titel på stævnet");
        String eventName = input.nextLine();

        System.out.println("Indtast dato for stævnet i formatet dd/MM/åååå");
        LocalDate date = LocalDate.parse(input.nextLine(), formatter);

        Result.SwimStyle styleChoice = selectStyle();

        CompetitionTime time = promptCompetitionTime();

        controller.addResult(medlemsID, new Result(medlemsID, eventName, date, styleChoice, time, false));
        saveResults();
    }

    public void displayResultPrompt() {
        System.out.println("Indtast medlemsID for svømmeren");
        String memberID = input.nextLine();
        Result.SwimStyle styleChoice = selectStyle();
        displayResults(memberID, styleChoice);
    }

    public void displayResults(String memberID, Result.SwimStyle swimStyle) {
        ArrayList<Result> results = controller.getResultList();
        Member swimmer = controller.findMemberByID(memberID);
        results.sort(Comparator.comparing(Result::getDate));

        boolean matchFound = false;
        for (Result r : results) {
            if (r.getMemberID().equalsIgnoreCase(memberID) && r.getStyle().equals(swimStyle)) {
                System.out.println(swimmer.getName() + r);
                matchFound = true;
            }
        }
        if (!matchFound) {
            System.out.println("Intet match for dette ID");
        }
        System.out.println();//linebreak
    }

    public Result.SwimStyle selectStyle() {
        System.out.println("Vælg disciplin");

        Result.SwimStyle[] swimStyles = Result.SwimStyle.values();
        for (int i = 0; i < swimStyles.length; i++) {
            System.out.println((i + 1) + ". " + swimStyles[i].getDiscipline());
        }
        while (true) {
            try {
                System.out.print("Indtast dit valg (1-" + swimStyles.length + "): ");
                int choice = Integer.parseInt(input.nextLine());
                if (choice < 1 || choice > swimStyles.length) {
                    System.out.println("Ugyldigt valg. Vælg et tal mellem 1 og " + swimStyles.length + ".");
                    continue;
                }
                Result.SwimStyle styleChoice = swimStyles[choice - 1];
                System.out.println("Du har valgt: " + styleChoice.getDiscipline());
                return styleChoice;
            } catch (NumberFormatException e) {
                System.out.println("Indtast venligst et gyldigt tal.");
            } catch (IllegalArgumentException e) {
                System.out.println("Disciplinen findes ikke");
            }
        }
    }

    private CompetitionTime promptCompetitionTime() {
        CompetitionTime time = null;
        while (time == null) {
            try {
                System.out.println("Indtast minutter");
                int minutes = Integer.parseInt(input.nextLine());

                System.out.println("Indtast sekunder");
                int seconds = Integer.parseInt(input.nextLine());

                System.out.println("Indtast hundrededele");
                int hundredths = Integer.parseInt(input.nextLine());

                time = CompetitionTime.createCompetitionTime(minutes, seconds, hundredths);
            } catch (NumberFormatException e) {
                System.out.println("Indtast venligst et gyldigt tal.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return time;
    }

    private void selectMember() {
        System.out.println("Indtast medlemsID");
        String selectedMember = input.nextLine();

        System.out.println("Medlemmet: " + controller.findMemberByID(selectedMember));

        Member m = controller.findMemberByID(selectedMember);
    }

    private void membersInDebt() {
        System.out.println("Medlemmer i restance:" + "\n");
        controller.membersInDebt();
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

        boolean isPaid = true;

        controller.addMember(name, surName, email, phoneNumber, dateOfBirth, dateJoined, isActive, isCompetitor, isPaid);
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

    private void deleteMember() {
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
            System.out.println("Tallet er ikke accpeteret");
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