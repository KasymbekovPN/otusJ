package ru.otus.kasymbekovPN.HW09;

public class QueryChunkImpl implements QueryChunk {
    private String name;
    private String type;
    private boolean isKey;

    public QueryChunkImpl(String name, String type, boolean isKey) {
        this.name = name;
        this.type = type;
        this.isKey = isKey;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean isKey() {
        return isKey;
    }
}
