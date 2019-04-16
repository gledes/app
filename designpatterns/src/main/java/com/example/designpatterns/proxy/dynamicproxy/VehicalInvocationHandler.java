package com.example.designpatterns.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class VehicalInvocationHandler implements InvocationHandler {


    private IVehical vehical;

    public VehicalInvocationHandler(IVehical vehical) {
        this.vehical = vehical;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("--before running...");
        Object ret = method.invoke(vehical, args);
        System.out.println("--after running...");

        return ret;
    }
}
