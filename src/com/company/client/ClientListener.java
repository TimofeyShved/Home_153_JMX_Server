package com.company.client;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationListener;
import java.util.Date;

public class ClientListener implements NotificationListener {
    @Override
    public void handleNotification(Notification notification, Object handback) {
        // При выполнении кода нам приходят с сервера сообщения (Notification)
        // можем из них узнать текстовое сообщение, тип сообщения и время создания
        System.out.println(notification.getMessage());
        System.out.println(notification.getType());
        System.out.println(new Date(notification.getTimeStamp()));

        // и если есть атрибуты, то есть данные то и их можно вывести
        if (notification instanceof AttributeChangeNotification){
            // получаем атрибуты
            AttributeChangeNotification attributeChangeNotification = (AttributeChangeNotification) notification;

            // выводим атрибыты, имя переменной, тип данных и новое значения
            System.out.println(attributeChangeNotification.getAttributeName());
            System.out.println(attributeChangeNotification.getAttributeType());
            System.out.println(attributeChangeNotification.getNewValue());
        }
    }
}
