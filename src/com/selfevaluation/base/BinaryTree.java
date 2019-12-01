package com.selfevaluation.base;

import lombok.Getter;
import lombok.Setter;

//https://www.byte-by-byte.com/recursion/

@Getter
@Setter
public class BinaryTree {

    //Global pointer
    Node root;
    //For testing
    int size = 0;
    int depth = 0;

    //TODO: See if you can use lombok annotations instead
    public BinaryTree()
    {
        this.root = null;
        this.size = 0;
        this.depth = 0;
    }

    @Getter
    @Setter
    public static class Node<T extends Number>
    {
        //TODO:Make this generic...what are the repercussions of it across ops and tests ?
        //Common
        private T data;

        //As per the DS def
        private Node left;
        private Node right;

        //public Node(int data)
        public Node(T data)
        {
          this.data = data;

          //all pointers are always initialized to null
          this.left = null;
          this.right = null;
        }
    }
}
