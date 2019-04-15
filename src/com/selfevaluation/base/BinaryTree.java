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

    @Getter
    @Setter
    public static class Node
    {
        //Common
        private int data;

        //As per the DS def
        private Node left;
        private Node right;

        public Node(int data)
        {
          this.data = data;

          //all pointers are always initialized to null
          this.left = null;
          this.right = null;
        }
    }
}
