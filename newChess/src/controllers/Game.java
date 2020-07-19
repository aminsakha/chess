package controllers;

import Model.Block;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import network.DB;
import network.SocketHandler;
import piece.Pawn;
import piece.Piece;
import piece.Rock;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    @FXML
    AnchorPane pane;
    @FXML
    GridPane gridPane;

    List<Piece> pieces = new ArrayList<>();

    static boolean selectedNode = false;

    static Piece selectedPiece = null;

    final double Defult_Size = 50;

    private SocketHandler socketHandler = new SocketHandler();

    public Game() throws IOException {
    }

    private ImageView loadImage(String url) {
        ImageView imageView = new ImageView();
        Image tmpImage = new Image(url);
        imageView.setImage(tmpImage);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        return imageView;
    }

    public void adding(ImageView imageView, int column, int row) {
        gridPane.add(imageView, column, row);
        pieces.add(new Piece(new Block(column, row), imageView) {
        });
    }

    public void initialize() {
        ImageView blueRock1 = loadImage("file:/C:/newChess/src/Assests/blue%20rock.png");
        adding(blueRock1, 0, 0);
        ImageView blueRock2 = loadImage("file:/C:/newChess/src/Assests/blue%20rock.png");
        adding(blueRock2, 7, 0);
        ImageView blueKnight1 = loadImage("file:/C:/newChess/src/Assests/blue%20knight.png");
        adding(blueKnight1, 1, 0);
        ImageView blueKnight2 = loadImage("file:/C:/newChess/src/Assests/blue%20knight.png");
        adding(blueKnight2, 6, 0);
        ImageView blueBishop = loadImage("file:/C:/newChess/src/Assests/blue%20bishop.png");
        adding(blueBishop, 2, 0);
        ImageView blueBishop2 = loadImage("file:/C:/newChess/src/Assests/blue%20bishop.png");
        adding(blueBishop2, 5, 0);
        ImageView queen = loadImage("file:/C:/newChess/src/Assests/blue%20queen.png");
        adding(queen, 3, 0);
        ImageView king = loadImage("file:/C:/newChess/src/Assests/blue%20king.png");
        adding(king, 4, 0);
        for (int i = 0; i < 8; i++) {
            ImageView pawn = loadImage("file:/C:/newChess/src/Assests/blue%20pawn.png");
            adding(pawn, i, 1);
        }
        ImageView Rock1 = loadImage("file:/C:/newChess/src/Assests/rock.png");
        adding(Rock1, 0, 7);
        ImageView Rock2 = loadImage("file:/C:/newChess/src/Assests/rock.png");
        adding(Rock2, 7, 7);
        ImageView Knight1 = loadImage("file:/C:/newChess/src/Assests/knight.png");
        adding(Knight1, 1, 7);
        ImageView Knight2 = loadImage("file:/C:/newChess/src/Assests/knight.png");
        adding(Knight2, 6, 7);
        ImageView Bishop = loadImage("file:/C:/newChess/src/Assests/bishop.png");
        adding(Bishop, 2, 7);
        ImageView Bishop2 = loadImage("file:/C:/newChess/src/Assests/bishop.png");
        adding(Bishop2, 5, 7);
        ImageView queen2 = loadImage("file:/C:/newChess/src/Assests/queen.png");
        adding(queen2, 3, 7);
        ImageView king2 = loadImage("file:/C:/newChess/src/Assests/king.png");
        adding(king2, 4, 7);
        for (int i = 0; i < 8; i++) {
            ImageView pawn = loadImage("file:/C:/newChess/src/Assests/pown.png");
            adding(pawn, i, 6);
        }
        new Thread(this::listen).start();

    }

    @FXML
    private void mouseClicked(MouseEvent e) throws IOException {
        int columnIndex = (int) Math.floor(e.getX() / Defult_Size);
        int rowIndex = (int) Math.floor(e.getY() / Defult_Size);

        Piece currentPiece = getPiece(columnIndex, rowIndex);

        if (selectedNode) {
            gridPane.getChildren().remove(selectedPiece.imageView);

            if (currentPiece != null) {
                gridPane.getChildren().remove(currentPiece.imageView);
                pieces.remove(currentPiece);
            }

            gridPane.add(selectedPiece.imageView, columnIndex, rowIndex);
            socketHandler.sendMessage(selectedPiece.currentBlock.toString() + "," + columnIndex + "," + rowIndex);
            selectedPiece.move(new Block(columnIndex, rowIndex));

            selectedNode = false;
            selectedPiece = null;
        } else {
            selectedNode = true;

            if (currentPiece == null) {
                selectedNode = false;
                return;
            }
            selectedPiece = currentPiece;
        }
    }

    private Piece getPiece(int col, int row) {
        for (Piece piece : pieces) {
            if (piece.currentBlock.getColumnIndex() == col && piece.currentBlock.getRowIndex() == row) {
                return piece;
            }
        }
        return null;
    }

    private void listen() {
        while (true) {
            try {
                DataInputStream dis = new DataInputStream(socketHandler.socket.getInputStream());
                String msg = dis.readUTF();
                int columnIndex = Integer.parseInt(msg.split(",")[2]);
                int rowIndex = Integer.parseInt(msg.split(",")[3]);
                Piece selectedPiece = getPiece(Integer.parseInt(msg.split(",")[0]),
                        Integer.parseInt(msg.split(",")[1]));
                Piece currentPiece = getPiece(columnIndex, rowIndex);

                Platform.runLater(() -> {
                    if (selectedPiece != null) {
                        gridPane.getChildren().remove(selectedPiece.imageView);

                        if (currentPiece != null) {
                            gridPane.getChildren().remove(currentPiece.imageView);
                            pieces.remove(currentPiece);
                        }

                        gridPane.add(selectedPiece.imageView, columnIndex, rowIndex);
                        selectedPiece.move(new Block(columnIndex, rowIndex));
                    }
                    System.out.println("client:" + msg);
                });
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
}