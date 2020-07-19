package network;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static List<ClientHandler> onlineUsers = new ArrayList<>();
    public static List<testHandler> onlineUsers2 = new ArrayList<testHandler>();
    static int i = 0;

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(5056);
        ServerSocket testPort = new ServerSocket(55555);


        while (true) {
            Socket s = null;
            Socket test = null;
            try {
                s = ss.accept();
                test = testPort.accept();

                System.out.println("A new client is connected : " + s);

                // obtaining input and out streams
                DataInputStream dis2 = new DataInputStream(test.getInputStream());
                DataOutputStream dos2 = new DataOutputStream(test.getOutputStream());

                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                testHandler testHandler = new testHandler(test, "c" + i, dis2, dos2);
                ClientHandler mtch = new ClientHandler(s, "c" + i, dis, dos);

                // Create a new Thread with this object.
                Thread t = new Thread(mtch);
                Thread t2 = new Thread(testHandler);
                onlineUsers.add(mtch);
                onlineUsers2.add(testHandler);

                System.out.println("size:" + onlineUsers2.size());
                // Invoking the start() method
                t.start();
                t2.start();
                i++;

            } catch (Exception e) {
                s.close();
                e.printStackTrace();
            }
        }
    }
}

