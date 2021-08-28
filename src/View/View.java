package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class View extends Application {

    List<Button> suggestedWords;
    Pane root = new FlowPane();

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle("PredictiveText");
        suggestedWords = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            suggestedWords.add(new Button("Word " + i));
        }
        root.getChildren().addAll(suggestedWords);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("View/stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        primaryStage.setResizable(false);
        for (int i = 0; i < 1000; i++) {
            setButtonText(1, "" + i);
            Thread.currentThread().sleep(10);
        }
    }

    public void setButtonText(int button, String text) {
        root.getChildren().remove(button);
        root.getChildren().add(new Button(text));
    }
}
