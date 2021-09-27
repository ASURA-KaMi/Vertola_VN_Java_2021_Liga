package ru.digitalleague.factory.ok;

import ru.digitalleague.factory.ok.enums.Localize;
import ru.digitalleague.factory.ok.enums.NotificationTemplate;
import ru.digitalleague.factory.ok.notification.Notification;
import ru.digitalleague.factory.ok.notification.decorator.LocalizedNotification;
import ru.digitalleague.factory.ok.notification.factory.NotificationFactory;
import ru.digitalleague.factory.ok.notification.factory.MailNotificationFactory;
import ru.digitalleague.factory.ok.notification.factory.PhoneNotificationFactory;

import java.util.ResourceBundle;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        User userRu = new User(2L, "Денис", "denis@gmail.com", "+79522668105", Localize.RUSSIAN);
        User userEn = new User(3L, "Georgi", "georji@britishcouncil.org", "+44 7911 123456", Localize.ENGLISH);
        User userDe = new User(4L, "Frank", "frank@fc-hosting.de", "+49-(0)5032-933-068-0", Localize.DEUTSCH);
        NotificationFactory factoryMail = new MailNotificationFactory();
        sendWithLocals(userDe, factoryMail);
        sendWithLocals(userEn, factoryMail);
        sendWithLocals(userRu, factoryMail);
        NotificationFactory factoryPhone = new PhoneNotificationFactory();
        sendWithLocals(userDe, factoryPhone);
        sendWithLocals(userEn, factoryPhone);
        sendWithLocals(userRu, factoryPhone);
    }

    private static void sendWithLocals(User user, NotificationFactory factory) {
        ResourceBundle local = ResourceBundle.getBundle("Bundle",
                Locale.forLanguageTag(user.getLanguage().getLocale()));
        Notification nativeMessage = factory.makeNotification(NotificationTemplate.GREETING.getCode(), user);
        LocalizedNotification localizedNotification = new LocalizedNotification(nativeMessage, local);
        sendNotification(localizedNotification);
    }
    private static void sendNotification(Notification notification) {
        System.out.println(notification.getText());
    }
}
