import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class Bai4 {

    public static Set<String> findV() {
        Set<String> V = new HashSet<>();
        Map<String, Integer> wordCount = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("./UIT-ViOCD.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    wordCount.merge(word, 1, Integer::sum);
                }
            }

            wordCount.forEach((word, count) -> {
                if (count >= 5) {
                    V.add(word);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        return V;
    }

    public static void main(String[] args) {
        Set<String> words = findV();
        System.out.println("Words that appear at least 5 times:");
        for (String word : words) {
            System.out.println(word);
        }
    }
}