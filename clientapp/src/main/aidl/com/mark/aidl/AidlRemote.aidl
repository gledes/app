// AidlRemote.aidl
package com.mark.aidl;


import com.mark.aidl.Person;
// Declare any non-default types here with import statements

interface AidlRemote {

    // 获取每个人的平均年龄
    float getAverageAge(in List<Person> persons);

}
