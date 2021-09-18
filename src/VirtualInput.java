import java.awt.*;
import java.awt.event.KeyEvent;

public class VirtualInput {
    private Robot robot;
    private boolean isActive;

    public VirtualInput() throws AWTException {
        robot = new Robot();
        isActive = false;
    }

    public void typeWord(String word) {
        isActive = true;
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        System.out.flush();
        for (int i = 0; i < word.length(); i++) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(word.charAt(i));
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
        }
        isActive = true;
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_SPACE);
    }

    public boolean isActive() {
        return isActive;
    }

    private void cleanSysOut() {

    }

    private void typeCharacter(int keyCode) {
        keyCode = KeyEvent.getExtendedKeyCodeForChar(keyCode);
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);
    }
}
