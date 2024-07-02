package Task_3;

import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/resources/words.txt");

        TreeMap<String, Integer> wordsFrequency = convertToTreeMap(getWordsFrequency(readWordsFromFile(file)));
        System.out.println(wordsFrequency);
    }

    public static String[] readWordsFromFile(File file) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(" ");
            }
        } catch (IOException e) {
            System.out.println("Error while opening file: " + file.getName());
        }
        return builder.toString().split(" +");
    }

    public static HashMap<String, Integer> getWordsFrequency(String[] words) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String word : words) {
            int count = hashMap.getOrDefault(word, 0);
            hashMap.put(word, count + 1);
        }
        return hashMap;
    }

    public static TreeMap<String, Integer> convertToTreeMap(HashMap<String, Integer> hashMap) {
        return new TreeMap<>(hashMap);
    }
}
