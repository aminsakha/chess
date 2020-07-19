package controllers;

import Model.PageLoader;
import Model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import network.DB;
import network.Server;

import java.io.File;
import java.io.IOException;

public class LoginAndSignUp {
    public static String tmp = null;
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;
    @FXML
    Label label;
    @FXML
    ImageView imageView;

    public void initialize() {
        DB.readPlayersInformation();
    }

    public void login() throws IOException {

        tmp = username.getText();
        for (int i = 0; i < DB.playerList.size(); i++) {
            if (username.getText().equals(DB.playerList.get(i).getUserName()) &&
                    password.getText().equals(DB.playerList.get(i).getPassWord())) {
                new PageLoader().load("../ui/playerPanel.fxml");
            } else label.setVisible(true);
        }
    }


    public void signUp() {
        DB.playerList.add(new Player(username.getText(),image, password.getText()));
        DB.savePlayersInformation();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "now you can log in");
        alert.showAndWait();
    }


    public void developer() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("I am Amin Sakha with id 98243081\n iam so happy that you use this DEsKAPK");
        alert.showAndWait();
    }

    public static Image image;

    public void selectFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Picture");
        File selectedFile = fileChooser.showOpenDialog(null);
        image = new Image(selectedFile.toURI().toString());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "your photo has been uploaded");
        alert.showAndWait();
    }
}

