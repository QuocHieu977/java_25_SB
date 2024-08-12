package com.example.demo.model;

public class Car implements Vehicle{
    @Override
    public void run() {
        System.out.println("Car is running");
    }
}
