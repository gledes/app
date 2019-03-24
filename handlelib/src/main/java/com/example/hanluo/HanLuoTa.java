package com.example.hanluo;

public class HanLuoTa {

    public static void main(String args[]) {
        System.out.println("hello, 你好！");
        new HanLuoTa().move(3, 1, 2, 3);
    }

    public void move(int num, int src, int middle, int dist) {
        if (num <= 1) {
            System.out.println("盘子" + num + "从柱子" + src + "----->柱子" + dist);
            return ;
        }
        move(num - 1, src, dist, middle);
        System.out.println("盘子" + num + "从柱子" + src + "------>柱子" + dist);
        move(num - 1, middle, src, dist);
    }
}
