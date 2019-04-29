package artistmanagement;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ArtistManagement {

    private int id;
    private String fName;
    private String lName;
    private int age;

    public static Scanner sc = new Scanner(System.in);
    public static Connection conn = null;
    public static Statement st = null;
    public static int option;
    public static boolean loop;

    public static void main(String[] args) throws SQLException {
        loop = true;

        connectToDb();

        while (loop) {
            System.out.println("\n");
            System.out.println("===========================");
            System.out.println("         Top Menue         ");
            System.out.println("===========================");
            System.out.println("1. Artist Management");
            System.out.println("2. Exit");
            System.out.println("===========================");

            int choice = ArtistMethods.readNumber("\nEnter a choice:");

            switch (choice) {
                case 1:
                    menue();
                    break;
                case 2:
                    loop = false;
                    System.out.println("Program terminated");
                    break;
                default:
                    System.out.println("Please choose a number between 1 and 2");
                    break;
            }

        }
    }

    private static void connectToDb() {
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ArtistDB", "app", "app");
        } catch (SQLException ex) {
            System.out.println("Connecting to database failed");
        }

        try {
            st = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("Creating the statement faield");
        }
    }

    public static void menue() throws SQLException {

        loop = true;

        while (loop) {
            System.out.println("\n");
            System.out.println("==========================");
            System.out.println("        Main Menue        ");
            System.out.println("==========================");
            System.out.println("0. To terminate program?");
            System.out.println("1. to add an Artist");
            System.out.println("2. to delete an Artist");
            System.out.println("3. to update first name");
            System.out.println("4. to update last name");
            System.out.println("5. to update age");
            System.out.println("6. show all");
            System.out.println("7. show By Id");
            System.out.println("==========================");
         

            int choice1 = ArtistMethods.readNumber("Enter a choice: ");

            switch (choice1) {
                case 0:

                    loop = false;
                    break;

                case 1:
                    System.out.println("case 1");
                    ArtistMethods.addArtist();
                    break;

                case 2:
                    ArtistMethods.showAll();
                    System.out.println("Delete Artist");
                    ArtistMethods.deleteName();
                    System.out.println("Artist deleted from list");
                    break;

                case 3:
                    ArtistMethods.showAll();
                    System.out.println("\nchange first name\n");
                    ArtistMethods.updateFName();
                    break;

                case 4:
                    ArtistMethods.showAll();
                    System.out.println("\nChange last name");
                    ArtistMethods.updateLName();
                    break;

                case 5:
                    ArtistMethods.showAll();
                    System.out.println("change age");
                    ArtistMethods.updateAge();
                    break;

                case 6:
                    System.out.println("\nList of all Artists");
                    ArtistMethods.showAll();
                    sc.nextLine();
                    break;

                case 7:
                    ArtistMethods.showAll();
                    ArtistMethods.findByID();
                    break;

                default:
                    System.out.println("Please choose a valid option 0-7.");
            }

        }

    }

}
