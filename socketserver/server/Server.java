package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private int port;
    private ServerSocket serverSocket;
    private Socket sock;
    private PrintWriter out;
    private BufferedReader in;
    
    public Server(int port) {
        this.port = port;
    }


    public void start() {
        try {
            serverSocket = new ServerSocket(this.port);
            sock = serverSocket.accept();


            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));;
            String data = in.readLine();

            System.out.println(data);

            stop();
        } catch (Exception e) {
            stop();
        }
    }


    public void stop() {
        try {
            serverSocket.close();
            sock.close();

        } catch (Exception e) {
            System.out.println("Fuck");
        }
    }


    public static void main(String[] args) {
        Server server = new Server(6666);

        server.start();
        
    }
}