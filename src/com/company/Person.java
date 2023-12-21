package com.company;

import java.io.Serializable;

public class Person implements Serializable {
    // просто класс Person
    private String name = "Tom";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
