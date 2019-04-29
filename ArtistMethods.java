package artistmanagement;

import static artistmanagement.ArtistManagement.conn;
import static artistmanagement.ArtistManagement.sc;
import static artistmanagement.ArtistManagement.st;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class ArtistMethods {

    public static void addArtist() throws SQLException {
              
        PreparedStatement addUser = conn.prepareStatement("INSERT INTO artist(id,fName,lName,age) VALUES (?,?,?,?)");

        int id = readNumber("Enter the ID: ");
        System.out.print("fName: ");
        String fname = sc.nextLine();
        System.out.print("lName: ");
        String lname = sc.nextLine();
        int age = readNumber("Enter the age :");
        
        addUser.setInt(1, id);
        addUser.setString(2, fname);
        addUser.setString(3, lname);
        addUser.setInt(4, age);

        addUser.executeUpdate();

    }

    public static void updateFName() throws SQLException {
        
          PreparedStatement changeName = conn.prepareStatement("UPDATE artist SET fname=? WHERE id=?");

        int id = readNumber("\nEnter ID: ");
        System.out.print("Enter new First Name: ");
        String name = sc.nextLine();

        changeName.setString(1, name);
        changeName.setInt(2, id);

        changeName.executeUpdate();

    }

    public static void updateLName() throws SQLException {

        PreparedStatement changeName = conn.prepareStatement("UPDATE artist SET lname=? WHERE id=?");

        int id = readNumber("\nEnter ID: ");
        System.out.print("Enter new Last Name: ");
        String name = sc.nextLine();

        changeName.setString(1, name);
        changeName.setInt(2, id);

        changeName.executeUpdate();

    }

    public static void updateAge() throws SQLException {

        PreparedStatement changeAge = conn.prepareStatement("UPDATE artist SET age=?, WHERE id=?");

        int id = readNumber("\nEnter ID: ");
        int age = readNumber(" Enter new Age: ");
        

        changeAge.setInt(1, age);
        changeAge.setInt(2, id);
        

        changeAge.executeUpdate();

    }

    public static void deleteName() throws SQLException {

        PreparedStatement deleteName = conn.prepareStatement("DELETE from artist WHERE id=?");

        int id = readNumber("\nEnter id: ");

        deleteName.setInt(1, id);

        deleteName.executeUpdate();

    }

    public static void showAll() throws SQLException {

        ResultSet rs = st.executeQuery("Select * FROM Artist");
        while (rs.next()) {

            int id = rs.getInt("id");
            String fname = rs.getString("fname");
            String lname = rs.getString("lname");
            int age = rs.getInt("age");

            System.out.println(id + " " + fname + " " + lname + " " + age);
            
        }
    }

    public static void findByID() throws SQLException {
        
     
        int id = readNumber("\nEnter the id:");
        ResultSet rs = st.executeQuery("Select * from Artist where id = " + id + "");
        try {
            while (rs.next()) {
                System.out.println("\n");
                String fname = rs.getString("fName");
                String lname = rs.getString("lName");
                int age = rs.getInt("age");

                System.out.println("first Name: " + fname);
                System.out.println("last Name: " + lname);
                System.out.println("age: " + age);
            }
            //rs.close();
        } catch (Exception e) {
            System.out.println("enter the right id");
        }
    }



public static int readNumber(String s) {
        int number = 0;
        boolean loop = true;

        while (loop) {
            System.out.print(s + " ");
            try {
                number = sc.nextInt();
                sc.nextLine();
                loop = false;
            } catch (InputMismatchException e) {
                System.out.println("Only numeric vales allowed. Try again!");
                sc.nextLine();
            }
        }

        return number;
    }
}
