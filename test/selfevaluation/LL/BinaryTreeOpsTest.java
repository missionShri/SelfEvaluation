package selfevaluation.LL;

import com.selfevaluation.LL.BinaryTreeOps;
import com.selfevaluation.LL.DoubleLinkedListOps;
import com.selfevaluation.base.BinaryTree;

import com.selfevaluation.base.BinaryTree.Node;
import com.selfevaluation.base.DoubleLinkedList;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class BinaryTreeOpsTest {

    BinaryTreeOps binaryTreeOps;

    @BeforeMethod
    public void setup()
    {
        binaryTreeOps = new BinaryTreeOps(new BinaryTree());
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
        binaryTreeOps.getBinaryTree().setRoot(new Node(10));

        //When ..
        Node root = binaryTreeOps.getBinaryTree().getRoot();
        root.setRight(new Node(15));
        root.getRight().setLeft(new Node(36));

        root.setLeft(new Node(12));
        root.getLeft().setLeft(new Node(25));
        root.getLeft().setRight(new Node(30));

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
        binaryTreeOps.getBinaryTree().setRoot(new Node(10));

        //When ..
        Node root = binaryTreeOps.getBinaryTree().getRoot();
        root.setLeft(new Node(5));
        root.setRight(new Node(36));

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
    public void printTest()
    {
        binaryTreeOps.insertInBST(binaryTreeOps.getBinaryTree().getRoot(),2);
        binaryTreeOps.insertInBST(binaryTreeOps.getBinaryTree().getRoot(),1);

        binaryTreeOps.insertInBST(binaryTreeOps.getBinaryTree().getRoot(),3);
        binaryTreeOps.printTree(binaryTreeOps.getBinaryTree().getRoot());
        System.out.println();
    }


}
