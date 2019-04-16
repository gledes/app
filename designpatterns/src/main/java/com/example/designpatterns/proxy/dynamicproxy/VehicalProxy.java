package com.example.designpatterns.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class VehicalProxy {

    private IVehical vehical;

    public VehicalProxy(IVehical vehical) {
        this.vehical = vehical;
    }

    public IVehical create() {

        final Class<?>[] interfaces = new Class[]{IVehical.class};
        VehicalInvocationHandler handler = new VehicalInvocationHandler(vehical);
        return (IVehical) Proxy.newProxyInstance(IVehical.class.getClassLoader(), interfaces, handler);
    }

}
