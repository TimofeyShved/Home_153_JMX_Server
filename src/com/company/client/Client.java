package com.company.client;

import com.company.HelloMBean;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.net.MalformedURLException;

public class Client {
    public static void main(String[] args) throws IOException, MalformedObjectNameException {

        // Подключаемся к нашему серверу по url
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://:9999/jmxrmi");
        JMXConnector jmxConnector = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection mbsc = jmxConnector.getMBeanServerConnection();

        // Вытаскиваем нужный класс
        ObjectName mbeanName = new ObjectName("com.company:type=Hello");
        HelloMBean mbeanProxy = JMX.newMBeanProxy(mbsc, mbeanName, HelloMBean.class, true);

        // Работаем с ним
        mbeanProxy.sayHello();
        System.out.println(mbeanProxy.add(1,2));
        System.out.println(mbeanProxy.getName());
        System.out.println(mbeanProxy.returnPerson().getName());

        // Закрываем соединение
        jmxConnector.close();

    }
}
