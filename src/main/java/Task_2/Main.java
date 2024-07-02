package Task_2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/resources/users.txt");
        File json = new File("src/main/resources/user.json");

        ArrayList<User> users = readUsersFromFile(file);
        convertUsersToJson(users, json);
    }

    public static ArrayList<User> readUsersFromFile(File file) {
        ArrayList<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))  {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(" ");
                try {
                    users.add(new User(info[0], Integer.parseInt(info[1])));
                } catch (NumberFormatException ignored) {}
            }
        } catch (IOException e) {
            System.out.println("Error while opening file: " + file.getName());
        }
        System.out.println("Users were successfully read from file: " + file.getName());
        System.out.println(users);

        return users;
    }

    public static void convertUsersToJson(ArrayList<User> users, File json) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(json))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String str = gson.toJson(users);
            writer.write(str);
        } catch (IOException e) {
            System.out.println("Error while opening file: " + json.getName());
        }
        System.out.println("Users were successfully added to file: " + json.getName());
    }
}
