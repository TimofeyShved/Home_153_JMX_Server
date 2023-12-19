package com.company;

public class Hello implements HelloMBean{

    // данные с которыми работает сервер

    private String name = returnPerson().getName();

    @Override
    public void sayHello() {
        System.out.println("Привет "+name+", это моя программа на JMX");
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
}
