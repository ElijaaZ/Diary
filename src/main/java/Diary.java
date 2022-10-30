import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Diary extends Entry {

    private static List<Entry> entriesFromJSON = new ArrayList<>();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Path path = Paths.get("src/main/resources/entries.json");
    private static final Scanner scanner = new Scanner(System.in);


    static {
        try {
            entriesFromJSON = List.of(mapper.readValue(path.toFile(), Entry[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metod som skapar ett inlägg
     */
    static Entry addNewEntry() {
        System.out.println("Enter a title");
        String title = scanner.nextLine();
        System.out.println("Enter a text");
        String text = scanner.nextLine();

        String date = LocalDate.now().toString();
        return new Entry(title, text, date);
    }

    /**
     * Metod som skriver ut menyn för användaren
     */
    public void menu() {
        System.out.println("1. Read your entries \n" +
                "2. Write a new entry \n" +
                "3. Exit the program");
    }

    /**
     * Metod som läser upp entries från JSON
     */
    public static void readEntriesFromJSON() throws IOException {
        List<Entry> entriesFromJSONUPDATED = List.of(mapper.readValue(path.toFile(), Entry[].class));
        for (Entry entry : entriesFromJSONUPDATED) {
            showInfoAboutEveryEntry(entry);
        }
    }

    /**
     * Metod som sparar ner din nya entry till JSON
     * @throws IOException
     */
    public static void saveEntryToJSON() throws IOException {
        entriesFromJSON = List.of(mapper.readValue(path.toFile(), Entry[].class));
        List<Entry> entries = new ArrayList<>(entriesFromJSON);
        entries.add(addNewEntry());
        mapper.writeValue(path.toFile(), entries);
    }

    /**
     * Metod som visar informationen om din entry
     * @param entry
     */
    public static void showInfoAboutEveryEntry(Entry entry) {
        System.out.println("Title: " + entry.getTitle());
        System.out.println("Text: " + entry.getText());
        System.out.println("Date: " + entry.getDate());

    }

    /**
     * Metod som skriver ut ett hejdå meddelande och tackar användaren
     */
    public void goodbyeMessage() {
        System.out.println("Thank you for using the diary, goodbye!");
    }

}