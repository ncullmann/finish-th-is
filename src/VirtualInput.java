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
        Transferable t = c.getContents(this);
//        if (t == null)
//            return;
//        try {
//            jtxtfield.setText()
//        }
//        robot.keyPress(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.);
//        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
//        robot.keyRelease(KeyEvent.VK_CONTROL);
//        typeCharacter(KeyEvent.VK_SPACE);

    }
}
