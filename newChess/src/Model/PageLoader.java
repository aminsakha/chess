package Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

public class PageLoader {
    static Stage stage;

    public static void initStage(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("Chess Game");
        stage.getIcons().add(new Image(Paths.get("C:\\newChess\\src\\Assests\\logo.jpg").toUri().toString()));
        stage.setHeight(435);
        stage.setWidth(435);
    }

    public void load(String url) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(url));
        stage.setScene(new Scene(root, 435, 435));
        stage.show();
    }
}
