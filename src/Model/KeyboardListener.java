package Model;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KeyboardListener implements NativeKeyListener {

    OutputStream out = new ByteArrayOutputStream();

    public void nativeKeyPressed(NativeKeyEvent e) {
        System.setOut(new PrintStream(out));
        String in = NativeKeyEvent.getKeyText(e.getKeyCode());
        if (in.length() > 1) {
            if (in.toLowerCase().equals("backspace")) {
                System.out.print("\b");
            } else if (in.toLowerCase().equals("space")) {
                System.out.print(" ");
            }
        } else {
            System.out.print(in);
        }
        // debug
//        System.err.print(out.toString());
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
    }

    public String getOutStream() {
        return out.toString();
    }

}
