import Controller.WordPredictor;
import Model.KeyboardListener;
import View.View;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class Main
{
    public static void main(String[] args) throws InterruptedException {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        KeyboardListener kb = new KeyboardListener();
        GlobalScreen.addNativeKeyListener(kb);
        GlobalScreen.addNativeMouseListener(kb);
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);
        Application.launch(View.class);

        WordPredictor wordPredictor = new WordPredictor();
        String lastCharacter = "";
        boolean wasIndexed;
        for (;;) {
            String[] sentence = kb.getOutput().split(" ");
            wasIndexed = false;
            // this throws out the last word. this should leave the last word as a key.
            if (sentence.length > 5) {
                sentence[0] = lastCharacter + sentence[0];
                lastCharacter = sentence[sentence.length-1];
                kb.clearOutput();
                for (int i = 0; i < sentence.length-1; i++) {
                    wordPredictor.putWord(sentence[i], sentence[i+1]);
                }
                System.err.println(wordPredictor.toString());
                wasIndexed = true;
            }

            if (sentence.length > 1 && !wasIndexed) {
                System.err.println(kb.getOutput());
                System.err.println("Word to predict for: " + sentence[sentence.length-1]);
                System.err.println("Partial word prediction: " + wordPredictor.availableWords(sentence[sentence.length-1]));
            }

            Thread.sleep(10);
        }
    }
}
