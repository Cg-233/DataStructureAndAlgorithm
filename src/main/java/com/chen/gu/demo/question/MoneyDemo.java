package com.chen.gu.demo.question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author wb-gc
 * @version 1.0
 * @date 2020/5/12 19:15
 */
public class MoneyDemo {
    public static void main(String[] args) throws InterruptedException {
        Double aDouble = parseMoney(3.44);
        Double bDouble = parseMoney(3.99);
        Double cDouble = parseMoney(2.00);
        Double dDouble = parseMoney(3.49);
        boolean notBlank = StringUtils.isNotBlank(null);

        boolean abba = wordPattern("abba", "北京 杭州 杭州 北京");
        boolean baba = wordPattern("baba", "北京 杭州 杭州 北京");
        boolean baab = wordPattern("baab", "北京 杭州 杭州 北京");
        boolean aaaa = wordPattern("aaaa", "北京 杭州 杭州 北京");

        Long reverse = reverse(123455L);
        Long reverse1 = reverse(34556788L);
        Long reverse2 = reverse(9865L);

        print("ss");
        System.out.println("111");
    }

    private static void print(String string) throws InterruptedException {
        String[] chars = new String[100];
        AtomicInteger index = new AtomicInteger(0);
        Thread t1 = new Thread(new Runnerabler(index, chars, "A", null));
        Thread t2 = new Thread(new Runnerabler(index, chars, "L", t1));
        Thread t3 = new Thread(new Runnerabler(index, chars, "i", t2));

        for (int i = 0; i < 1; i++) {
            t1.start();
            t2.start();
            t3.start();

            //while (!t3.isAlive()){
            //    System.out.println(11);
           // }
            //t1.interrupt();
            //t2.interrupt();
            //t3.interrupt();
        }


        List list = Arrays.asList(chars);
        System.out.println(list);

    }

    static class Runnerabler implements Runnable{

        AtomicInteger index;
        String[] chars;
        String str;
        Thread thread;

        Runnerabler(AtomicInteger index, String[] chars, String str, Thread thread) {
            this.index = index;
            this.chars = chars;
            this.str = str;
            this.thread = thread;
        }
        @SneakyThrows
        @Override
        public void run() {
            if (Objects.nonNull(thread)){
                thread.join();
            }
            chars[index.get()] = str;
            index.incrementAndGet();
        }
    }

    public static Long reverse(Long bi) {
        char[] chars = bi.toString().toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = chars.length - 1; i >= 0; i--) {
            stringBuffer.append(String.valueOf(chars[i]));
        }
        String toString = stringBuffer.toString();
        Long aLong = Long.valueOf(toString);
        return aLong;
    }

    private static boolean wordPattern(String pattern,String str) {
        String[] s = str.split(" ");
        char[] chars = pattern.toCharArray();

        boolean isPattern = true;
        HashMap<String, String> hashMap = Maps.newHashMap();
        HashMap<String, String> patternHashMap = Maps.newHashMap();
        for (int i = 0; i < s.length; i++) {
            String s1 = s[i];
            String s2 = hashMap.get(s1);
            if (Objects.nonNull(s2)){
                hashMap.put(s1, s2.concat(String.valueOf(i)));
            } else {
                hashMap.put(s1, String.valueOf(i));
            }
        }
        for (int i = 0; i < chars.length; i++) {
            String s1 = String.valueOf(chars[i]);

            String s2 = patternHashMap.get(s1);
            if (Objects.nonNull(s2)){
                patternHashMap.put(s1, s2.concat(String.valueOf(i)));
            } else {
                patternHashMap.put(s1, String.valueOf(i));
            }
        }

        for (int i = 0; i < s.length; i++) {
            boolean equals = hashMap.get(s[i]).equals(patternHashMap.get(String.valueOf(chars[i])));
            if (!equals){
                isPattern = false;
            }
        }

        return isPattern;

    }


    private static Double parseMoney(Double money){
        long l = money.longValue();
        double pre = l - 0.01;
        double next = l + 0.49;
        if (money >= next){
            next = l + 0.99;
        }
        if (money - pre >= next - money){
            return next;
        } else {
            return pre;
        }
    }


    /**
     * 过滤不可用支付方式类型
     * @return 可用支付方式类型列表
     */
    public List<String> filterDisablePayment(List<String> allPaymentList) {
        //TODO 写出代码实现

        List<String> validPaymentList = Lists.newArrayList();
        for (String payment : allPaymentList) {
            ConsultResult enabled = isEnabled(payment);
            if (enabled.isEnable()){
                validPaymentList.add(payment);
            }
        }
        return validPaymentList;
    }
    ConsultResult isEnabled(String paymentType){
        return null;
    }
}


@Data
class ConsultResult {
    /** 咨询结果是否可用*/
    private boolean isEnable;
    /** 错误码 */
    private String errorCode;
}

