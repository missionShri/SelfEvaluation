package com.selfevaluation.ops;

import com.selfevaluation.ops.SingleCircularListOps;
import com.selfevaluation.base.SingleCircularLinkedList;
import com.selfevaluation.base.SingleCircularLinkedList.Node;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SingleCircularListOpsTest {

    SingleCircularListOps singleCircularListOps;
    Node node0;

    @BeforeMethod
    public void setUp()
    {
        singleCircularListOps = new SingleCircularListOps(new SingleCircularLinkedList());
        node0 =  new Node(0);
    }

    //Insert at start
    //empty
    @Test
    public void whenInsertAtEndInEmptyListThenAddNode()
    {
        singleCircularListOps.setSingleCircularLinkedList(null);
        singleCircularListOps.insertAtEnd(node0);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        Assert.assertEquals(1, singleCircularListOps.getSingleCircularLinkedList().getSize());
    }
    //single
    @Test
    public void  whenInsertAtEndInSingleListThenAddNode()
    {
        singleCircularListOps.insertAtEnd(node0);
        singleCircularListOps.insertAtEnd(new Node(1));

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        Assert.assertEquals(2, singleCircularListOps.getSingleCircularLinkedList().getSize());
    }
    //multi
    @Test
    public void  whenInsertAtEndInMultiListThenAddNode()
    {
        singleCircularListOps.insertAtEnd(node0);
        singleCircularListOps.insertAtEnd(new Node(1));
        singleCircularListOps.insertAtEnd(new Node(2));
        singleCircularListOps.insertAtEnd(new Node(1));

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        Assert.assertEquals(4, singleCircularListOps.getSingleCircularLinkedList().getSize());
    }

    //empty
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenInsertAtStartInListThenAddNode()
    {
        singleCircularListOps.insertAtEnd(node0);
        singleCircularListOps.insertAtStart(null);
        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
    }

    //Insert at start
    //empty
    @Test
    public void whenInsertAtStartInEmptyListThenAddNode()
    {
        singleCircularListOps.setSingleCircularLinkedList(null);
        singleCircularListOps.insertAtStart(node0);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        Assert.assertEquals(1, singleCircularListOps.getSingleCircularLinkedList().getSize());
    }

    //single
    @Test
    public void  whenInsertAtStartInSingleListThenAddNode()
    {
        singleCircularListOps.insertAtEnd(node0);
        singleCircularListOps.insertAtStart(new Node(1));

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        Assert.assertEquals(2, singleCircularListOps.getSingleCircularLinkedList().getSize());
    }

    //multi
    @Test
    public void  whenInsertAtStartInMultiListThenAddNode()
    {
        singleCircularListOps.insertAtStart(node0);
        singleCircularListOps.insertAtStart(new Node(1));
        singleCircularListOps.insertAtStart(new Node(2));
        singleCircularListOps.insertAtStart(new Node(1));

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        Assert.assertEquals(4, singleCircularListOps.getSingleCircularLinkedList().getSize());
    }

    // ################## ################## ##################

    //Insert at start
    //empty
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenInvalidInsertionAfterNodeInEmptyListThenThrowException()
    {
        singleCircularListOps.setSingleCircularLinkedList(null);
        singleCircularListOps.insertAfterNode(new Node(-1),0);
    }

    //empty
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenInsertAfterNodeInEmptyListThenAddThenThrowException()
    {
        singleCircularListOps.setSingleCircularLinkedList(null);
        singleCircularListOps.insertAfterNode(new Node(1),0);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        Assert.assertEquals(2, singleCircularListOps.getSingleCircularLinkedList().getSize());
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void whenInsertAfterNonExistantNodeThenThrowException()
    {
        singleCircularListOps.insertAtEnd(node0);
        singleCircularListOps.insertAfterNode(new Node(1),2);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
    }

    @Test()
    public void whenInsertAfterSingleNodeThenAddNode()
    {
        singleCircularListOps.insertAtEnd(new Node(8));
        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        singleCircularListOps.insertAfterNode(new Node(1),8);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        Assert.assertEquals(2, singleCircularListOps.getSingleCircularLinkedList().getSize());

    }

    //multi
    @Test
    public void  whenInsertAfterNodeThenAddNode()
    {
        singleCircularListOps.insertAtStart(node0);
        singleCircularListOps.insertAtStart(new Node(1));

        singleCircularListOps.insertAfterNode(new Node(2), 1);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        Assert.assertEquals(3, singleCircularListOps.getSingleCircularLinkedList().getSize());
    }

    // ################## ################## ##################

    //empty list
    //non -existant node
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenDeleteFromEmptyListThenThrowException()
    {
        singleCircularListOps.insertAtStart(null);
        singleCircularListOps.deleteNode(1);
    }

    //delete a non-existant node
    @Test(expectedExceptions = RuntimeException.class)
    public void whenDeleteNonExistantNodeFromListThenThrowException()
    {
        singleCircularListOps.insertAtStart(node0);
        singleCircularListOps.deleteNode(1);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

    }

    //single-element
    @Test()
    public void whenDeleteNodeFromSingleElementListThenDeleteNode()
    {
        singleCircularListOps.insertAtStart(node0);
        singleCircularListOps.deleteNode(0);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        Assert.assertEquals(0, singleCircularListOps.getSingleCircularLinkedList().getSize());

    }

    //multi node, last element
    @Test()
    public void whenDeleteNodeFromMultiElementListThenDeleteNode()
    {
        singleCircularListOps.insertAtStart(node0);
        singleCircularListOps.insertAtStart(new Node(1));
        singleCircularListOps.insertAtStart(new Node(2));
        singleCircularListOps.insertAtStart(new Node(3));
        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        //left
        singleCircularListOps.deleteNode(0);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        Assert.assertEquals(3, singleCircularListOps.getSingleCircularLinkedList().getSize());

        //right
        singleCircularListOps.deleteNode(3);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        Assert.assertEquals(2, singleCircularListOps.getSingleCircularLinkedList().getSize());

        singleCircularListOps.deleteNode(1);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        Assert.assertEquals(1, singleCircularListOps.getSingleCircularLinkedList().getSize());
    }

    // ################## ################## ##################

    //empty list
    //non -existant node
    @Test(expectedExceptions = RuntimeException.class)
    public void whenDeleteNodeAtPositionFromEmptyListThenThrowException()
    {
        singleCircularListOps.insertAtStart(null);
        singleCircularListOps.deleteNodeAtPosition(1);
    }

    //delete at illegal node
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenDeleteNodeAtIllegalPositionFromListThenThrowException()
    {
        singleCircularListOps.insertAtStart(node0);
        singleCircularListOps.deleteNodeAtPosition(-1);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

    }

    //single-element
    @Test()
    public void whenDeleteNodeAtPositionFromSingleElementListThenDeleteNode()
    {
        singleCircularListOps.insertAtStart(node0);
        singleCircularListOps.deleteNodeAtPosition(0);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        Assert.assertEquals(0, singleCircularListOps.getSingleCircularLinkedList().getSize());

    }

    //multi node, last element
    @Test()
    public void whenDeleteNodeAtPositionFromMultiElementListThenDeleteNode()
    {
        singleCircularListOps.insertAtStart(node0);
        singleCircularListOps.insertAtStart(new Node(1));
        singleCircularListOps.insertAtStart(new Node(2));
        singleCircularListOps.insertAtStart(new Node(3));
        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        //left
        singleCircularListOps.deleteNodeAtPosition(0);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        Assert.assertEquals(3, singleCircularListOps.getSingleCircularLinkedList().getSize());

        //right
        singleCircularListOps.deleteNodeAtPosition(2);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        Assert.assertEquals(2, singleCircularListOps.getSingleCircularLinkedList().getSize());

        singleCircularListOps.deleteNodeAtPosition(1);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        Assert.assertEquals(1, singleCircularListOps.getSingleCircularLinkedList().getSize());
    }

    //delete a non-existant node
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenDeleteNodeAtGreaterThanListsizePositionFromListThenThrowException()
    {
        singleCircularListOps.insertAtStart(node0);
        singleCircularListOps.deleteNodeAtPosition(4);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

    }

    //##########

    //Empty list
    @Test(expectedExceptions = RuntimeException.class)
    public void whenSplitEmptyListThenThrowException()
    {
        singleCircularListOps.getSingleCircularLinkedList().setLast(null);
        singleCircularListOps.splitInMiddle();
    }

    //single element
    @Test()
    public void whenSplitSingleElementListThenReturnList()
    {
        singleCircularListOps.insertAtStart(node0);
        singleCircularListOps.splitInMiddle();

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        Assert.assertEquals(1, singleCircularListOps.getSingleCircularLinkedList().getSize());
    }

    //multi element even-numbered
    @Test()
    public void whenSplitMultiEvenNumberElementListThenReturnList()
    {
        singleCircularListOps.insertAtStart(node0);
        singleCircularListOps.insertAtStart(new Node(1));
        singleCircularListOps.insertAtStart(new Node(2));
        singleCircularListOps.insertAtStart(new Node(3));

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();
        int originalSize = singleCircularListOps.getSingleCircularLinkedList().getSize();

        singleCircularListOps.splitInMiddle();

        Assert.assertEquals(originalSize/2, singleCircularListOps.getSingleCircularLinkedList().getSize());
    }

    //multi element even-numbered
    @Test()
    public void whenSplitMultiOddNumberElementListThenReturnList()
    {
        singleCircularListOps.insertAtStart(node0);
        singleCircularListOps.insertAtStart(new Node(1));
        singleCircularListOps.insertAtStart(new Node(2));
        singleCircularListOps.insertAtStart(new Node(3));
        singleCircularListOps.insertAtStart(new Node(4));

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();
        int originalSize = singleCircularListOps.getSingleCircularLinkedList().getSize();

        singleCircularListOps.splitInMiddle();

        Assert.assertEquals(originalSize/2, singleCircularListOps.getSingleCircularLinkedList().getSize());
    }

    // ##### ##### ######
    //empty/illegal value
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenSortedInsertNullNodeThenThrowException()
    {
        singleCircularListOps.insertAtEnd(node0);
        singleCircularListOps.sortedInsert(-1);
    }

    //insertInBST in Empty list
    @Test()
    public void whenSortedInsertEmptyListThenCreateList()
    {
        singleCircularListOps.setSingleCircularLinkedList(null);
        singleCircularListOps.sortedInsert(1);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();


        Assert.assertEquals(1, singleCircularListOps.getSingleCircularLinkedList().getSize());
    }

    //insertInBST in single-element list
    @Test()
    public void whenSortedInsertInSingleElementListThenAddAtEnd()
    {
        singleCircularListOps.insertAtEnd(node0);
        singleCircularListOps.sortedInsert(1);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        Assert.assertEquals(2, singleCircularListOps.getSingleCircularLinkedList().getSize());
    }

    //insertInBST at end in the multi-element list
    @Test()
    public void whenSortedInsertSmallerListThenAddAtEnd()
    {
        singleCircularListOps.insertAtEnd(node0);
        singleCircularListOps.insertAtEnd(new Node(1));
        singleCircularListOps.insertAtEnd(new Node(2));
        singleCircularListOps.insertAtEnd(new Node(3));

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        singleCircularListOps.sortedInsert(5);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        Assert.assertEquals(5, singleCircularListOps.getSingleCircularLinkedList().getSize());
    }


    //insertInBST in middle of multi-element list
    @Test()
    public void whenSortedInsertInMiddleListThenAdd()
    {
        singleCircularListOps.insertAtEnd(node0);
        singleCircularListOps.insertAtEnd(new Node(1));
        singleCircularListOps.insertAtEnd(new Node(8));
        singleCircularListOps.insertAtEnd(new Node(9));

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        singleCircularListOps.sortedInsert(6);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        Assert.assertEquals(5, singleCircularListOps.getSingleCircularLinkedList().getSize());
    }

    //insertInBST in middle of multi-element list
    @Test()
    public void whenSortedInsertInStartListThenAdd()
    {
        singleCircularListOps.insertAtEnd(new Node(1));
        singleCircularListOps.insertAtEnd(new Node(8));
        singleCircularListOps.insertAtEnd(new Node(9));

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        singleCircularListOps.sortedInsert(0);

        SingleCircularListOps.printList(singleCircularListOps.getSingleCircularLinkedList().getLast());
        System.out.println();

        Assert.assertEquals(4, singleCircularListOps.getSingleCircularLinkedList().getSize());
    }
}
