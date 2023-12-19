package com.company;

public interface HelloMBean {

    //Данный интерфейс говорит о том с какими данными будет работать сервер, то есть какие команды он сможет выполнить при запросах
    public void sayHello();
    public int add(int x, int y);
    public Person returnPerson();
    public String getName();
    public int  getCacheSize();
    public void setCacheSize(int size);
}
