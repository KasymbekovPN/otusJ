package ru.otus.kasymbekovPN.HW15.L25.domain_;

public class Message_ {
    private String messageStr;

    public Message_(String messageStr) {
        this.messageStr = messageStr;
    }

    public Message_() {
    }

    public String getMessageStr() {
        return this.messageStr;
    }

    public Message_ setMessageStr(String messageStr) {
        this.messageStr = messageStr;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Message_)) return false;
        final Message_ other = (Message_) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$messageStr = this.getMessageStr();
        final Object other$messageStr = other.getMessageStr();
        if (this$messageStr == null ? other$messageStr != null : !this$messageStr.equals(other$messageStr))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Message_;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $messageStr = this.getMessageStr();
        result = result * PRIME + ($messageStr == null ? 43 : $messageStr.hashCode());
        return result;
    }

    public String toString() {
        return "Message_(messageStr=" + this.getMessageStr() + ")";
    }
}
