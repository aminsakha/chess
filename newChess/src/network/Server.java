package network;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static List<ClientHandler> onlineUsers = new ArrayList<>();
    static int i = 0;

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(5056);


        while (true) {
            Socket s = null;
            try {
                s = ss.accept();

                System.out.println("A new client is connected : " + s);

                // obtaining input and out streams

                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                ClientHandler mtch = new ClientHandler(s, "c" + i, dis, dos);

                // Create a new Thread with this object.
                Thread t = new Thread(mtch);
                onlineUsers.add(mtch);



                // Invoking the start() method
                t.start();
                i++;

            } catch (Exception e) {
                s.close();
                e.printStackTrace();
            }
        }
    }
}

