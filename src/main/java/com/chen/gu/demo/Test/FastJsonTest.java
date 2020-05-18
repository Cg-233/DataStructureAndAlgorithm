package com.chen.gu.demo.Test;

import java.lang.reflect.Array;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.Lists;
import org.junit.Test;

/**
 * @author wb-gc
 * @version 1.0
 * @date 2019/9/20 11:24
 */
public class FastJsonTest {
    @Test
    public void testJson(){
        List<Long> longs = Lists.newArrayList(1L, 4L, 5L);
        List<String> lists = Lists.newArrayList("dfasdf", "sdfsdf", "sdfasdf");
        String serialize = FastJsonTools.serialize(lists);
        List deserialize = FastJsonTools.deserialize(serialize, List.class);
        List<String> list = Lists.newArrayList();
        for (Object o : deserialize) {
            list.add((String)o);
        }
        List<Long> longs1 = JSONObject.parseArray(serialize, Long.TYPE);
        System.out.println(list);
        System.out.println(deserialize);
        System.out.println(serialize);
    }
    @Test
    public void test(){
    }
}
