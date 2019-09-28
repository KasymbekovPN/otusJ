package ru.otus.kasymbekovPN.HW09.query;

import ru.otus.kasymbekovPN.HW09.query.QueryChunk;

public class QueryChunkImpl implements QueryChunk {
//    private String name;
//    private String type;
//    private boolean isKey;
    //<
    private String queryChunk;
    private String name;
    private String type;

    public QueryChunkImpl(String name, String type, boolean isKey) {
//        this.name = name;
//        this.type = type;
//        this.isKey = isKey;
        //<
        StringBuilder sb = new StringBuilder(name)
                .append(" ")
                .append(type);
        if (isKey){
            sb.append(" ")
                    .append("AUTO_INCREMENT");
        }

        this.queryChunk = String.valueOf(sb);
        this.name = name;
        this.type = type;
    }

    //<
//    @Override
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public String getType() {
//        return type;
//    }
//
//    @Override
//    public boolean isKey() {
//        return isKey;
//    }

    @Override
    public String getQueryChunk() {
        return queryChunk;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }
}
