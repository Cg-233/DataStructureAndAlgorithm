package com.chen.gu.demo.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

/**
 * @author wb-gc
 * @version 1.0
 * @date 2019/9/16 12:05
 */
public class StreamTest {

    private static List<InnerClass> list;

    public static void main(String[] args) {
        Map map = getMap();
    }

    public static Map getMap() {

        InnerClass innerClass2 = new InnerClass(2);
        InnerClass innerClass = new InnerClass(1);
        InnerClass innerClass3 = new InnerClass(1);
        list = Lists.newArrayList(innerClass, innerClass2, innerClass3);

        /**
         * Collectors.toMap(p1, p2, (v1,v2) -> v2)
         * p1为 Map的key， p2为 Map的value， 第三个参数是当key值冲突时，保存第一个还是第二个的value
         */
        Map<Integer, InnerClass> collect = list.stream().collect(
            Collectors.toMap(InnerClass::getInner, v -> v, (v1, v2) -> v1));

        System.out.println(collect);
        List<InnerClass> collect1 = list.stream().distinct().collect(Collectors.toList());
        System.out.println(collect1);
        return collect;
    }
}
