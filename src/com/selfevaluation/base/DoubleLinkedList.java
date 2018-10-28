package com.selfevaluation.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoubleLinkedList {

    //Global pointer
    private Node head;
    //For testing
    private int size = 0;

    @Getter
    @Setter
    public static class Node {

        //Common
        private int data;

        //As per the DS def
        private Node next;
        private Node previous;

        public Node(int data)
        {
            this.data = data;

            //all pointers are always initialized to null
            this.next = null;
            this.previous = null;
        }
    }

}
