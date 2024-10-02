import java.util.Scanner;
import java.util.ArrayList;

public class Main
{
    // Laver en scanner som kan blive brugt, sådan at man ikke laver flere scannere undervejs, da det kan skabe problemer
    public static final Scanner scanner = new Scanner(System.in);
    // Laver en ArrayList som bliver kaldt usernames. Den gemmer alle burgernavne i den
    private static final ArrayList usernames = new ArrayList();
    // Laver en ArrayList som bliver kaldt passwords. Den gemmer alle password i den
    private static final ArrayList passwords = new ArrayList();
    public static void main(String[] args)
    {

        System.out.println("1. Lav account");
        System.out.println("2. Login");
        int choice = scanner.nextInt();
        // Her bliver der lavet en simpel switch case hvor bruger vælger hvad de vil om de vil lave en account eller login
        switch (choice)
        {
            case 1:
                accountCreate();
                loginAccount();
                break;
            case 2:
                loginAccount();
                break;
        }
    }

    public static void accountCreate()
    {
        // Laver en nextLine, sådan at man først skal skrive brugernavn og derefter password ellers ville begge sout komme frem.
        scanner.nextLine();
        // Bruger laver et brugernavn og kodeord
        System.out.println("Lav dit username");
        String username = scanner.nextLine();
        System.out.println("Lav dit password");
        String password = scanner.nextLine();
        // Her bliver username gemt i arraylisten usernames
        usernames.add(username);
        // Her bliver password gmet i arraylisten passwords
        passwords.add(password);


    }

    public static void loginAccount()
    {

        // laver datatypen int som bliver kaldt tries. Den skal holde styr på hvor mange forsøg man bruger
        int tries = 0;
        // laver datatypen boolean skal bliver kaldt wrongPassword. Den tjekker om det forkert
        boolean wrongPassword = false;
        // kører while loopet indtil bruger skriver det rigtigt password eller har brugt 3 forsøg
        while(!wrongPassword && tries < 3) {
            System.out.println("Skriv dit username");
            String username = scanner.nextLine();
            System.out.println("Skriv dit password");
            String password = scanner.nextLine();
            // Her laver jeg en datatype int som jeg kalder index, hvor jeg får arraylisten usernames index af datatypen String username
            int index = usernames.indexOf(username);
            //Her tjekker vi om index ikke er lig med -1 og password er lig med datatypen Arraylist passwords hvor vi tjekker om indexet
            // passer med password i arraylisten passwords. index ville give -1 hvis den ikke findes og ved derfor gå ned i else if
            // og sige brugeren ikke findes
            if (index != -1 && password.equals(passwords.get(index))) {
                wrongPassword = true;
                System.out.println("Du er logget ind");
            } else if (index == -1) {
                System.out.println("Brugeren findes ikke ");
            } else {
                tries++;
                System.out.println("Forkert brugernavn eller password: " + (3 - tries) + " forsøg tilbage");
            }
        }
        if (!wrongPassword)
        {
            System.out.println("Du har brugt alle forsøg");
        }
    }
}