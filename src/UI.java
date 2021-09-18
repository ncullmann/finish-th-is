import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class UI extends Application {
    private List<Label> suggestedWords;
    private GridPane pane = new GridPane();
    private PredictionEngine predictionEngine;

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        predictionEngine = PredictionEngine.getInstance();
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle("Finish Th-(is)");
        var scene = new Scene(pane);
        scene.getStylesheets().add("stylesheet.css");
        primaryStage.getIcons().add(new Image("icon.png"));
        setLabels();
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
        startDaemonTask(this::setLabels);
    }

    private void setLabels() {
        List<String> list = predictionEngine.availableWords();
        for (int i = 0; i < 3; i++) {
            pane.add(new Label("     ...     "), i, 0);
        }
        for (int i = 0; i < 3 && i < list.size(); i++) {
            pane.add(new Label(list.get(i)), i, 0);
        }
    }

    public void init() throws InterruptedException {
    }

    private void startDaemonTask(Runnable runnable) {
        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    Platform.runLater(runnable);
                    Thread.sleep(100);
                }
            }
        };

        Thread th = new Thread(task);
        th.start();
    }
}
