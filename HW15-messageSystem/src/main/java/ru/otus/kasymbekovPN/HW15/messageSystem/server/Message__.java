package ru.otus.kasymbekovPN.HW15.messageSystem.server;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class Message__ {

    static final Message__ VOID_MESSAGE = new Message__();

    private final UUID id = UUID.randomUUID();
    private final String from;
    private final String to;
    private final Optional<UUID> sourceMessageId;
    private final String type;
    private final int payloadLength;
    private final byte[] payload;

    public Message__() {
        this.from = null;
        this.to = null;
        this.sourceMessageId = Optional.empty();
        this.type = "voidTechnicalMessage";
        this.payload = new byte[1];
        this.payloadLength = this.payload.length;
    }

    public Message__(String from, String to, Optional<UUID> sourceMessageId, String type, byte[] payload) {
        this.from = from;
        this.to = to;
        this.sourceMessageId = sourceMessageId;
        this.type = type;
        this.payload = payload;
        this.payloadLength = payload.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message__ message__ = (Message__) o;
        return Objects.equals(id, message__.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Message__{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", sourceMessageId=" + sourceMessageId +
                ", type='" + type + '\'' +
                ", payloadLength=" + payloadLength +
                ", payload=" + Arrays.toString(payload) +
                '}';
    }

    public static Message__ getVoidMessage() {
        return VOID_MESSAGE;
    }

    public UUID getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
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
}
