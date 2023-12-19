package com.company;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class Main {

    public static void main(String[] args) throws MalformedObjectNameException, InterruptedException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {

        // Запуск сервера
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("com.company:type=Hello");
        // какой бин мы используем на сервере
        Hello hello = new Hello();
        server.registerMBean(hello, name);

        // зацикливаем работы, что-бы не выключался
        System.out.println("Сервер запущен");
        Thread.sleep(Long.MAX_VALUE);

        // путь к консоли сервера C:\Program Files\Microsoft\jdk-11.0.16.101-hotspot\bin\jconsole
        // так же можно прописать программе вручную, некоторые данные, что-бы удобно было подключаться через удалённый доступ
        // -Dcom.sun.management.jmxremote.port=9999
        // -Dcom.sun.management.jmxremote.authenticate=false
        // -Dcom.sun.management.jmxremote.ssl=false
    }
}
