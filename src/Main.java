import java.awt.AWTException;
import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class Main {

    private static PredictionEngine predictionEngine = PredictionEngine.getInstance();
    private static final Set<String> validWords = defineValidWords();

    public static void main(String[] args) throws InterruptedException, AWTException {
        try {
            predictionEngine.loadState();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            System.err.println("No saved dictionary found, training...");
            defaultEngineTrain();
        }
        System.err.println(predictionEngine.toString());

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

        var inputListener = new InputListener();
        GlobalScreen.addNativeKeyListener(inputListener);
        GlobalScreen.addNativeMouseListener(inputListener);
        var logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        logger.setUseParentHandlers(false);
        Thread uiThread = new Thread(() -> Application.launch(UI.class));
        uiThread.start();

        while (uiThread.isAlive()) {
            var sentence = inputListener.toString().split(" ");

            if (sentence.length > 50) {
                inputListener.clearOutput();
                trainEngine(sentence);
            }

            if (sentence.length > 1) {
                // prediction based on last word fully typed, else use partial prediction
                if (inputListener.toString().endsWith(" ")) {
                    inputListener.updatePredictions(predictionEngine.nextWords(filterWord(sentence[sentence.length - 1])));
                } else {
                    inputListener.updatePredictions(predictionEngine.availableWords(filterWord(sentence[sentence.length - 2]), filterWord(sentence[sentence.length - 1])));
                }
//                System.err.println(inputListener);
//                System.err.println("Word to predict for: " + sentence[sentence.length - 1]);
//                System.err.println("Partial word prediction: " + predictionEngine.availableWords(filterWord(sentence[sentence.length - 2]), filterWord(sentence[sentence.length - 1])));
            }
            Thread.sleep(100);
        }
        try {
            predictionEngine.saveState();
        } catch (IOException e) {
            System.err.println("Could not save state.");
        }
        System.exit(0);
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

    private static void defaultEngineTrain() {
        File folder = new File("NewsArticles/");
        File[] articles = folder.listFiles();
        assert articles != null;

        BufferedReader br;
        for (File file : articles) {
            try {
                System.err.println("Feeding engine on: " + file.getName());
                br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    var words = line.split(" ");
                    if (words.length < 2)
                        continue;
                    else
                        trainEngine(words);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        System.err.println(predictionEngine.toString());
    }

    private static void trainEngine(String[] words) {
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i], w2 = words[i + 1];

            if (validWords.contains(filterWord(w1)) && validWords.contains(filterWord(w2))) {
                w1 = filterWord(w1);
                w2 = filterWord(w2);
            } else if (validWords.contains(filterWord(w1)) && validWords.contains(w2)) {
                w1 = filterWord(w1);
            } else if (validWords.contains(w1) && validWords.contains(filterWord(w2))) {
                w2 = filterWord(w2);
            } else if (validWords.contains(w1) && validWords.contains(w2)) {

            } else {
                w1 = "";
                w2 = "";
            }

            predictionEngine.train(w1, w2);
        }
    }

    private static String filterWord(String word) {
        return word.toLowerCase().trim().replaceAll("\\p{Punct}", "");
    }

}

