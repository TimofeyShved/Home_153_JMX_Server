package com.company;

import javax.management.*;

public class Hello extends NotificationBroadcasterSupport implements HelloMBean{

    //----------------------------------------  данные с которыми работает сервер

    private String name = returnPerson().getName();
    static int notificationNumber = 0;

    @Override
    public void sayHello() {
        System.out.println("Привет "+name+", это моя программа на JMX");

        //работа с сообшениями на сервере(ниже есть нотификатор)
        Notification notification = new AttributeChangeNotification(
                this,
                notificationNumber++,
                System.currentTimeMillis(),
                "Вызвали метод sayHello",
                "Едиственное измение в нотификаторе переменной notificationNumber",
                "int",
                notificationNumber-1,
                notificationNumber);
        sendNotification(notification);
    }

    @Override
    public int add(int x, int y) {
        return x+y;
    }

    @Override
    public Person returnPerson() {
        Person person = new Person();
        return person;
    }

    @Override
    public String getName() {
        return returnPerson().getName();
    }

    @Override
    public int getCacheSize() {
        return 0;
    }

    @Override
    public void setCacheSize(int size) {

    }

    // ---------------------------------------- Сообщения на сервере
    // Кастомная информация при выполении кода на сервере
    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        // получаем тип того что меняем (наш атрибут)
        String[] types = new String[]{
                AttributeChangeNotification.ATTRIBUTE_CHANGE
        };

        // в каком классе
        String className = AttributeChangeNotification.class.getName();
        // сообщение
        String description = "Изменение атрибута Hello";

        //формируем и передаём сообщение на сервер
        MBeanNotificationInfo info = new MBeanNotificationInfo(types, className, description);
        return new  MBeanNotificationInfo[]{info};

        // (по умолчанию было это)
        // return super.getNotificationInfo();
    }
}
