package Task_1;

import java.io.*;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/resources/numbers.txt");

        // Ensure the directory exists
        file.getParentFile().mkdirs();

        String[] phoneNumbers = { "987-123-4567", "123 456 7890", "(123) 456-7890", "987 123-4567", "(123)-456-7890" };

        writePhoneNumbersToFile(phoneNumbers, file);
        printValidPhoneNumbers(file);
    }

    public static void writePhoneNumbersToFile(String[] phoneNumbers, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String phoneNumber : phoneNumbers) {
                writer.append(phoneNumber).append("\n");
            }
            System.out.println("Phone numbers were successfully added to file: " + file.getName());
        } catch (IOException e) {
            System.out.println("Error while opening file: " + file.getName());
            e.printStackTrace();
        }
    }

    public static void printValidPhoneNumbers(File file) {
        final String regex = "\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}";
        Pattern pattern = Pattern.compile(regex);

        System.out.println("Valid phone numbers:");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String phoneNumber;
            while ((phoneNumber = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(phoneNumber);
                if (matcher.matches()) {
                    System.out.println(phoneNumber);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while opening file: " + file.getName());
            e.printStackTrace();
        }
    }
}

