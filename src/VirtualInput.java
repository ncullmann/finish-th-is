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
        robot.keyPress(KeyEvent.VK_CONTROL);
        typeCharacter('\b');
        robot.keyRelease(KeyEvent.VK_CONTROL);

        for (int i = 0; i < word.length(); i++) {
            typeCharacter(word.charAt(i));
        }
        typeCharacter(' ');
    }
    
    public void typeCharacter(char key) {
        robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(key));
        robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(key));
    }
}
