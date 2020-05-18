package com.chen.gu.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wb-gc
 * @version 1.0
 * @date 2020/4/13 14:24
 */
public class Demo {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String format = sdf.format(date);
        System.out.println(format);

    }
}
