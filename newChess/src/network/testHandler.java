package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

public class testHandler extends Thread{
    private String name;
    final DataInputStream dis;
    final DataOutputStream dos;
    Socket s;
    public testHandler(Socket s, String name,
                         DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.s = s;
    }

    @Override
    public void run() {
        String received;
        while (true) {
            try {
                received = dis.readUTF();
                for (testHandler mc : Server.onlineUsers2) {
                    if (mc.name.equals("c1"))
                        mc.dos.writeUTF(received);
                    else if (mc.name.equals("c0"))
                        mc.dos.writeUTF(received);
                }
                System.out.println(received);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

