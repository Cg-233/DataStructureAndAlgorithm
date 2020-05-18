package com.chen.gu.demo.Test;

import com.chen.gu.demo.Test.InnerClass.Car;
import com.google.common.collect.Lists;
import org.junit.Test;

/**
 * @author wb-gc
 * @version 1.0
 * @date 2019/9/12 9:58
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastJsonTools {
    private FastJsonTools() {
    }

    public static <T> String serializeWithClz(T object) {
        return JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss", new SerializerFeature[]{SerializerFeature.WriteClassName});
    }

    public static <T> T deserializeWithClz(String jsonStr) {
        return JSON.parseObject(jsonStr, (Type)Object.class);
    }

    public static <T> String serialize(T object) {
        return JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss", new SerializerFeature[0]);
    }

    public static <T> T deserialize(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }

    public static void main(String[] args) throws Exception {
        Map<String, List<String>> kv = new HashMap();
        kv.put("k1", Arrays.asList("v1"));
        kv.put("k2", Arrays.asList("v2"));
        String json = serializeWithClz(kv);
        System.out.println(json);
        Map<String, List<String>> kv2 = (Map)deserializeWithClz(json);
        System.out.println(kv2);
        json = serialize(kv);
        System.out.println(json);
        Map<String, List<String>> kv3 = (Map)deserialize(json, Map.class);
        System.out.println(kv3);
        FastJsonTools.A a = new FastJsonTools.A();
        a.f1 = "f1";
        a.f2 = "f2";
        a.d1 = new Date();
        json = serializeWithClz(a);
        System.out.println(json);
        //A deserialize = deserialize(json, A.class);
        //System.out.println(deserialize);
        FastJsonTools.A a2 = (FastJsonTools.A)deserializeWithClz(json);
        System.out.println(a2.getClass());
        System.out.println(a2);
    }

    public static class A {
        String f1;
        String f2;
        Date d1;

        public A() {
        }

        public Date getD1() {
            return this.d1;
        }

        public void setD1(Date d1) {
            this.d1 = d1;
        }

        public String getF1() {
            return this.f1;
        }

        public void setF1(String f1) {
            this.f1 = f1;
        }

        public String getF2() {
            return this.f2;
        }

        public void setF2(String f2) {
            this.f2 = f2;
        }
    }


}
