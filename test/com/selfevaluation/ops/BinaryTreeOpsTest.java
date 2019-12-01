package com.selfevaluation.ops;

import com.selfevaluation.ops.BinaryTreeOps;
import com.selfevaluation.ops.DoubleLinkedListOps;
import com.selfevaluation.ops.queue.QueueOps;
import com.selfevaluation.base.BinaryTree;

import com.selfevaluation.base.BinaryTree.Node;
import com.selfevaluation.base.DoubleLinkedList;
import com.selfevaluation.base.Queue;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BinaryTreeOpsTest {

    BinaryTreeOps binaryTreeOps;

    @BeforeMethod
    public void setup()
    {
        binaryTreeOps = new BinaryTreeOps();
    }

    //empty node
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenInsertEmptyNodeThenThrowException()
    {
        binaryTreeOps.insertInBST(null,-1);
    }

    //empty tree
    @Test
    public void whenInsertInBSTNodeInEmptyTreeThenCreateTree()
    {
        binaryTreeOps.insertInBST(null,1);
        binaryTreeOps.printTree(binaryTreeOps.getBinaryTree().getRoot());
        System.out.println();
        Assert.assertEquals(binaryTreeOps.getBinaryTree().getSize(),1);
    }

    //single element tree
    @Test
    public void whenInsertInBSTNodeInSingleElementTreeThenAddNode()
    {
        //Given
        binaryTreeOps.insertInBST(null,1);

        //When
        binaryTreeOps.insertInBST(binaryTreeOps.getBinaryTree().getRoot(),2);


        //Then
        binaryTreeOps.printTree(binaryTreeOps.getBinaryTree().getRoot());
        System.out.println();
        Assert.assertEquals(binaryTreeOps.getBinaryTree().getSize(),2);
    }

    //multi node
    @Test
    public void whenInsertInBSTNodeInMultiElementTreeThenAddNodes()
    {
        //Given
        binaryTreeOps.insertInBST(null,3);

        //When
        binaryTreeOps.insertInBST(binaryTreeOps.getBinaryTree().getRoot(),2);
        binaryTreeOps.insertInBST(binaryTreeOps.getBinaryTree().getRoot(),4);

        binaryTreeOps.insertInBST(binaryTreeOps.getBinaryTree().getRoot(),1);

        //Then
        binaryTreeOps.printTree(binaryTreeOps.getBinaryTree().getRoot());

        System.out.println("Inorder traversal");
        binaryTreeOps.printInorderTraversal(binaryTreeOps.getBinaryTree().getRoot());
        Assert.assertEquals(binaryTreeOps.getBinaryTree().getSize(),4);
    }

    // #########

    //empty tree
    @Test
    public void whenInorderEmptyTreeThenPrintTree()
    {
        binaryTreeOps.insertInBST(null,1);
        binaryTreeOps.printInorderTraversal(binaryTreeOps.getBinaryTree().getRoot());
        System.out.println();
        Assert.assertEquals(binaryTreeOps.getBinaryTree().getSize(),1);
    }

    //single element tree
    @Test
    public void whenInorderInSingleElementTreeThenAddNode()
    {
        //Given
        binaryTreeOps.insertInBST(null,1);

        //When
        binaryTreeOps.insertInBST(binaryTreeOps.getBinaryTree().getRoot(),2);


        //Then
        binaryTreeOps.printInorderTraversal(binaryTreeOps.getBinaryTree().getRoot());
        System.out.println();
        Assert.assertEquals(binaryTreeOps.getBinaryTree().getSize(),2);
    }

    //multi node
    @Test
    public void whenInorderInMultiElementTreeThenAddNodes()
    {
        //Given
        binaryTreeOps.insertInBST(null,3);

        //When
        binaryTreeOps.insertInBST(binaryTreeOps.getBinaryTree().getRoot(),2);
        binaryTreeOps.insertInBST(binaryTreeOps.getBinaryTree().getRoot(),4);

        binaryTreeOps.insertInBST(binaryTreeOps.getBinaryTree().getRoot(),1);

        //Then
        binaryTreeOps.printTree(binaryTreeOps.getBinaryTree().getRoot());

        System.out.println("Inorder traversal");
        binaryTreeOps.printInorderTraversal(binaryTreeOps.getBinaryTree().getRoot());
        Assert.assertEquals(binaryTreeOps.getBinaryTree().getSize(),4);
    }

    //multi node
    @Test
    public void whenInorderInBinaryTreeThenPrintTree()
    {
        //Given
        binaryTreeOps.getBinaryTree().setRoot(new Node<>(10));

        //When ..
        Node root = binaryTreeOps.getBinaryTree().getRoot();
        root.setRight(new Node<>(15));
        root.getRight().setLeft(new Node<>(36));

        root.setLeft(new Node<>(12));
        root.getLeft().setLeft(new Node<>(25));
        root.getLeft().setRight(new Node<>(30));

        //Then
        binaryTreeOps.printTree(binaryTreeOps.getBinaryTree().getRoot());

        System.out.println("Inorder traversal");
        binaryTreeOps.printInorderTraversal(binaryTreeOps.getBinaryTree().getRoot());
    }

    //multi node
    @Test
    public void whenConvertBinaryTreeToDLLThenTransform()
    {
        //Given
        binaryTreeOps.getBinaryTree().setRoot(new Node<>(10));

        //When ..
        Node root = binaryTreeOps.getBinaryTree().getRoot();
        root.setLeft(new Node<>(5));
        root.setRight(new Node<>(36));

        /* as from https://www.geeksforgeeks.org/convert-a-binary-tree-to-a-circular-doubly-link-list/
        root.getRight().setLeft(new Node(36));
        root.setLeft(new Node(12));
        root.getLeft().setLeft(new Node(25));
        root.getLeft().setRight(new Node(30));*/

        //Then
        binaryTreeOps.printTree(binaryTreeOps.getBinaryTree().getRoot());
        System.out.println();

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        DoubleLinkedListOps doubleLinkedListOps = new DoubleLinkedListOps(doubleLinkedList);
        //binaryTreeOps.convertBTreeToCDLL(binaryTreeOps.getBinaryTree().getRoot(),null, doubleLinkedList,new HashMap<>(), new HashMap<>());

        binaryTreeOps.createListFromTreeWithExtraSpace(binaryTreeOps.getBinaryTree().getRoot(),doubleLinkedListOps);
        System.out.println("DLL");
        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
    }

    // ##########

    @Test
    public void whenNullRootTreeReturnNullQueuOps()
    {
        QueueOps queueOps = null;
        binaryTreeOps.convertBinaryTreeToQueue(queueOps,null);
        Assert.assertNull(queueOps);
    }

    @Test
    public void whenNullRootNullQueueOpsTreeReturnNullQueuOps()
    {
        QueueOps queueOps = null;
        binaryTreeOps.convertBinaryTreeToQueue(queueOps,null);
        Assert.assertNull(queueOps);
    }

    @Test
    public void whenNonNullRootTreeReturnQueueOps()
    {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(new Node<>(0));
        binaryTree.getRoot().setLeft((new Node<>(1)));
        binaryTree.getRoot().setRight((new Node<>(2)));

        binaryTree.getRoot().getLeft().setRight((new Node<>(3)));
        binaryTree.getRoot().getRight().setRight((new Node<>(5)));
        QueueOps queueOps = new QueueOps(new Queue());
        binaryTreeOps.convertBinaryTreeToQueue(queueOps, binaryTree.getRoot());

        Assert.assertEquals(queueOps.getQueue().getSize(),5);

        do {
            System.out.print(queueOps.dequeue().getData() + "\t");
        }while (queueOps.getQueue().getSize()!=0);
    }


    // #########
    @Test
    public void printTest()
    {
        binaryTreeOps.insertInBST(binaryTreeOps.getBinaryTree().getRoot(),2);
        binaryTreeOps.insertInBST(binaryTreeOps.getBinaryTree().getRoot(),1);

        binaryTreeOps.insertInBST(binaryTreeOps.getBinaryTree().getRoot(),3);
        binaryTreeOps.printTree(binaryTreeOps.getBinaryTree().getRoot());
        System.out.println();
    }


}
