package ru.digitalleague.factory.ok.notification.decorator;

import ru.digitalleague.factory.ok.enums.NotificationTemplate;
import ru.digitalleague.factory.ok.notification.Notification;

import java.util.ResourceBundle;

public class LocalizedNotification implements Notification {
    private final Notification notification;
    private final ResourceBundle locals;

    public LocalizedNotification(Notification notification, ResourceBundle locals){
        this.notification = notification;
        this.locals = locals;
    }

    @Override
    public String getText(){
        String message = notification.getText();
        for (NotificationTemplate tmp : NotificationTemplate.values()) {
            message = message.replace(tmp.getCode(), locals.getString(tmp.name()));
        }
        return message;
    }
}
