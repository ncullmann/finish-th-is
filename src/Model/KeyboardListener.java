package Model;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

public class KeyboardListener implements NativeKeyListener, NativeMouseListener
{

    OutputStream out;

    public KeyboardListener()
    {
        out = new ByteArrayOutputStream();
    }

    public void nativeKeyPressed(NativeKeyEvent e)
    {
        System.setOut(new PrintStream(out));
        String in = NativeKeyEvent.getKeyText(e.getKeyCode());
        if (in.length() > 1) {
            if (in.equals("Backspace")) {
                System.out.print("\b");
            } else if (in.equals("Space") || in.equals("Enter")) {
                System.out.print(" ");
            }
        } else {
            System.out.print(in);
        }
        // debug
//        System.err.print(out.toString());
    }

    public void nativeMousePressed(NativeMouseEvent e)
    {
        clearOutput();
    }

    public String getOutput() { return out.toString(); }
    public void clearOutput() { out = new ByteArrayOutputStream(); }

    // empty methods
    public void nativeKeyReleased(NativeKeyEvent e) {}
    public void nativeKeyTyped(NativeKeyEvent e) {}
    public void nativeMouseReleased(NativeMouseEvent e) {}
    public void nativeMouseClicked(NativeMouseEvent e) {}
}
