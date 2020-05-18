package com.chen.gu.demo.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.chen.gu.demo.pojo.UserTestVO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author wb-gc
 * @version 1.0
 * @date 2019/9/17 11:08
 */
public class NullAndEmptyTest {

    public static void main(String[] args) {
        List<String> list = null;
        String a = null;
        boolean empty = StringUtils.isEmpty(a);
        boolean empty1 = CollectionUtils.isEmpty(list);
        a = "";
        list = new ArrayList<String>();
        boolean empty2 = StringUtils.isEmpty(a);
        boolean empty3 = CollectionUtils.isEmpty(list);
        InnerClass innerClass = null;
        System.out.println(innerClass);
        innerClass = new InnerClass();
        System.out.println(innerClass);
        innerClass.setInner(1);
        System.out.println(innerClass);
    }
    @Test
    public void whenCreateEmptyOptional_thenNull() {
        UserTestVO userTestVO = new UserTestVO();
        UserTestVO userTestVO1 = null;
        Optional<UserTestVO> emptyOpt = Optional.empty();
        Optional<UserTestVO> userOptional = Optional.of(userTestVO);
        userOptional.get();
        Optional<UserTestVO> user2 = Optional.ofNullable(userTestVO1);
        Optional<UserTestVO> user3 = Optional.ofNullable(userTestVO);
        userTestVO.setName("abcdefg");
        //user2.get();
        //emptyOpt.get();
        UserTestVO userTestVO4 = emptyOpt.orElse(null);
        UserTestVO userTestVO5 = emptyOpt.orElse(userTestVO);
        //如果没有值，就按照相应的函数进行生成相应的对象返回
        UserTestVO userTestVO6 = emptyOpt.orElseGet(() -> {
            return new UserTestVO();
        });
        //如果存在就对他做点什么
        userOptional.ifPresent(System.out::println);
        // 需要进行map操作的，可以对元数据进行改变后继续流式编程
        String s = userOptional.map(u -> u.getName())
            .map(name -> name.toUpperCase())
            .orElse(null);

        userOptional.filter(userTestVO7 -> !userTestVO7.getName().isEmpty()).ifPresent(
            e -> System.out.println(e.getName()));
        Optional<UserTestVO> userOptional1 = userOptional.filter(userTestVO7 -> !userTestVO7.getName().isEmpty());
        Optional<UserTestVO> userOptional2 = userOptional.filter(userTestVO7 -> userTestVO7.getName() == "");
        Optional<UserTestVO> user7 = Optional.ofNullable(userTestVO);
        UserTestVO userTestVO8 = Optional.ofNullable(userTestVO).orElse(null);
        userTestVO8 = Optional.ofNullable(userTestVO).orElseGet(UserTestVO::new);

    }

    /**
     * 可以将不同业务选择不同方法的处理的策略模式，放入到map中，map的值为方法体，get().apply()
     */
    @Test
    public void lambda(){
        HashMap<Integer, Function<Integer, UserTestVO>> hashMap = Maps.newHashMap();
        hashMap.put(1,integer -> {
            UserTestVO userTestVO = new UserTestVO();
            userTestVO.setName("aaa");
            userTestVO.setAge(16);
            System.out.println(integer);
            System.out.println(userTestVO);
            return userTestVO;
        });
        hashMap.put(2,e -> {
            UserTestVO userTestVO = new UserTestVO();
            userTestVO.setAge(e);
            userTestVO.setNum(e);
            System.out.println(userTestVO);
            return userTestVO;
        });
        UserTestVO apply = hashMap.get(1).apply(333);
        hashMap.get(2).apply(123456789);
        System.out.println(111);
    }

    @Test
    public void objectChange(){
        UserTestVO userTestVO = new UserTestVO();
        userTestVO.setName("aaa");
        ArrayList<UserTestVO> userTestVOS = Lists.newArrayList(userTestVO);

        userTestVO.setName("bbb");
        userTestVOS.get(0).setName("ccc");
        UserTestVO userTestVO1 = new UserTestVO();
        userTestVO1.setName("rrr");
        userTestVO.setUserTestVO(userTestVO1);
        System.out.println(userTestVOS);
        userTestVO1.setName("ttt");
        String a = "111";
        ArrayList<String> strings = Lists.newArrayList(a);
        a = "ddd";
        System.out.println(strings);
        Integer b = 111;
        int c = 222;
        ArrayList<Integer> integers = Lists.newArrayList(b);
        ArrayList<Integer> integers1 = Lists.newArrayList(c);
        b = 333;
        c = 444;
    }
}
