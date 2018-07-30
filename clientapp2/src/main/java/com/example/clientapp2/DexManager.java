package com.example.clientapp2;


import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import dalvik.system.DexFile;

/**
 * Created by jin on 2018/7/25.
 */

public class DexManager {

    private Context context;


    public DexManager(Context context) {
        this.context = context;
    }

    public void loadDex(File dexFileDex) {
        File optFile = new File(context.getCacheDir(), dexFileDex.getName());
        if (optFile.exists()) {
            optFile.delete();
        }

        try {
            DexFile dexFile = DexFile.loadDex(dexFileDex.getAbsolutePath(), optFile.getAbsolutePath(), Context.MODE_PRIVATE);
            Enumeration<String> entry = dexFile.entries();
            while (entry.hasMoreElements()) {
                String className = entry.nextElement();
                Class realClazz = dexFile.loadClass(className, context.getClassLoader());
                Log.i(MainActivity.TAG, "找到的类：" + className);

                fix(realClazz);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fix(Class realClazz) {
        Method[] methods = realClazz.getDeclaredMethods();
        for (Method method : methods) {
            Replace replace = method.getAnnotation(Replace.class);
            if (replace == null) {
                continue;
            }
            String wrongClazzName = replace.clazz();
            String wrongMethodName = replace.method();

            try {
                Class wrongClazz = Class.forName(wrongClazzName);
                Method wrongMethod = wrongClazz.getMethod(wrongMethodName, method.getParameterTypes());
//                replace
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }


        }
    }
}
