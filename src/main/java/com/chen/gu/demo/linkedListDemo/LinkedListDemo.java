package com.chen.gu.demo.linkedListDemo;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.chen.gu.demo.pojo.User;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

/**
 * @author wb-gc
 * @version 1.0
 * @date 2020/4/21 20:12
 */
public class LinkedListDemo {

    public static void main(String[] args) {

        String a = "a,b,c,,,,";
        String a2 = ",,,a,b,c,,,,";
        String a3 = ",,,a,b,,,,c,,,,";
        String[] split = a.split(",");
        String[] split1 = a2.split(",");
        String[] split2 = a3.split(",");

        User user = new User(1);
        User user1 = new User(1);

        modify(user, user1);

        HashMap<Object, Object> map = new HashMap<>();

        List list = Lists.newArrayList();
        list.add(null);
        //ddddd


        boolean empty = CollectionUtils.isEmpty(list);
        System.out.println(list.toString());

        //ddd
        Node node = new Node();
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        node.setValue(0);
        node1.setValue(1);
        node2.setValue(2);
        node3.setValue(3);
        node.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);

        //Node node4 = linkedListReversion(node);

        System.out.println(node);
    }

    private static void modify(User user, User user1) {
        user.setAge(2);

        user1 = new User(3);
        user1.setAge(4);
    }

    private static Node linkedListReversion(Node node){

        Node pointer = null;
        Node current = node;
        Node next = node.getNext();
        pointer = node;
        while (next != null){
            pointer = next;
            current = next;
            next = next.getNext();
            node.getNext().setNext(pointer);

            if (node.getNext() != null){
                pointer.setNext(node.getNext());
            }
        }
        node = pointer;
        return node;
    }
}
