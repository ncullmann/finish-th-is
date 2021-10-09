package interaction;

import engine.EngineSuggestions;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;


public class UI extends Application {

    private final GridPane pane;
    private final EngineSuggestions suggestions;

    public UI() {
        pane = new GridPane();
        suggestions = new EngineSuggestions();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle("Finish Th-(is)");
        var scene = new Scene(pane);
        scene.getStylesheets().add("file:src/Interaction/UIStylesheet.css");
        primaryStage.getIcons().add(new Image("file:assets/icon.png"));
        setLabels();
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
        startRecurringTask(this::setLabels);
    }

    private void setLabels() {
        List<String> list = suggestions.getAvailableWords();
        pane.getChildren().clear();

        for (int i = 0; i < 3; i++) {
            // default label
            pane.add(new Label("           ———           "), i, 0);
        }

        for (int i = 0; i < 3 && i < list.size(); i++) {
            // preserve label size
            String s = " ".repeat(Math.max(0, 10 - list.get(i).length())) +
                    list.get(i) +
                    " ".repeat(Math.max(0, 10 - list.get(i).length()));
            pane.add(new Label(s), i, 0);
        }
    }

    private void startRecurringTask(Runnable runnable) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    Platform.runLater(runnable);
                    Thread.sleep(100);
                }
            }
        };
        Thread t = new Thread(task);
        t.start();
    }
}
