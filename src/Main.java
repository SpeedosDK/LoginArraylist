import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class Main {
    // Laver en scanner som kan blive brugt overalt så man undgår at skabe flere scannere undervejs hvilket kan skabe problemer
    public static final Scanner scanner = new Scanner(System.in);

    // Laver en ArrayList til usernames og en til passwords
    private static final ArrayList<String> usernames = new ArrayList<>();
    private static final ArrayList<String> passwords = new ArrayList<>();
    private static final int MAX_TRIES = 3;

    public static void main(String[] args) {

        LocalTime tid = LocalTime.now().truncatedTo(ChronoUnit.SECONDS); // gør så tiden bliver vist i sekunder

        // Tilføjer navne og passwords til den ArrayList de hører til i
        usernames.add("admin");
        usernames.add("user1");
        usernames.add("user2");
        passwords.add("password123");
        passwords.add("letmein");
        passwords.add("qwerty");

        // Printer velkomstbesked og brugerens valgmuligheder
        System.out.println("Velkommen. Klokken er " + tid + ". Vælg en mulighed.");
        System.out.println("1. Lav ny bruger.");
        System.out.println("2. Login.");
        System.out.println("3. Exit.");

        boolean repeat;
        // Laver en do while løkke som kører igennem, sådan hvis man skriver noget forkert, så lukker programmet ikke ned, men kører videre.
        do {
            repeat = false;
            String choice = scanner.nextLine();

            // Her bliver der lavet en simpel switch case hvor bruger vælger om de vil logge ind eller lave en bruger
            switch (choice) {
                case "1":
                    accountCreate();
                    loginAccount();
                    break;
                case "2":
                    loginAccount();
                    break;
                case "3":
                    System.out.println("System lukker ned.");
                    scanner.close();
                    break;
                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
                    repeat = true;
            }
        } while (repeat);
    }

    public static void accountCreate() {

        // Bruger opretter et brugernavn og et kodeord
        String username = getUserInput("Lav dit brugernavn");
        String password = getUserInput("Lav dit password");
        // Her bliver brugerens indtastede username gemt i ArrayListen usernames
        usernames.add(username);
        // Her bliver brugerens indtaste password gemt i ArrayListen passwords
        passwords.add(password);
    }

    public static void loginAccount() {

        LocalTime tid = LocalTime.now().truncatedTo(ChronoUnit.SECONDS); // gør så tiden bliver vist i sekunder
        LocalDate dato = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // retter format fra mm-dd-yyyy til dd-mm-yyyy
        // Laver datatypen int som bliver kaldt tries. Den skal holde styr på hvor mange forsøg man bruger
        int tries = 0;
        // Laver datatypen boolean skal bliver kaldt wrongPassword
        boolean wrongPassword = false;
        // kører while loopet indtil bruger skriver det rigtige password eller har brugt 3 forsøg
        while(!wrongPassword && tries < MAX_TRIES) {
            String username = getUserInput("Skriv brugernavn");
            String password = getUserInput("Skriv password");

            //int index = usernames.indexOf(username); // initaliserer index som får ArrayListen usernames index af String username

            //Her tjekker vi om index ikke er lig med -1 og password er lig med datatypen Arraylist passwords hvor vi tjekker om indexet
            // passer med password i ArrayListen passwords. Index ville give -1 hvis den ikke findes og ved derfor gå ned i if
            // og sige brugeren ikke findes. I else if tjekker vi om password er forkert og hvis det er udskriver den og adder et forsøg

            if (findUserIndex(username) != -1 && password.equals(passwords.get(findUserIndex(username))))
            {
                wrongPassword = true;
                System.out.println("Login succesfuldt");
            }
            else if (findUserIndex(username) != -1 && !password.equals(findUserIndex(password)))
            {
                tries++;
                System.out.println("Forkert brugernavn eller password " + (3-tries) + " forsøg tilbage");
            }
            if (findUserIndex(username) == -1)
            {
                System.out.println("Brugeren findes ikke");
            }
        }
        if (!wrongPassword) {
            System.out.println("Du har brugt alle forsøg. Programmet lukker.");
        }
    }
    public static int findUserIndex(String username)
    {
        for (int i = 0; i < usernames.size(); i++)
        {
            if (username.equals(usernames.get(i)))
            {
                return i;
            }
        }
        return -1;
    }

    public static String getUserInput(String prompt)
    {
        System.out.println(prompt);
        return scanner.nextLine();
    }
}