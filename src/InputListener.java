import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

public class InputListener implements NativeKeyListener, NativeMouseListener {

    private OutputStream out;
    private List<String> predictedWords;
    private VirtualInput virtualInput;
    private boolean modifierPressed;

    public InputListener() throws AWTException {
        out = new ByteArrayOutputStream();
        predictedWords = new ArrayList<>();
        virtualInput = new VirtualInput();
        modifierPressed = false;
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        System.setOut(new PrintStream(out));
        String in = NativeKeyEvent.getKeyText(e.getKeyCode());
        if (in.length() > 1) {
            switch (in) {
                case "Backspace" -> System.out.print("\b");
                case "Space", "Enter" -> System.out.print(" ");
                case "Back Quote" -> modifierPressed = true;
            }
        } else {
            try {
                if (!modifierPressed) {
                    switch (in) {
                        case "1" -> virtualInput.typeWord(predictedWords.get(0));
                        case "2" -> virtualInput.typeWord(predictedWords.get(1));
                        case "3" -> virtualInput.typeWord(predictedWords.get(2));
                    }
                }
                System.out.print(in);
                modifierPressed = false;
            } catch (Exception awtException) {
                awtException.getCause();
                System.out.print(in);
            }
        }
        // debug
//        System.err.print(out.toString());
    }

    public void updatePredictions(List<String> predictions) {
        predictedWords = predictions;
    }

    public void clearOutput() {
        out = new ByteArrayOutputStream();
    }

    @Override
    public String toString() {
        return out.toString();
    }

    public void nativeMousePressed(NativeMouseEvent e) {
        clearOutput();
    }

    // empty methods
    public void nativeKeyReleased(NativeKeyEvent e) {
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
    }

    public void nativeMouseReleased(NativeMouseEvent e) {
    }

    public void nativeMouseClicked(NativeMouseEvent e) {
    }
}
