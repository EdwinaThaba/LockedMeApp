import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LockedMeApp {

    private static final String ROOT_DIRECTORY = "C:\\Users\\User\\OneDrive\\Documents\\LockedMeApp"; // Set your desired root directory

    public static void main(String[] args) {
        System.out.println("Welcome to LockedMe.com");
        System.out.println("Developer: Edwina Thaba");

        // Create the root directory if it doesn't exist
        File rootDir = new File(ROOT_DIRECTORY);
        if (!rootDir.exists()) {
            rootDir.mkdirs();
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    listFilesInAscendingOrder(ROOT_DIRECTORY);
                    break;
                case 2:
                    handleFileOperations(ROOT_DIRECTORY, scanner);
                    break;
                case 3:
                    System.out.println("Exiting the application.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. List files (Ascending Order)");
        System.out.println("2. File Operations");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void listFilesInAscendingOrder(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("The directory is empty.");
            return;
        }

        List<String> fileNames = new ArrayList<>();
        for (File file : files) {
            fileNames.add(file.getName());
        }

        Collections.sort(fileNames);
        System.out.println("\nFiles in ascending order:");
        for (String fileName : fileNames) {
            System.out.println(fileName);
        }
    }

    private static void handleFileOperations(String rootDirectory, Scanner scanner) {
        while (true) {
            System.out.println("\nFile Operations Menu:");
            System.out.println("1. Add a file");
            System.out.println("2. Delete a file");
            System.out.println("3. Search for a file");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addFile(rootDirectory, scanner);
                    break;
                case 2:
                    deleteFile(rootDirectory, scanner);
                    break;
                case 3:
                    searchFile(rootDirectory, scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void addFile(String rootDirectory, Scanner scanner) {
        System.out.print("Enter the file name to add: ");
        String fileName = scanner.nextLine();
        File file = new File(rootDirectory + fileName);

        try {
            if (file.createNewFile()) {
                System.out.println("File added successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void deleteFile(String rootDirectory, Scanner scanner) {
        System.out.print("Enter the file name to delete: ");
        String fileName = scanner.nextLine();
        File file = new File(rootDirectory + fileName);

        if (file.delete()) {
            System.out.println("File deleted successfully.");
        } else {
            System.out.println("File not found.");
        }
    }

    private static void searchFile(String rootDirectory, Scanner scanner) {
        System.out.print("Enter the file name to search: ");
        String fileName = scanner.nextLine();
        File file = new File(rootDirectory + fileName);

        if (file.exists()) {
            System.out.println("File found.");
        } else {
            System.out.println("File not found.");
        }
    }
}
