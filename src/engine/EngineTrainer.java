package engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class EngineTrainer {

    // English words with correct spelling
    private final Set<String> validWords;
    private final PredictionEngine predictionEngine;

    public EngineTrainer() {
        predictionEngine = PredictionEngine.getInstance();
        validWords = defineValidWords();
        try {
            // load user dictionary, if available
            predictionEngine.loadState();
        } catch (ClassNotFoundException | IOException e) {
            defaultEngineTrain();
        }
    }

    public void trainEngine(String[] words) {
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i], w2 = words[i + 1];

            if (validWords.contains(w1) && validWords.contains(w2)) {
                // leave words capitalized/punctuated if valid
            } else if (validWords.contains(filterWord(w1)) && validWords.contains(w2)) {
                w1 = filterWord(w1);
            } else if (validWords.contains(w1) && validWords.contains(filterWord(w2))) {
                w2 = filterWord(w2);
            } else if (validWords.contains(filterWord(w1)) && validWords.contains(filterWord(w2))) {
                w1 = filterWord(w1);
                w2 = filterWord(w2);
            } else {
                w1 = "";
                w2 = "";
            }
            predictionEngine.train(w1, w2);
        }
    }

    public void shutdown() {
        try {
            predictionEngine.saveState();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not save state. Exiting...");
            System.exit(-1);
        }
    }

    private void defaultEngineTrain() {
        File folder = new File("assets/news_articles/");
        File[] articles = folder.listFiles();
        assert articles != null;

        // trains on thousands of news articles
        BufferedReader br;
        for (File file : articles) {
            try {
                br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    var words = line.split(" ");
                    if (words.length > 2) {
                        trainEngine(words);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Could not load articles, exiting...");
                System.exit(-1);
            }
        }
    }

    private Set<String> defineValidWords() {
        Set<String> set = new HashSet<>();
        try {
            var br = new BufferedReader(new FileReader("assets/english_words.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                set.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not load dictionary, exiting...");
            System.exit(-1);
        }
        return set;
    }

    private String filterWord(String word) {
        return word.toLowerCase().trim().replaceAll("\\p{Punct}", "");
    }

}
