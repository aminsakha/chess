package Model;

import javafx.scene.image.Image;
import piece.*;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class Player implements Serializable {
private String userName;
private Image image;

    public Image getImage() {
        return image;
    }

    public Player(String userName, Image image, String passWord) {
        this.userName = userName;
        this.image = image;
        this.passWord = passWord;
    }

    public void setImage(Image image) {
        this.image = image;
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
