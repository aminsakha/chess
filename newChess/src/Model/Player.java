package Model;

import javafx.scene.image.Image;
import piece.*;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class Player implements Serializable {
    private String userName;

    public Player(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    private String passWord;
    Color color;
    List<Piece> pieces;
}
