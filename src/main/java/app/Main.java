package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilities.Constants;

import java.net.URL;

public class Main extends Application {

    private double xPos, yPos;

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL path = getClass().getResource(Constants.FXML_LOGIN);
        if (path != null) {
            Parent root = FXMLLoader.load(path);
            root.setOnMousePressed((value) -> {
                xPos = value.getSceneX();
                yPos = value.getSceneY();
            });

            root.setOnMouseDragged((value) -> {
                primaryStage.setX(value.getScreenX() - xPos);
                primaryStage.setY(value.getScreenY() - yPos);
            });

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);

            primaryStage.setTitle(Constants.APP_TITLE);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            System.err.println("Main FXML Could Not Be Loaded.");
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
