package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

//< rename
public enum Txt {
    COLON(":"),
    COMMA(","),
    NULL("null"),
    OPEN_BRACE("{"),
    CLOSE_BRACE("}"),
    OPEN_SQ_BRACKET("["),
    CLOSE_SQ_BRACKET("]"),
    DOUBLE_QUOTE("\"");

    private String value;

    public String get(){
        return value;
    }

    Txt(String value) {
        this.value = value;
    }
}
