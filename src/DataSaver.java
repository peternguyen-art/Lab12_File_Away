import java.io.*;
import java.nio.file.*;
import javax.swing.*;
import java.util.Scanner;
import java.util.ArrayList;

public class DataSaver {
    public static void main(String [] args){
        Scanner in = new Scanner(System.in);
        ArrayList<String> userData = new ArrayList<>();
        String firstName, lastName, idNumber, email, birthDay;
        int counter =1;

        while(true){
            firstName = SafeInput.getNonZeroLenString(in,"Enter your first name");
            lastName= SafeInput.getNonZeroLenString(in, "Enter your last name");
            email = SafeInput.getNonZeroLenString(in, "Enter your email address");
            birthDay = SafeInput.getNonZeroLenString(in, "Enter your birth year");

            idNumber = String.format("%06d", counter);
            counter++;
            // Example: String myStr = "Hello %s! One kilobyte is %,d bytes.";
            //String result = String.format(myStr, "World", 1024);

            String record = String.join(", ", firstName,lastName,idNumber,email,birthDay);
            userData.add(record);

            boolean userResponse = SafeInput.getYNConfirm(in,"Do you wish to continue");
            if(!userResponse){
                break;
            }
        }

        String fileName = SafeInput.getNonZeroLenString(in, "Your file name is");
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("src/" +fileName))){
            for (String i : userData){
                writer.write(i);
                writer.newLine();
            }
            System.out.println("Data saved to your file "+fileName);
        } catch (IOException e){
            e.printStackTrace();
        }

        try(BufferedReader reader = new BufferedReader(new FileReader("src/" + fileName))){
            String file;
            System.out.println("Your file: ");
            while ((file = reader.readLine()) != null){
                System.out.println(file);
            }
        } catch(IOException e){
            e.printStackTrace();
        }

    }
}
