import java.awt.AWTException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class Main {
    public static void main(String[] args) throws InterruptedException, AWTException {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

        InputListener inputListener = new InputListener();
        GlobalScreen.addNativeKeyListener(inputListener);
        GlobalScreen.addNativeMouseListener(inputListener);
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        logger.setUseParentHandlers(false);
        var uiThread = new Thread(() -> Application.launch(UI.class));
        uiThread.start();
        PredictionEngine predictionEngine = PredictionEngine.getInstance();
        String lastCharacter = "";
        boolean wasIndexed = true;
        while (uiThread.isAlive()) {
            String[] sentence = inputListener.toString().split(" ");
            // this throws out the last word. this should leave the last word as a key.
            if (sentence.length > 5) {
                sentence[0] = lastCharacter + sentence[0];
                lastCharacter = sentence[sentence.length - 1];
                inputListener.clearOutput();
                for (int i = 0; i < sentence.length - 2; i++) {
                    predictionEngine.feed(sentence[i], sentence[i + 1]);
                }
                System.err.println(predictionEngine);

            }

            if (sentence.length > 1) {
                System.err.println(inputListener);
                System.err.println("Word to predict for: " + sentence[sentence.length - 1]);
                System.err.println("Partial word prediction: " + predictionEngine.availableWords(sentence[sentence.length - 1]));
                inputListener.updatePredictions(predictionEngine.availableWords(sentence[sentence.length - 1]));
            }

            Thread.sleep(10);
        }
        System.exit(0);
    }
}

