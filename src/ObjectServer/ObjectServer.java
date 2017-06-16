package ObjectServer;

import java.io.*;
import java.net.*;
import java.util.*;

public class ObjectServer {

   
    ServerSocket clientConn;

// ObjectOutputStream out ;
// ObjectInputStream in ;
    public ObjectServer(int port) {
        System.out.println(" Server␣connecting␣to␣port␣" + port);
        try {
            clientConn = new ServerSocket(port);
        } catch (Exception e) {
            System.out.println(" Exception : " + e);
            System.exit(1);
        }
    }
    
    

    public static void main(String[] args) {
        lista l = new lista();
        l.insert("foo", "192.168.1.1", 0);
        l.insert("bar", "192.168.1.2", 0);
        l.insert("baz", "192.168.1.3", 0);
        int port = 3000;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (Exception e) {
                port = 3000;
            } 
        }
        ObjectServer server = new ObjectServer(port);
        System.out.println("Server␣running␣on␣port␣" + port);
        server.listen(l);
    }

    public void listen(lista l) {
        
        int numer=0;
        
        for(int m=0;m<l.dirSize;m++){
                System.out.println(l.getHostName(m) + ":" + l.getOnOff(m));
                }      
                System.out.println("\n\n"+numer);
        
        
        try {
            System.out.println("Waiting␣for␣clients . . . ");
            while (true) {
                Socket clientReq = clientConn.accept();
                System.out.println(" Connection ␣ from␣" + clientReq.getInetAddress().getHostName());
                numer++;
                serviceClient(clientReq,l);
                
                for(int m=0;m<l.dirSize;m++){
                System.out.println(l.getHostName(m) + ":" + l.getOnOff(m));
                }      
                System.out.println("\n\n"+numer); 
            }
        } catch (Exception e) {
            System.out.println(" Excep tion : " + e);
        }
    }

    public void serviceClient(Socket s, lista l) {
        ObjectOutputStream outStream;
        ObjectInputStream inStream;
        try {
            outStream = new ObjectOutputStream(s.getOutputStream());
            inStream = new ObjectInputStream(s.getInputStream());
            int message_id;
            Object message = null;
            
                message_id = inStream.readInt();
                System.out.println("Got␣message ␣" + message_id);
                message = inStream.readObject();
                System.out.println("message " + message);

             
                int i = l.search(message);
            if (i != -1) {
                l.onOff[i]=message_id;

            } else {
                System.out.println(message + "not found");
            }
                outStream.writeObject(new String("dostalem"));   
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done . ");
    }
}
