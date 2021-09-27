package ru.digitalleague.factory.ok.notification;


import ru.digitalleague.factory.ok.User;
import ru.digitalleague.factory.ok.enums.NotificationTemplate;

public class PhoneNotification implements Notification {

    private String body;
    private User user;

    public PhoneNotification(String body, User user) {
        this.body = body;
        this.user = user;
    }

    public String getText() {
        return String.format(
                NotificationTemplate.PHONE.getCode() + " %s\n%s",
                user.getPhone(),
                body
        );
    }
}
