package Task_1;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/resources/numbers.txt");
        String[] phoneNumbers = { "987-123-4567", "123 456 7890", "(123) 456-7890", "987 123-4567", "(123)-456-7890" };

        writePhoneNumbersToFile(phoneNumbers, file);
        printValidPhoneNumbers(file);
    }

    public static void writePhoneNumbersToFile(String[] phoneNumbers, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String phoneNumber : phoneNumbers) {
                writer.append(phoneNumber).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error while opening file: " + file.getName());
        }
        System.out.println("Phone numbers were successfully added to file: " + file.getName());
    }

    public static void printValidPhoneNumbers(File file) {
        final String regex = "(\\([0-9]{3}\\) |[0-9]{3}-)[0-9]{3}-[0-9]{4}";

        System.out.println("Valid phone numbers:");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String phoneNumber;
            while ((phoneNumber = reader.readLine()) != null) {
                if (phoneNumber.matches(regex)) {
                    System.out.println(phoneNumber);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while opening file: " + file.getName());
        }
    }
}

