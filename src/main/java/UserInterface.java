import java.util.*;
public class UserInterface {

    Scanner input = new Scanner(System.in);
    boolean uiIsRunning = true;
    public void startProgram() {
        while (uiIsRunning) {
            showMainMenu();
            switch (input.nextInt()) {
                case 9 -> exitProgram();

            }
        }
    }
    private void showMainMenu(){
        System.out.println("Velkommen!");
    }


    private void exitProgram() {
        uiIsRunning = false;
        System.out.println("Slukker program. Hav en god dag!");
    }

}
