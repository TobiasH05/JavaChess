package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    
    public Client(String addr, int port) {
        try {
            client = new Socket(addr, port);
            System.out.println("connected");
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            out.println("halla");

        } catch (Exception e) {

        }
    }


    public static void main(String[] args) {

        Client client = new Client("127.0.0.1", 6666);

    }
}
