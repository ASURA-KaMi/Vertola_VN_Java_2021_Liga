package ru.digitalleague.factory.ok.enums;

public enum Localize {
    RUSSIAN("ru"),
    ENGLISH("en"),
    DEUTSCH("de");

    private final String locale;
    Localize(String locale){
        this.locale = locale;
    }

    public String getLocale(){
        return locale;
    }
}
