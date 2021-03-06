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
    TextArea textArea;

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
        DB.playerList.add(new Player(username.getText(), password.getText()));
        DB.savePlayersInformation();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "now you can log in");
        alert.showAndWait();
    }

    public void developer() throws IOException {

        new PageLoader().load("../ui/about.fxml");
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

    public void home() {
        try {
            new PageLoader().load("../ui/login.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

