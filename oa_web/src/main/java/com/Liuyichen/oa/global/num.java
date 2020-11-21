package com.Liuyichen.oa.global;

import java.util.Random;

public class num {
    public char[] serialNo = {'1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
            'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            'Z'};

    public String getSerialNo() {
        Random random = new Random();
        char[] cs = new char[10];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = serialNo[random.nextInt(serialNo.length)];
        }
        return new String(cs);
    }
}
