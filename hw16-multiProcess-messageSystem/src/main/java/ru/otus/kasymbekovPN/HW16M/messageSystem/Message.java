package ru.otus.kasymbekovPN.HW16M.messageSystem;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

//< !!! comment !!!
public class Message {
    static final Message VOID_MESSAGE = new Message();

    private final UUID id = UUID.randomUUID();
    private final String fromUrl;
    private final String toUrl;
    private final Optional<UUID> sourceMessageId;
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

    public Optional<UUID> getSourceMessageId() {
        return sourceMessageId;
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
        this.sourceMessageId = Optional.empty();
        this.type = "voidTechnicalMessage";
        this.payload = new byte[1];
        this.payloadLength = this.payload.length;
    }

    public Message(String fromUrl, String toUrl, Optional<UUID> sourceMessageId,
                   String type, byte[] payload)
    {
        this.fromUrl = fromUrl;
        this.toUrl = toUrl;
        this.sourceMessageId = sourceMessageId;
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
                ", sourceMessageId=" + sourceMessageId +
                ", type='" + type + '\'' +
                ", payloadLength=" + payloadLength +
                ", payload=" + Arrays.toString(payload) +
                '}';
    }
}