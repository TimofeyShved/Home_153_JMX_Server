package com.company.client;

import com.company.HelloMBean;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.net.MalformedURLException;

public class Client {
    public static void main(String[] args) throws IOException, MalformedObjectNameException, InstanceNotFoundException, InterruptedException {

        // Подключаемся к нашему серверу по url
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://:9999/jmxrmi");
        JMXConnector jmxConnector = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection mbsc = jmxConnector.getMBeanServerConnection();

        // Вытаскиваем нужный класс
        ObjectName mbeanName = new ObjectName("com.company:type=Hello");
        HelloMBean mbeanProxy = JMX.newMBeanProxy(mbsc, mbeanName, HelloMBean.class, true);

        // кроме взаимодействий нам ещё придёться добавить обработчик приходящих сообщений
        mbsc.addNotificationListener(mbeanName, new ClientListener(), null, null);

        // Работаем с ним
        mbeanProxy.sayHello();
        // спит текущий поток, что-бы потом обработать пришедший ответ (сообщение)
        Thread.sleep(1000);
        System.out.println(mbeanProxy.add(1,2));
        System.out.println(mbeanProxy.getName());
        System.out.println(mbeanProxy.returnPerson().getName());

        // Закрываем соединение
        jmxConnector.close();

    }
}
