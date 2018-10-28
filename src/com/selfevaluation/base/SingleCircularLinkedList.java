package com.selfevaluation.base;

import lombok.Getter;
import lombok.Setter;

//Feels so wierd designing the new data-structure
@Getter
@Setter
public class SingleCircularLinkedList {

    //Global pointer
    private  Node last;
    //for testing purposes
    private int size;


    @Getter
    @Setter
    public static class Node
    {
        //Common
        private int data;

        //As per the DS def
        private Node next;

        public Node(int data)
        {
            this.data = data;

            //all pointers are always initialized to null
            this.next = null;
            //Keeping the structure same as single linked list
            //this.next = next;
        }
    }
}
