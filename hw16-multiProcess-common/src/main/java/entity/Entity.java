package entity;

public enum Entity {
    UNKNOWN("UNKNOWN"),
    FRONTEND("FRONTEND"),
    DATABASE("DATABASE"),
    MESSAGE_SYSTEM("MESSAGE_SYSTEM");

    private String value;

    public String getValue() {
        return value;
    }

    Entity(String value) {
        this.value = value;
    }
}
