package utils;

public class MessageBuilder {
    // the length in bytes (does not include the length of this byte)
    final private byte contentLength;
    final private byte messageVersion;

    public MessageBuilder(byte contentLength, byte messageVersion) {
        this.contentLength = contentLength;
        this.messageVersion = messageVersion;
    }

    public byte[] build(byte[] message) {
        if (message.length != contentLength) {
            throw new IllegalArgumentException("You do not have the right length of this message.");
        }

        byte[] payload = new byte[contentLength];

        payload[0] = contentLength;
        payload[1] = messageVersion;

        for (int i = 2; i < contentLength; i++) {
            payload[i] = message[i - 2];
        }

        return payload;
    }
}
