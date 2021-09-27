package ru.digitalleague.factory.ok.enums;

public enum NotificationTemplate {
    HEADER,
    ADDRESS,
    GREETING,
    ENDING,
    PHONE,
    AD;

    public String getCode(){
        return (name());
    }
}
