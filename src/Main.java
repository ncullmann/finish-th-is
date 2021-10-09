import java.awt.AWTException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Engine.EngineSuggestions;
import Engine.EngineTrainer;
import Interaction.InputListener;
import Interaction.UI;
import javafx.application.Application;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class Main {
    public static void main(String[] args) throws InterruptedException, AWTException, NativeHookException {
        var trainer = new EngineTrainer();

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
            System.err.println("Could not register hook, exiting...");
//            System.exit(1);
        }

        var suggestions = new EngineSuggestions();
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
            var len = sentence.length;
            if (len > 50) {
                inputListener.clearOutput();
                trainer.trainEngine(sentence);
            }

            if (len > 1) {
                // prediction based on last word fully typed, else use partial prediction
                if (inputListener.toString().endsWith(" ")) {
                    inputListener.updatePredictions(suggestions.getNextWords(sentence[len - 1]));
                } else {
                    inputListener.updatePredictions(suggestions.getAvailableWords(sentence[len - 2], sentence[len - 1]));
                }
            }
            Thread.sleep(100);
        }
        trainer.shutdown();
        System.exit(0);
    }
}

