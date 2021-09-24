import java.io.*;
import java.nio.Buffer;
import java.util.HashSet;
import java.util.Set;

public class PredictionEngineTrainer {


    public static void main(String[] args) {
        File folder = new File("NewsArticles/");
        File[] articles = folder.listFiles();
        assert articles != null;
        PredictionEngine predictionEngine = PredictionEngine.getInstance();
        Set<String> validWords = defineValidWords();


        BufferedReader br;
        for (File file : articles) {
            if (file.isFile()) {
                try {
                    System.err.println("Training on: " + file.getName());
                    br = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = br.readLine()) != null) {
                        var words = line.split(" ");
                        for (int i = 0; i < words.length - 1; i++) {
                            String w1 = words[i], w2 = words[i + 1];
                            if (validWords.contains(w1) && validWords.contains(w2))
                                predictionEngine.train(w1, w2);
                            else if (validWords.contains(filterWord(w1)) && validWords.contains(filterWord(w2)))
                                predictionEngine.train(filterWord(w1), filterWord(w2));
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.err.println("File did not read, skipping.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.err.println(predictionEngine.toString());
    }

    private static Set<String> defineValidWords() {
        Set<String> set = new HashSet<>();
        try {
            var br = new BufferedReader(new FileReader("EnglishWords.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                set.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }

    private static String filterWord(String word) {
        return word.toLowerCase().trim().replaceAll("\\p{Punct}", "");
    }

}
