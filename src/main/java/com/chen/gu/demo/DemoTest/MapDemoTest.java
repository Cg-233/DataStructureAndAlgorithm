package com.chen.gu.demo.DemoTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.transform.Source;

import com.alibaba.excel.util.StringUtils;

import org.apache.commons.compress.utils.Lists;

/**
 * @author wb-gc
 * @version 1.0
 * @date 2019/11/1 4:04 下午
 */
public class MapDemoTest {
    public static void main(String[] args) {

        method(null);
        MyQueue myQueue = new MyQueue(null, 1, "1111");
        MyQueue myQueue2 = new MyQueue(null, 2, null);
        MyQueue myQueue3 = new MyQueue(null, 3, "111d");
        MyQueue myQueue4 = new MyQueue(null, 4, "1111fwe");

        List<MyQueue> list = Lists.newArrayList();
        //list.add(myQueue);
        list.add(myQueue2);
        //list.add(myQueue3);
        //list.add(myQueue4);

        Map<Integer, String> collect = list.stream().filter(myQueue1 -> !StringUtils.isEmpty(myQueue1.getA()))
            .collect(Collectors.toMap(MyQueue::getP_start, MyQueue::getA));
        String s1 = collect.get(1);
        String s = collect.get(2);
        String s2 = collect.get(3);
        String s3 = collect.get(4);
        String s4 = collect.get(10);



        String a = null;
        String b = null;
        Long a1 = null;
        Long a2 = null;

        //boolean b1 = a.equals(b);
        boolean b2 = a1.equals(a2);
        System.out.println("_________");
    }

    private static void method(String str) {
        switch (str){
            case "1" :
                System.out.println("1111");

            default:
                System.out.println("22222");
        }
    }
}
