package sockets;

public enum Entity {
    UNKNOWN("unknown"),
    FRONTEND("frontend"),
    DATABASE("database");

    private String value;

    public static String check(String value){
        String ret = Entity.UNKNOWN.getValue();
        for (Entity entity : values()) {
            if (entity.getValue().equals(value)){
                ret = value;
                break;
            }
        }

        return ret;
    }

    public String getValue() {
        return value;
    }

    Entity(String value) {
        this.value = value;
    }
}
