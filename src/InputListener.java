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

    OutputStream out;
    List<String> predictedWords;
    VirtualInput virtualInput;

    public InputListener() throws AWTException {
        out = new ByteArrayOutputStream();
        predictedWords = new ArrayList<>();
        virtualInput = new VirtualInput();
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        System.setOut(new PrintStream(out));
        var in = NativeKeyEvent.getKeyText(e.getKeyCode());
        if (in.length() > 1) {
            if (in.equals("Backspace")) {
                System.out.print("\b");
            } else if (in.equals("Space") || in.equals("Enter")) {
                System.out.print(" ");
            }
        } else {
            try {
                switch (in) {
                    case "1" -> virtualInput.typeWord(predictedWords.get(0));
                    case "2" -> virtualInput.typeWord(predictedWords.get(1));
                    case "3" -> virtualInput.typeWord(predictedWords.get(2));
                    default -> System.out.print(in);
                }
            } catch (Exception awtException) {
                awtException.getCause();
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
