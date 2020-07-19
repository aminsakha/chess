package controllers;

import Model.Block;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import network.SocketHandler;
import network.networkHandler2;
import piece.Piece;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class testController {
    @FXML
    private TextField text;
    @FXML
    private TextArea area;
    private networkHandler2 networkHandler=new networkHandler2();

    public testController() throws IOException {
    }
    public void initialize() {
        new Thread(this::listen).start();
    }
    public void sendChat(ActionEvent actionEvent) throws IOException {
        networkHandler.sendMessage(text.getText());
        area.setText(text.getText());

    }
    private void listen() {
        while (true) {
            try {
                DataInputStream dis = new DataInputStream(networkHandler.socket.getInputStream());
                String msg = dis.readUTF();

                Platform.runLater(() -> {
                    area.setText(msg);
                    System.out.println("client:" + msg);
                });
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
}
