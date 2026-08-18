import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        // These variables store the user's registration information.
        String storedUsername = "";
        String storedPassword = "";
        String storedPhoneNumber = "";
        String firstName = "";
        String lastName = "";

        boolean registered = false;

        System.out.println("=================================");
        System.out.println("       Welcome to ChatApp");
        System.out.println("=================================");

        boolean running = true;

        while (running) {

            System.out.println("\nPlease choose an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":

                    System.out.println("\n--- Registration ---");

                    System.out.print("Enter your first name: ");
                    firstName = scanner.nextLine();

                    System.out.print("Enter your last name: ");
                    lastName = scanner.nextLine();

                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();

                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    System.out.print("Enter phone number (+27...): ");
                    String phoneNumber = scanner.nextLine();

                    // Check all registration information.
                    String registrationMessage =
                            login.registerUser(username, password, phoneNumber);

                    System.out.println("\n" + registrationMessage);

                    // Only store the information if all validation checks pass.
                    if (login.checkUserName(username)
                            && login.checkPasswordComplexity(password)
                            && login.checkCellPhoneNumber(phoneNumber)) {

                        storedUsername = username;
                        storedPassword = password;
                        storedPhoneNumber = phoneNumber;

                        registered = true;

                        System.out.println("Registration successful!");
                    } else {
                        System.out.println("Registration failed. Please try again.");
                    }

                    break;

                case "2":

                    System.out.println("\n--- Login ---");

                    // The user must register before they can log in.
                    if (!registered) {
                        System.out.println("Please register before attempting to login.");
                        break;
                    }

                    System.out.print("Enter username: ");
                    String enteredUsername = scanner.nextLine();

                    System.out.print("Enter password: ");
                    String enteredPassword = scanner.nextLine();

                    // Check the entered credentials against the stored credentials.
                    boolean isLoggedIn = login.loginUser(
                            enteredUsername,
                            enteredPassword,
                            storedUsername,
                            storedPassword
                    );

                    // Display the appropriate login message.
                    System.out.println(
                            login.returnLoginStatus(
                                    isLoggedIn,
                                    firstName,
                                    lastName
                            )
                    );

                    break;

                case "3":

                    System.out.println("Thank you for using ChatApp. Goodbye!");
                    running = false;
                    break;

                default:

                    System.out.println("Invalid option. Please choose 1, 2 or 3.");
            }
        }

        scanner.close();
    }
}