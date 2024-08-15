package com.example.demoStreamAPI;

public class VietNam implements Greeting{

    @Override
    public void sayHello(String name) {
        System.out.println("Xin chào: " + name);
    }
}
