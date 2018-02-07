package com.mark.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.List;

public class RemoteService extends Service {
    public RemoteService() {
    }

    /**
     * 当服务绑定的时候调用此方法，返回IBinder对象，即AidlRemote.Stub实例
     * @param intent
     * @return
     */

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    /**
     * Stub为AidlRemote中的一个静态抽象内部类，并且此类继承了android.os.Binder
     * 并实现了com.mark.aidl.AidlRemote接口，也就是我们创建的AIDL接口。
     */
    AidlRemote.Stub iBinder = new AidlRemote.Stub() {

        /**
         * 实现计算年龄平均值方法
         * @param persons 多人集合
         * @return 集合中人的平均年龄
         * @throws RemoteException
         */
        @Override
        public float getAverageAge(List<Person> persons) throws RemoteException {
            int sum = 0;

            // 计算集合中人的总年龄
            for(Person person : persons) {
                sum += person.getAge();
            }

            // 返回总年龄除以人数-平均年龄
            return sum / persons.size();
        }

    };

}
