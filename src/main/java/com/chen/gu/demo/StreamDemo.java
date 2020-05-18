package com.chen.gu.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.formula.functions.T;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author wb-gc
 * @version 1.0
 * @date 2019/11/4 10:25 上午
 */
public class StreamDemo {

    public static void main(String[] args) {

        ArrayList<Long> arrayList = Lists.newArrayList();
        arrayList.sort(Long::compareTo);



        List<She> streamList = Lists.newArrayList();
        //streamList.add(null);   stream中只有少量API支持空指针，返回值为容器类型Optional
        streamList.add(new She(5, "第一个", Boolean.TRUE));
        streamList.add(new She(2, "第二个", Boolean.FALSE));
        streamList.add(new She(3, "第三个", Boolean.TRUE));
        streamList.add(new She(4, "第四个", Boolean.FALSE));
        streamList.add(new She(5, "第五个", Boolean.TRUE));
        streamList.add(new She(1, "第六个呀", Boolean.TRUE));

        /**
         *   all match 全部match则为true
         */
        boolean b = streamList.stream().distinct().allMatch(she -> she.sex);
        boolean b1 = streamList.stream().anyMatch(she -> she.name.length() > 3);
        boolean b2 = streamList.stream().noneMatch(she -> she.name != null);
        //异步的stream流， 如果没有线程安全等问题，可以用异步流来快速操作
        List<She> collect = streamList.parallelStream().peek(System.out::println).collect(Collectors.toList());
        List<String> list = Lists.newArrayList();
        //findAny()只要存在一个即可       findFirst()返回第一个
        Optional<She> any = streamList.stream().peek(she -> list.add(she.getName())).findAny();
        Optional<She> an1y = streamList.stream().peek(she -> list.add(she.getName())).findFirst();
        Optional<List<She>> reduce = Stream.of(streamList).reduce((a, c) -> {
            c.addAll(a);
            return c;
        });
        Object[] objects = streamList.stream().sorted(Comparator.comparingInt(She::getAge)).toArray();
        System.out.println();

        She a333 = new She();
        a333.setAge(1);
        String name = a333.getName();
        boolean aNull = Objects.isNull(name);
        String str = "[0-9]*";
        Pattern pattern = Pattern.compile(str);
        Matcher ddddd = pattern.matcher("ddddd");
        boolean matches = ddddd.matches();
        Matcher matcher = pattern.matcher("123545");
        matches =  matcher.matches();


        new She().setAge(1).setAge(2).setName("我是什么");
    }
}

@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
class She {
    Integer age;
    String name;
    Boolean sex;

}
