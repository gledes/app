package com.example.hanluo;

public class HanLuoTa {

    public static void main(String args[]) {
        System.out.println("hello, ��ã�");
        new HanLuoTa().move(3, 1, 2, 3);
    }

    public void move(int num, int src, int middle, int dist) {
        if (num <= 1) {
            System.out.println("����" + num + "������" + src + "----->����" + dist);
            return ;
        }
        move(num - 1, src, dist, middle);
        System.out.println("����" + num + "������" + src + "------>����" + dist);
        move(num - 1, middle, src, dist);
    }
}
