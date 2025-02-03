package server;

// might scrap this shit

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionHandler {
    
    private Socket sock;
    private InputStream in;
    private OutputStream out;


    ConnectionHandler(Socket sock) {
        try {
            this.sock = sock;
            this.in = sock.getInputStream();
            this.out = sock.getOutputStream();



        } catch (Exception e) {
            System.out.println("Connection failed.");
        }
    }


    

}
