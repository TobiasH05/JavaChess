package server;
import java.net.ServerSocket;

public class Server {
    private int port;
    private ServerSocket serverSocket;
    private boolean running = true;

    private Player player1;
    private Player player2;

    public Server(int port) {
        this.port = port;
    }

    private boolean checkMove(byte[] move, boolean isWhite) {
        // TODO: check the moves server-side

        return true;
    }

    private void checkIfStillRunning() {

        // TODO: to be implemented, but should check after every move

        running = false;

    }

    private void gameLoop() {
        byte[] whiteMove;
        byte[] blackMove;

        while (true) {
            whiteMove = player1.recv();
            if (checkMove(whiteMove, true)) break;
        }

        player2.send(whiteMove);
        checkIfStillRunning();

        while (true) {
            blackMove = player2.recv();
            if (checkMove(blackMove, false)) break;
        }

        player1.send(blackMove);
        checkIfStillRunning();
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(this.port);

            player1 = new Player(serverSocket.accept(), true);
            player2 = new Player(serverSocket.accept(), false);

            while (true) {
                gameLoop();
                if (running) {
                    stop();
                    break;
                }
            }
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