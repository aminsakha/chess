package controllers;

import Model.PageLoader;
import Model.Player;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import network.ClientHandler;
import network.DB;
import network.Server;


import java.io.IOException;

public class Panel {

@FXML
ImageView imageView;
    @FXML
    private ListView<String> onlineUsers = new ListView<>();

    @FXML
    private PasswordField changePass;

    public void initialize() {
        imageView.setImage(LoginAndSignUp.image);
        for (Player player : DB.playerList) {
            for (int i = 0; i <onlineUsers. ; i++) {

            }
            if (!onlineUsers.getItems().equals(player.getUserName()))
            onlineUsers.getItems().add(player.getUserName());
        }
    }

    public void startGame() throws IOException {
        new PageLoader().load("../ui/chessBoard.fxml");
    }


    public void changePass(ActionEvent actionEvent) {
    }

    public void logOut() throws IOException {
        Alert alert=new Alert(Alert.AlertType.WARNING,"you are logged out");
        alert.showAndWait();
        new PageLoader().load("../ui/login.fxml");
    }

    public void chat(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("../ui/chat.fxml");
    }

    public void refresh(ActionEvent actionEvent) {
        initialize();
    }
}
