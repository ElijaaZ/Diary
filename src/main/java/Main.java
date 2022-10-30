import java.io.IOException;
import java.util.Scanner;

public class Main {

    /**
     * Main metod
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        welcomeMessage();

        Diary diary = new Diary();
        int choice;

        do {
            diary.menu();
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    Diary.readEntriesFromJSON();
                    break;
                case 2:
                    Diary.saveEntryToJSON();
                    break;
                case 3:
                    System.out.println("You chose to exit the program, welcome back another time!");
                    break;
                default:
                    System.out.println("Invalid input, pick a number between 1-3");
            }
        } while (choice != 3);

        diary.goodbyeMessage();

    }

    /**
     * Metod som välkomnar användaren
     */
    static void welcomeMessage() {
        System.out.println("Hello and welcome to your diary.");
    }
}
