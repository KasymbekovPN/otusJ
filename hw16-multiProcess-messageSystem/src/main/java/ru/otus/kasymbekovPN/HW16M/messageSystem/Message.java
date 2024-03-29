package ru.otus.kasymbekovPN.HW16M.messageSystem;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class Message {
    static final Message VOID_MESSAGE = new Message();

    private final UUID id = UUID.randomUUID();
    private final String fromUrl;
    private final String toUrl;
    private final String type;
    private final int payloadLength;
    private final byte[] payload;

    public static Message getVoidMessage() {
        return VOID_MESSAGE;
    }

    public UUID getId() {
        return id;
    }

    public String getFromUrl() {
        return fromUrl;
    }

    public String getToUrl() {
        return toUrl;
    }

    public String getType() {
        return type;
    }

    public int getPayloadLength() {
        return payloadLength;
    }

    public byte[] getPayload() {
        return payload;
    }

    public Message() {
        this.fromUrl = null;
        this.toUrl = null;
        this.type = "voidTechnicalMessage";
        this.payload = new byte[1];
        this.payloadLength = this.payload.length;
    }

    public Message(String fromUrl, String toUrl, String type, byte[] payload)
    {
        this.fromUrl = fromUrl;
        this.toUrl = toUrl;
        this.type = type;
        this.payload = payload;
        this.payloadLength = this.payload.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", fromUrl='" + fromUrl + '\'' +
                ", toUrl='" + toUrl + '\'' +
                ", type='" + type + '\'' +
                ", payloadLength=" + payloadLength +
                ", payload=" + Arrays.toString(payload) +
                '}';
    }
}
