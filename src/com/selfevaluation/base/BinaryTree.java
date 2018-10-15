package com.selfevaluation.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BinaryTree {
    Node root;
    int size = 0;
    int depth = 0;

    @Getter
    @Setter
    public static class Node
    {
        private  int data;
        private Node left;
        private Node right;

        public Node(int data)
        {
          this.data = data;
          this.left = null;
          this.right = null;
        }
    }
}
