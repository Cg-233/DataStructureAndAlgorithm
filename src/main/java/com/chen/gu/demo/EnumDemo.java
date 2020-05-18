package com.chen.gu.demo;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author wb-gc
 * @version 1.0
 * @date 2019/9/24 16:04
 */
public class EnumDemo {
    public enum ColorEnum{
        red, green, yellow, blue
    }
    /**
     * 枚举像普通的类一样可以添加属性和方法，可以为它添加静态和非静态的属性和方法
     */
    public enum SeasonEnum{
        Spring, Summer, Autumn, Winter;

        private final static String position = "test";
        public static SeasonEnum getSeason(){
            if ("test".equals(position)){
                return Spring;
            }else {
                return Winter;
            }
        }
    }

    /**
     * 实现带有构造器的枚举
     */
    public enum Gender{
        //通过括号赋值，而且必须带一个有参构造器和一个属性跟方法，否则会编译出错
        // 赋值必须都赋值或都不赋值，不能一部分赋值，一部分不赋值，如果不赋值就不能写构造器，赋值编译也出错
        MAN("MAN"),WOMEN("WOMEN");

        private final String value;

        //构造器默认也只能是private，从而保证构造函数只能在内部使用
        Gender(String value){
            this.value = value;
        }

        public String getValue(){
            return value;
        }
    }

    /**
     * 订单状态
     * 实现带有抽象方法的枚举
     */
    public enum OrderState{
        /** 已取消 */
        CANCEL{
            @Override
            public String getName(){return "已取消";}},
        /** 待审核 */
        WAITCONFIRM{
            @Override
            public String getName(){return "待审核";}},
        /** 等待付款 */
        WAITPAYMENT{
            @Override
            public String getName(){return "等待付款";}},
        /** 正在配货 */
        ADMEASUREPRODUCT{
            @Override
            public String getName(){return "正在配货";}},
        /** 等待发货 */
        WAITDELIVER{
            @Override
            public String getName(){return "等待发货";}},
        /** 已发货 */
        DELIVERED{
            @Override
            public String getName(){return "已发货";}},
        /** 已收货 */
        RECEIVED{
            @Override
            public String getName(){return "已收货";}};

        public abstract String getName();
    }

    public static void main(String[] args) {

        List<Integer> arrayList = Lists.newArrayList();

        arrayList.add(11);
        arrayList.add(22);
        arrayList.add(33);
        arrayList.add(44);

        arrayList.stream().map(v -> {
            v = v + 1;

            System.out.println(v);
            return v;
        });

        //枚举是一种类型，用于定义变量，以限制变量的赋值， 赋值时通过“枚举名，值”取得枚举中的值
        ColorEnum colorEnum = ColorEnum.blue;
        System.out.println(colorEnum);
        System.out.println("遍历ColorEnum枚举中的所有值");
        for (ColorEnum value : ColorEnum.values()) {
            System.out.println(value);
        }
        ColorEnum[] values = ColorEnum.values();
        //获取枚举的个数
        System.out.println("ColorEnum枚举中的值有" + ColorEnum.values().length + "个");

        //获取枚举的索引位置，默认从0开始
        System.out.println(ColorEnum.red.ordinal());
        System.out.println(ColorEnum.yellow.ordinal());
        System.out.println(ColorEnum.green.ordinal());
        System.out.println(ColorEnum.blue.ordinal());

        System.out.println("================================================");
        //枚举默认实现了java.lang.CompareTo接口
        System.out.println(ColorEnum.red.compareTo(ColorEnum.green));

        System.out.println("================================================");
        System.out.println("现在的季节是" + SeasonEnum.getSeason());

        System.out.println("================================================");
        for (Gender gender : Gender.values()) {
            System.out.println(gender.value);
            System.out.println(gender.getValue());
        }

        System.out.println("================================================");
        for (OrderState order : OrderState.values()) {
            System.out.println(order);
            System.out.println(order.getName());
        }
    }
}
