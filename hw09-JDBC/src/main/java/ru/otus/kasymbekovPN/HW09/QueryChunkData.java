package ru.otus.kasymbekovPN.HW09;

import java.util.List;

public interface QueryChunkData {
    QueryChunk getKeyField();
    List<QueryChunk> getFields();
    boolean isValid();
}
