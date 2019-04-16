package com.example.designpatterns.proxy.dynamicproxy;

public class Car implements IVehical {
    @Override
    public void run() {
        System.out.println("Car is running");
    }
}
