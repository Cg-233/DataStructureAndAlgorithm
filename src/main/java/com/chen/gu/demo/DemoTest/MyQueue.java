package com.chen.gu.demo.DemoTest;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wb-gc
 * @version 1.0
 * @date 2019/9/11 20:42
 */
@Data
@AllArgsConstructor
public class MyQueue {

    private List<Integer> data;

    private int p_start;

    private String a;

    public MyQueue (){
        data = new ArrayList<Integer>();
        p_start = 0;
    }

    public boolean enQueue(int x){
        data.add(x);
        return true;
    }

    public boolean deQueue(){
        if (isEmpty() == true){
            return false;
        }
        p_start++;
        return true;
    }


    public int Front(){
        return data.get(p_start);
    }

    private boolean isEmpty() {
        return p_start >= data.size();
    }
}

class Main{
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.enQueue(5);
        myQueue.enQueue(3);
        myQueue.enQueue(6);
        myQueue.deQueue();
        myQueue.Front();
    }
}
