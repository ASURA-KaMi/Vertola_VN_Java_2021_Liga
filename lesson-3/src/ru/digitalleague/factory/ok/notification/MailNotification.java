package ru.digitalleague.factory.ok.notification;


import ru.digitalleague.factory.ok.User;
import ru.digitalleague.factory.ok.enums.NotificationTemplate;

public class MailNotification implements Notification {

    private String body;
    private User user;
    private boolean hasAdvertisement;

    public MailNotification(String body, User user, boolean hasAdvertisement) {
        this.body = body;
        this.user = user;
        this.hasAdvertisement = hasAdvertisement;
    }

    public String getText() {
        return String.format(
                NotificationTemplate.ADDRESS.getCode() + "%s\n" + NotificationTemplate.HEADER.getCode() +
                        " %s,\n%s%s\n" + NotificationTemplate.ENDING.getCode(),
                user.getEmail(),
                user.getName(),
                body,
                hasAdvertisement ? "\n\n" + NotificationTemplate.AD.getCode() + "\n": ""
        );
    }
}
