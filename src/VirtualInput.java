import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

public class VirtualInput {
    private Robot robot;

    public VirtualInput() throws AWTException {
        robot = new Robot();
    }

    public void typeWord(String word) {
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        c.setContents(new StringSelection(word), null);
        robot.keyPress(KeyEvent.VK_CONTROL);
        typeCharacter('\b');
        typeCharacter('v');
        robot.keyRelease(KeyEvent.VK_CONTROL);
        typeCharacter(' ');
    }
    
    public void typeCharacter(char key) {
        robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(key));
        robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(key));
    }
}
