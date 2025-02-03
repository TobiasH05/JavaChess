package server;

import java.net.ServerSocket;

public class Server {

    private int port;
    private ServerSocket serverSocket;

    private Player player1;
    private Player player2;

    
    public Server(int port) {
        this.port = port;
    }


    private void gameLoop() {

    }


    public void start() {
        try {
            serverSocket = new ServerSocket(this.port);

            player1 = new Player(serverSocket.accept(), true);
            player2 = new Player(serverSocket.accept(), false);

            byte[] message = player1.recv();

            player2.send(message);

            gameLoop();

            stop();
        } catch (Exception e) {
            stop();
        }
    }


    public void stop() {
        try {
            serverSocket.close();

        } catch (Exception e) {
            System.out.println("Fuck");
        }
    }


    public static void main(String[] args) {
        Server server = new Server(6666);

        server.start();
        
    }
}