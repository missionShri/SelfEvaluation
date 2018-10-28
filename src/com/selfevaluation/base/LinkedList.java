package com.selfevaluation.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkedList {

    //Global pointer
    private Node head;
    //For testing
    private int size = 0;

    @Setter
    @Getter
    public static class Node {

        //Common
        private int data;

        //As per the DS def
        private Node next;


        public Node(int data) {
            this.data = data;

            //all pointers are always initialized to null
            this.next = null;
        }
    }

//    public static void main()
//    {
//        LinkedList linkedList = new LinkedList();
//        linkedList.head =  new Node(0);
//        Node node1 = new Node(1);
//        linkedList.head.next = node1;
//        printList(linkedList);
//    }


    private static void printList(LinkedList linkedList)
    {
        Node n = linkedList.head;
        while (n != null)
       {
            System.out.println(n.data+"-->");
            n = n.next;
        }
    }
}
