package ru.otus.kasymbekovPN.HW16.common;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class Message {

    static final Message VOID_MESSAGE = new Message();

    private final UUID id = UUID.randomUUID();
    private final String fromHost;
    private final int fromPort;
    private final String toHost;
    private final int toPort;
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

    public String getFromHost() {
        return fromHost;
    }

    public int getFromPort() {
        return fromPort;
    }

    public String getToHost() {
        return toHost;
    }

    public int getToPort() {
        return toPort;
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
        this.fromHost = null;
        this.fromPort = 0;
        this.toHost = null;
        this.toPort = 0;
        this.sourceMessageId = Optional.empty();
        this.type = "voidTechnicalMessage";
        this.payload = new byte[1];
        this.payloadLength = this.payload.length;
    }

    public Message(String fromHost, int fromPort, String toHost, int toPort, Optional<UUID> sourceMessageId,
                   String type, byte[] payload) {
        this.fromHost = fromHost;
        this.fromPort = fromPort;
        this.toHost = toHost;
        this.toPort = toPort;
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
        return fromPort == message.fromPort &&
                Objects.equals(id, message.id) &&
                Objects.equals(fromHost, message.fromHost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromHost, fromPort);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", fromHost='" + fromHost + '\'' +
                ", fromPort=" + fromPort +
                ", toHost='" + toHost + '\'' +
                ", toPort=" + toPort +
                ", sourceMessageId=" + sourceMessageId +
                ", type='" + type + '\'' +
                ", payloadLength=" + payloadLength +
                ", payload=" + Arrays.toString(payload) +
                '}';
    }
}
