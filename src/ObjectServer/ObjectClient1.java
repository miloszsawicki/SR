package ObjectServer;

import java.io.*;
import java.net.*;
import java.util.*;

public class ObjectClient1 {
    Socket serverConn;
    ObjectInputStream inStream = null;
    ObjectOutputStream outStream = null;

    public ObjectClient1(String host, int port) throws
            UnknownHostException, IOException {
        serverConn = new Socket(host, port);
        outStream = new ObjectOutputStream(serverConn.
                getOutputStream());
        inStream = new ObjectInputStream(serverConn.
                getInputStream());
    }

    public Object sendMessage(int message_id, Object message) throws Exception {
        outStream.writeInt(message_id);
        outStream.writeObject(message);
        outStream.flush();
        Object response = inStream.readObject();
        return response;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean finished = false;
        try {
            while (!finished) {            
            ObjectClient1 client1 = new ObjectClient1("localhost", 3000);
                String str = "bar";
                String r = (String) client1.sendMessage(1, str);
                System.out.println(" Server :  " + r);
                Thread.sleep( 3333 );

            }
        } catch (Exception e) {
            System.out.println("Exception : " + e);
            e.printStackTrace();
        }
    }
}
