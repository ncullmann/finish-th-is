import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class UI extends Application {
    private GridPane pane = new GridPane();
    private PredictionEngine predictionEngine;

    @Override
    public void start(Stage primaryStage) throws IOException {
        predictionEngine = PredictionEngine.getInstance();
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle("Finish Th-(is)");
        var scene = new Scene(pane);
        scene.getStylesheets().add("stylesheet.css");
        primaryStage.getIcons().add(new Image("icon.png"));
        setLabels();
//        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();

        // TODO: this updates labels but hogs CPU
        startDaemonTask(this::setLabels);
        final Button b = new Button("Button 1");
        pane.getChildren().add(b);
    }

    private void setLabels() {
        List<String> list = predictionEngine.availableWords();
        pane.getChildren().clear();
        for (int i = 0; i < 3; i++) {
            pane.add(new Label("        ...         "), i, 0);
        }
        for (int i = 0; i < 3 && i < list.size(); i++) {
            StringBuilder sb = new StringBuilder();
            // hopefully this changes label size
//            pane.getChildren().remove(i);
            pane.add(new Label(list.get(i)), i, 0);
        }
    }

    private void startDaemonTask(Runnable runnable) {
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
