package View;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import Controller.WordPredictor;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    @FXML
    private static Button button1;
    @FXML
    private static Button button2;
    @FXML
    private static Button button3;

    private static WordPredictor predictor = WordPredictor.getInstance();

    @FXML
    public static void changeButtonText() {
        List<String> text = predictor.availableWords();
        List<Button> buttons = new ArrayList<>(Arrays.asList(button1, button2, button3));
        for (int i = 0; i < text.size(); i++) {
            buttons.get(i).setText(text.get(i));
        }
        button1 = buttons.get(0);
        button2 = buttons.get(1);
        button3 = buttons.get(2);
    }
}
