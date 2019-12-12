package entity;

public enum Entity {
    UNKNOWN("UNKNOWN"),
    FRONTEND("FRONTEND"),
    DATABASE("DATABASE"),
    MESSAGE_SYSTEM("MESSAGE_SYSTEM");
    //<
//    UNKNOWN("unknown"),
//    FRONTEND("frontend"),
//    DATABASE("database"),
//    MESSAGE_SYSTEM("messageSystem");

    private String value;

    //<
//    public static String check(String value){
//        String ret = Entity.UNKNOWN.getValue();
//        for (Entity entity : values()) {
//            if (entity.getValue().equals(value)){
//                ret = value;
//                break;
//            }
//        }
//
//        return ret;
//    }

    public String getValue() {
        return value;
    }

    Entity(String value) {
        this.value = value;
    }
}
