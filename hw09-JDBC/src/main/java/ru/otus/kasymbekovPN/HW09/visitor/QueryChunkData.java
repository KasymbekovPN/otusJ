package ru.otus.kasymbekovPN.HW09.visitor;

import ru.otus.kasymbekovPN.HW09.query.QueryChunk;

import java.util.List;

public interface QueryChunkData {
    QueryChunk getKeyField();
    List<QueryChunk> getFields();
    boolean isValid();
}
