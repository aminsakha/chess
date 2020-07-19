package network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class SocketHandler {

    public Socket socket;

    public SocketHandler() throws IOException {
        InetAddress ip = InetAddress.getByName("localhost");

        socket = new Socket(ip, 5056);
    }

    public void sendMessage(String message) throws IOException {
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        Thread sendMessage = new Thread(() -> {
            // read the message to deliver.
            try {
                // write on the output stream
                dos.writeUTF(message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        sendMessage.start();
    }
}
