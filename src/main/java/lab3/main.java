package lab3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load((getClass().getResource("/lab3.fxml")));
        primaryStage.setTitle("");
        primaryStage.setX(Toolkit.getDefaultToolkit().getScreenSize().width/2.0);
        primaryStage.setY(Toolkit.getDefaultToolkit().getScreenSize().height/2.0);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
