package com.selfevaluation.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stack {

    //global pointer
    private Stack.Node top;
    //for testing
    private int size;

    @Getter
    @Setter
    public static class Node {

        //Common
        private int data;
        //As per the DS def
        private Node next; //should actually be more like prev

        public Node(int data)
        {
            this.data = data;

            //all pointers are always initialized to null
            this.next = null;
        }

    }
}
