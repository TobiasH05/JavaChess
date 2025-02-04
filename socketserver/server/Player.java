package server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import utils.MessageBuilder;

public class Player {
    
    private Socket socket;
    private InputStream in;
    private OutputStream out;

    private boolean isWhite;

    private MessageBuilder builder;

    Player(Socket socket, boolean isWhite) {
        try {
            this.isWhite = isWhite;
            this.socket = socket;
            in = this.socket.getInputStream();
            out = this.socket.getOutputStream();

            builder = new MessageBuilder((byte) 8,(byte) 1);

            byte[] isWhiteMessage = new byte[8];
            isWhiteMessage[0] = (byte) (this.isWhite ? 0 : 1);

            byte[] msg = builder.build(isWhiteMessage);

            send(msg);

        } catch (Exception e) {
            System.out.println("Connection failed.");
        }
    }

    public void send(byte[] content) {
        byte[] message = builder.build(content);

        try {
            out.write(message);
            
        } catch (Exception e) {
            System.out.println("failed to send");
        }
    }

    public byte[] recv() {
        try {
            int length = in.read();
            byte[] content = in.readNBytes(length);
            return content;
        } catch (Exception e) {
            System.out.println("Failed to recv");
        }
        return new byte[] {0, 0, 0};
    }
}
