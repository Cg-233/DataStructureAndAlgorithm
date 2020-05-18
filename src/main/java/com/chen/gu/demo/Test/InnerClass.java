package com.chen.gu.demo.Test;

import com.chen.gu.demo.Test.InnerClass.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wb-gc
 * @version 1.0
 * @date 2019/9/12 9:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InnerClass {

    int inner;

    static class Car{
        int color;
        int num;

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }


}
class Demo{
    public static void main(String[] args) {
        Car car = new Car();


    }
}
