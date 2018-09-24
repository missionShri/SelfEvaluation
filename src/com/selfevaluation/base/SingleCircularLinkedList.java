package com.selfevaluation.base;

import lombok.Getter;
import lombok.Setter;

//Feels so wierd designing the new data-structure
@Getter
@Setter
public class SingleCircularLinkedList {

    private  Node last;
    //for testing purposes
    private int size;


    @Getter
    @Setter
    public static class Node
    {
        private int data;
        private Node next;

        public Node(int data)
        {
            this.data = data;
            this.next = null;
            //Keeping the structure same as single linked list
            //this.next = next;
        }
    }
}
