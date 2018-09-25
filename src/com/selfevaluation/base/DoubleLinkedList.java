package com.selfevaluation.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoubleLinkedList {

    private Node head;
    private int size = 0;

    @Getter
    @Setter
    public static class Node {

        private Node next;
        private Node previous;
        private int data;

        public Node(int data)
        {
            this.data = data;
            this.next = null;
            this.previous = null;
        }

    }

}
