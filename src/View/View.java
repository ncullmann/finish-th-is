package View;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class View extends Application
{

    private static List<Button> suggestedWords;
    private static Pane root = new FlowPane();

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle("PredictiveText");
        suggestedWords = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            suggestedWords.add(new Button("Word " + i));
        }
        root.getChildren().addAll(suggestedWords);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("View/stylesheet.css");
        primaryStage.getIcons().add(new Image("View/icon.png"));
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public void setButtonText(int button, String text)
    {
        root.getChildren().remove(button);
        root.getChildren().add(new Button(text));
    }
}
