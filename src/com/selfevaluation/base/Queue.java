package com.selfevaluation.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Queue {

    //global pointer
    private Queue.Node front;
    //global pointer
    private Queue.Node rear;
    //for testing
    private int size;

    @Getter
    @Setter
    public static class Node {
        //Common
        private int data;
        //As per the DS def
        private Queue.Node next;

        public Node(int data)
        {
            this.data = data;

            //all pointers are always initialized to null
            this.next = null;
        }
    }
}