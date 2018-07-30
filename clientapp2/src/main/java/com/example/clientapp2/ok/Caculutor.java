package com.example.clientapp2.ok;

import com.example.clientapp2.Replace;

/**
 * Created by jin on 2018/7/27.
 */

public class Caculutor {

    @Replace(clazz = "com.example.clientapp2.Caculutor", method = "cal")
    public int cal() {
        int i = 1;
        int j = 100;
        return j / i;
    }
}
