package network;

import Model.Player;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DB implements Serializable {
    public static List<Player> playerList = new ArrayList<>();


    public static void savePlayersInformation() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get("E:\\Projects\\chess\\newChess\\src\\network\\players.ser")))) {
            objectOutputStream.writeInt(playerList.size());
            for (Player player : playerList) {
                objectOutputStream.writeObject(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readPlayersInformation() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get("E:\\Projects\\chess\\newChess\\src\\network\\players.ser")))) {
            int size = objectInputStream.readInt();
            for (int i = 0; i < size; i++) {
                playerList.add((Player) objectInputStream.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
