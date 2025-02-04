package client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import utils.MessageBuilder;

public class Client {
    
    private boolean isWhite;
    private Socket client;
    private InputStream in;
    private OutputStream out;
    private MessageBuilder builder;

    public Client(String addr, int port) {
        try {
            client = new Socket(addr, port);
            System.out.println("connected");
            out = client.getOutputStream();
            in = client.getInputStream();
            builder = new MessageBuilder((byte) 8, (byte) 1);

            byte[] whiteMsg = recv();
            isWhite = whiteMsg[1] == 1;

        } catch (Exception e) {

        }
    }

    public void send(byte[] content) {
        byte[] msg =  builder.build(content);
        try {
            out.write(msg);
        } catch (Exception e) {
        }
    }

    private byte[] recv() {
        try {
            int length = in.read();
            byte[] content = in.readNBytes(length);
            return content;
        } catch (Exception e) {
            return new byte[] {0, 0, 0};
        }
    }

    public void stop() {
        try {
            out.close();
            in.close();
            client.close();
        } catch (Exception e) {
            System.out.println("Fuck");
        }
    }

    public void makeTheMove() {
        // TODO: tobias makes the move
    }

    public void run() {
        while (true) {
            if (isWhite) {
                // TODO: this is first draft and just a outline of what the game loop will look like...

                while (true) {
                    byte[] mymove = { 1, 2, 3, 4, 5, 6, 7, 8 };
                    send(mymove);
                    byte[] validMove = recv();
                    if (validMove[1] == 1) break;
                }
                makeTheMove();

                byte[] othersMove = recv();
                makeTheMove();

            } else {

                byte[] othersMove = recv();
                makeTheMove();

                while (true) {
                    byte[] mymove = { 1, 2, 3, 4, 5, 6, 7, 8 };
                    send(mymove);
                    byte[] validMove = recv();
                    if (validMove[1] == 1) break;
                }
                makeTheMove();

            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 6666);

        client.stop();
    }
}
