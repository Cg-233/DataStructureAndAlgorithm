package com.chen.gu.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wb-gc
 * @version 1.0
 * @date 2019/9/17 12:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTestVO {
    int age;
    int num;
    String color;
    String name;
    UserTestVO userTestVO;
}
