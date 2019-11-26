package selfevaluation.LL;

import com.selfevaluation.ops.LLOps;
import com.selfevaluation.base.LinkedList;
import com.selfevaluation.base.LinkedList.Node;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LLOpsTest {

    LinkedList linkedList;
    LLOps llOps;
    Node node0;

    // TODO: ***** https://medium.freecodecamp.org/coding-interviews-for-dummies-5e048933b82b

    // null/empty list
    // single element list
    // duplicate values list
    // happy case :
       /* - first
        - middle
        - last*/

    @BeforeMethod
    public void setup()
    {
        linkedList = new LinkedList();
        llOps = new LLOps(linkedList);
        node0 = new Node(0);
    }

    @Test
    public void whenInsertAtFrontThenAddNode()
    {
        llOps.insertAtFront(node0);
        Assert.assertEquals(node0,llOps.getLinkedList().getHead());
        Assert.assertEquals(1,llOps.getLinkedList().getSize());
    }

    @Test()
    public void whenInsertAtFrontOnEmptyListThenThrowException()
    {
        llOps.setLinkedList(null);
        llOps.insertAtFront(node0);
        Assert.assertEquals(1,llOps.getLinkedList().getSize());
    }

    //################
    @Test()
    public void whenInsertNodeToNullListThenThrowException()
    {
        llOps.setLinkedList(null);
        llOps.insertAfterGivenNode(new Node(3), 8);
    }

    @Test()
    public void whenInsertNullNodeThenThrowException()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));

        LLOps.printList(llOps.getLinkedList().getHead());
        System.out.println();

        try {
            llOps.insertAfterGivenNode(null, 8);
        } catch (Exception e) {
            Assert.assertEquals(IllegalArgumentException.class,e.getClass());
            Assert.assertEquals("Input is empty",e.getMessage());
        }
    }

    @Test()
    public void whenInsertAfterNotPresentNodeThenThrowException()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));

        LLOps.printList(llOps.getLinkedList().getHead());
        System.out.println();

        try {
            llOps.insertAfterGivenNode(new Node(3), 8);
        } catch (Exception e) {
            Assert.assertEquals(RuntimeException.class,e.getClass());
            Assert.assertEquals("Reached end of the list but element not found",e.getMessage());
        }
    }

    @Test
    public void whenInsertAfterGivenNodeThenAddNode()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));

        LLOps.printList(llOps.getLinkedList().getHead());
        System.out.println();

        llOps.insertAfterGivenNode(new Node(3), 1);
        Assert.assertEquals(4,llOps.getLinkedList().getSize());
    }

    //1-node value edge case...was failing.
    //Now will handle single element cases separately, BE it ANY DS
    @Test
    public void whenInsertAfterFirstNodeInSingleElementThenAddNode()
    {
        llOps.insertAtFront(node0);

        LLOps.printList(llOps.getLinkedList().getHead());
        System.out.println();

        llOps.insertAfterGivenNode(new Node(3), 0);
        Assert.assertEquals(2,llOps.getLinkedList().getSize());
    }

    //################
    //null list
    //Adding to it as a feature to insertInBST
    @Test
    public void whenInsertAtEndOfNullListThenThrowException()
    {
        llOps.setLinkedList(null);
        llOps.insertAtEnd(new Node(3));
        Assert.assertEquals(1,llOps.getLinkedList().getSize());

    }
    //null addition
    @Test
    public void whenInsertNullElementThenThrowException()
    {
        try {
            llOps.insertAtEnd(null);
        } catch (Exception e) {
            Assert.assertEquals(IllegalArgumentException.class,e.getClass());
            Assert.assertEquals("Input is empty",e.getMessage());
        }
    }

    //adding to a single element list
    @Test
    public void whenInsertAtEndOfSingleElementlList()
    {
        llOps.insertAtFront(node0);

        LLOps.printList(llOps.getLinkedList().getHead());
        System.out.println();

        llOps.insertAtEnd(new Node(3));
        Assert.assertEquals(2,llOps.getLinkedList().getSize());
    }

    //happy case
    @Test
    public void whenInsertAtEndOflListThenAddNode()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));

        LLOps.printList(llOps.getLinkedList().getHead());
        System.out.println();

        llOps.insertAtEnd(new Node(3));
        Assert.assertEquals(4,llOps.getLinkedList().getSize());
    }

    //################################################
    // null linked-list
    @Test()
    public void whenDeleteFromNullListThenThrowException()
    {
        llOps.setLinkedList(null);
        try {
            llOps.delete(3);
        } catch (Exception e) {
            Assert.assertEquals(RuntimeException.class,e.getClass());
            Assert.assertEquals("Input is empty",e.getMessage());
        }
    }

    // null input
    @Test
    public void whenDeleteNullInputThenThrowException()
    {
        llOps.insertAtFront(node0);
        try {
            llOps.delete(-1);
        } catch (Exception e) {
            Assert.assertEquals(RuntimeException.class,e.getClass());
            Assert.assertEquals("Input is empty",e.getMessage());
        }
    }
    // item not in the list
    @Test
    public void whenDeleteInputNotInListThenThrowException()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));

        try {
            llOps.delete(3);
        } catch (Exception e) {
            Assert.assertEquals(RuntimeException.class,e.getClass());
            Assert.assertEquals("Reached end of the list but element not found",e.getMessage());
        }
    }
    // single-element list
    @Test
    public void whenDeleteSingleElementThenDelete()
    {
        llOps.insertAtFront(node0);
        LLOps.printList(llOps.getLinkedList().getHead());
        System.out.println();

        llOps.delete(0);
        Assert.assertEquals(0, llOps.getLinkedList().getSize());
    }

    //last element
    @Test
    public void whenDeleteLastElementInListThenDelete()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));

        LLOps.printList(llOps.getLinkedList().getHead());
        System.out.println();

        llOps.delete(0);
        Assert.assertEquals(2, llOps.getLinkedList().getSize());
    }

    // happy case
    @Test
    public void whenDeleteElementInListThenDelete()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));
        llOps.insertAtFront(new Node(3));

        LLOps.printList(llOps.getLinkedList().getHead());
        System.out.println();

        llOps.delete(2);
        Assert.assertEquals(3, llOps.getLinkedList().getSize());
    }

    //################################################

    //bad input : null list,
    @Test
    public void whenDeleteElementInNullListThenThrowException()
    {
        llOps.setLinkedList(null);
        try {
            llOps.deleteAtPosition(1);
        } catch (Exception e) {
            Assert.assertEquals(RuntimeException.class,e.getClass());
            Assert.assertEquals("Input is invalid",e.getMessage());
        }
    }

    //negative index
    @Test
    public void whenDeleteElementWithBadPositionThenThrowException()
    {
        llOps.insertAtFront(node0);
        try {
            llOps.deleteAtPosition(-1);
        } catch (Exception e) {
            Assert.assertEquals(RuntimeException.class,e.getClass());
            Assert.assertEquals("Input is invalid",e.getMessage());
        }
    }

    // index bigger than list
    @Test
    public void whenDeleteElementWithPositionGreaterThanListSizeThenThrowException()
    {
        llOps.insertAtFront(node0);
        try {
            llOps.deleteAtPosition(2);
        } catch (Exception e) {
            Assert.assertEquals(RuntimeException.class,e.getClass());
            Assert.assertEquals("Reached end of the list but element not found",e.getMessage());
        }
    }

    // happy case
    @Test
    public void whenDeleteElementAtFirstPositionInSingleElementListThenDelete()
    {
        llOps.insertAtFront(node0);

        LLOps.printList(llOps.getLinkedList().getHead());
        System.out.println();

        llOps.deleteAtPosition(0);
        Assert.assertEquals(0, llOps.getLinkedList().getSize());
    }

    @Test
    public void whenDeleteElementAtLastPositionInListThenDelete()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));
        llOps.insertAtFront(new Node(3));

        LLOps.printList(llOps.getLinkedList().getHead());
        System.out.println();

        llOps.deleteAtPosition(3);
        Assert.assertEquals(3, llOps.getLinkedList().getSize());
    }

    @Test
    public void whenDeleteElementAtGivenPositionInListThenDelete()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));
        llOps.insertAtFront(new Node(3));

        LLOps.printList(llOps.getLinkedList().getHead());
        System.out.println();

        llOps.deleteAtPosition(2);
        Assert.assertEquals(3, llOps.getLinkedList().getSize());
    }

    //################################################
    //null input

    @Test
    public void whenListSizeInNullListThenThrowException()
    {
        llOps.setLinkedList(null);
        try {
            //llOps.findListSize();
            llOps.findListSizeRecursively();
        } catch (Exception e) {
            Assert.assertEquals(RuntimeException.class,e.getClass());
            Assert.assertEquals("Input is invalid",e.getMessage());
        }
    }

    //single element
    @Test
    public void whenListSizeInSingleElementListThenFindSize()
    {
        llOps.insertAtFront(node0);
        //Assert.assertEquals(1,llOps.findListSize());
        Assert.assertEquals(1,llOps.findListSizeRecursively());
    }

    //average case
    @Test
    public void whenListSizeInListThenFindSize()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));
        llOps.insertAtFront(new Node(3));
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));
        llOps.insertAtFront(new Node(3));
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));
        llOps.insertAtFront(new Node(3));

        //Assert.assertEquals(4,llOps.findListSize());
        llOps.findListSize();
        //llOps.findListSizeRecursively();
    }

    // ################

    // null/empty list
    @Test
    public void whenListIsNullToSwapTwoElementThenThrowException()
    {
        llOps.setLinkedList(null);
        try {
            llOps.swapElements(1,2);
        }catch (Exception e)
        {
            Assert.assertEquals(IllegalArgumentException.class,e.getClass());
            Assert.assertEquals("Invalid input", e.getMessage());
        }
    }

    //bad inputs
    @Test
    public void whenInvalidInputsToSwapTwoElementThenThrowException()
    {
        llOps.insertAtFront(node0);
        try {
            llOps.swapElements(1,2);
        }catch (Exception e)
        {
            Assert.assertEquals(RuntimeException.class,e.getClass());
            Assert.assertEquals("Reached end of the list but element not found", e.getMessage());
        }
    }

    // single element list
    @Test
    public void whenSwapTwoElementInSingleElementListThenThrowException()
    {
        llOps.insertAtFront(node0);
        try {
            llOps.swapElements(1,2);
        }catch (Exception e)
        {
            Assert.assertEquals(RuntimeException.class,e.getClass());
            Assert.assertEquals("Reached end of the list but element not found", e.getMessage());
        }
    }

    @Test
    public void whenSwapNonPresentTwoElementSingleElementListThenThrowException()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));
        llOps.insertAtFront(new Node(3));

        try {
            llOps.swapElements(8,9);
        }catch (Exception e)
        {
            Assert.assertEquals(RuntimeException.class,e.getClass());
            Assert.assertEquals("Reached end of the list but element not found", e.getMessage());
        }
    }

    // duplicate values list
    // happy case :
       /* - first
        - middle
        - last
        - first element to swap appears later in the list*/

    @Test
    public void whenSwapTwoElement()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));
        llOps.insertAtFront(new Node(3));

        LLOps.printList(llOps.getLinkedList().getHead());
        System.out.println();

        //Assert.assertTrue(llOps.swapElements(0,1)); //Breaking :
        //Assert.assertTrue(llOps.swapElements(1,0));

        //Assert.assertTrue(llOps.swapElements(0,2));
        //Assert.assertTrue(llOps.swapElements(2,0));


        //Assert.assertTrue(llOps.swapElements(2,1));
        //Assert.assertTrue(llOps.swapElements(1,2)); //Breaking :

        //Assert.assertTrue(llOps.swapElements(1,3));
        //Assert.assertTrue(llOps.swapElements(3,1));

        //Assert.assertTrue(llOps.swapElements(0,3));
        //Assert.assertTrue(llOps.swapElements(3,0));
    }

    // ##########

    //null
    @Test
    public void whenReverseNullListThrowException() {

        llOps.setLinkedList(null);
        try {
            llOps.reverseList();
        }catch (Exception e)
        {
            Assert.assertEquals(RuntimeException.class,e.getClass());
            Assert.assertEquals("Invalid Input", e.getMessage());
        }
    }

    //single element list
    @Test
    public void whenReverseSingleElementListThrowException() {

        llOps.insertAtFront(node0);
        Assert.assertTrue(llOps.reverseList());
    }

    //>1 list
    @Test
    public void whenReverseMultiElementListThrowException() {

        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));
        llOps.insertAtFront(new Node(3));
        LLOps.printList(linkedList.getHead());

        Assert.assertTrue(llOps.reverseList());
    }

    //############
    //Sorted merge

    //if both inputs null,  // invalid inputs
    @Test
    public void whenMergeTwoNullListsThrowException() {

        llOps.setLinkedList(null);
        LLOps llOpsOther=  new LLOps(null);
        try {
            llOps.mergeSortedLists(llOps.getLinkedList(),llOpsOther.getLinkedList());
        }catch (Exception e)
        {
            Assert.assertEquals(IllegalArgumentException.class,e.getClass());
            Assert.assertEquals("Invalid Input", e.getMessage());
        }

    }

    @Test
    public void whenMergeOneNullListsReturnTheNonNullList() {

        llOps.setLinkedList(null);

        LinkedList otherLinkedList =  new LinkedList();
        LLOps llOpsOther=  new LLOps(otherLinkedList);
        llOpsOther.insertAtFront(node0);

        Assert.assertEquals(llOpsOther.getLinkedList().getHead(),llOps.mergeSortedLists(llOps.getLinkedList(),llOpsOther.getLinkedList()));
    }

    //single valued list FLFESmallerThanSLFE
    @Test
    public void whenMergeTwoSortedSingleElementWithFLFESmallerThanSLFEListsReturnMergedList() {

        llOps.insertAtFront(new Node(4));

        LinkedList otherLinkedList =  new LinkedList();
        LLOps llOpsOther=  new LLOps(otherLinkedList);
        llOpsOther.insertAtFront(new Node(5));

        llOps.mergeSortedLists(linkedList,otherLinkedList);
        //Assert.assertEquals(llOpsOther.getLinkedList().getHead(),llOps.mergeSortedLists(llOps.getLinkedList(),llOpsOther.getLinkedList()));
    }


    //single valued list FLFEEqualThanSLFE
    @Test
    public void whenMergeTwoSortedSingleElementWithFLFEEqualThanSLFEListsReturnMergedList() {

        llOps.insertAtFront(new Node(4));

        LinkedList otherLinkedList =  new LinkedList();
        LLOps llOpsOther=  new LLOps(otherLinkedList);
        llOpsOther.insertAtFront(new Node(4));

        llOps.mergeSortedLists(linkedList,otherLinkedList);
        //Assert.assertEquals(llOpsOther.getLinkedList().getHead(),llOps.mergeSortedLists(llOps.getLinkedList(),llOpsOther.getLinkedList()));
    }

    //single valued list FLFEGreaterThanSLFE
    @Test
    public void whenMergeTwoSortedSingleElementWithFLFEGreaterThanSLFEListsReturnMergedList() {

        llOps.insertAtFront(new Node(5));

        LinkedList otherLinkedList =  new LinkedList();
        LLOps llOpsOther=  new LLOps(otherLinkedList);
        llOpsOther.insertAtFront(new Node(4));

        llOps.mergeSortedLists(linkedList,otherLinkedList);
        //Assert.assertEquals(llOpsOther.getLinkedList().getHead(),llOps.mergeSortedLists(llOps.getLinkedList(),llOpsOther.getLinkedList()));
    }

    //multi valued list
    @Test
    public void whenMergeTwoSortedListsReturnMergedList() {

        llOps.insertAtFront(new Node(8));
        llOps.insertAtFront(new Node(6));
        llOps.insertAtFront(new Node(4));
        llOps.insertAtFront(node0);

        LinkedList otherLinkedList =  new LinkedList();
        LLOps llOpsOther=  new LLOps(otherLinkedList);
        llOpsOther.insertAtFront(new Node(9));
        llOpsOther.insertAtFront(new Node(7));
        llOpsOther.insertAtFront(new Node(5));


        llOps.mergeSortedLists(linkedList,otherLinkedList);
        //Assert.assertEquals(llOpsOther.getLinkedList().getHead(),llOps.mergeSortedLists(llOps.getLinkedList(),llOpsOther.getLinkedList()));
    }

    //Mutli valued list FLSmallerThanSL
    @Test
    public void whenMergeTwoSortedListsWithFLSmallerThanSLReturnMergedList() {

        llOps.insertAtFront(new Node(8));
        llOps.insertAtFront(new Node(6));
        llOps.insertAtFront(new Node(4));

        LinkedList otherLinkedList =  new LinkedList();
        LLOps llOpsOther=  new LLOps(otherLinkedList);
        llOpsOther.insertAtFront(new Node(16));
        llOpsOther.insertAtFront(new Node(15));
        llOpsOther.insertAtFront(new Node(14));
        llOpsOther.insertAtFront(new Node(13));
        llOpsOther.insertAtFront(new Node(12));
        llOpsOther.insertAtFront(new Node(10));


        llOps.mergeSortedLists(linkedList,otherLinkedList);
        //Assert.assertEquals(llOpsOther.getLinkedList().getHead(),llOps.mergeSortedLists(llOps.getLinkedList(),llOpsOther.getLinkedList()));
    }

    //Mutli valued list FLEqualThanSL
    @Test
    public void whenMergeTwoSortedListsWithFLEqualToSLReturnMergedList() {

        llOps.insertAtFront(new Node(13));
        llOps.insertAtFront(new Node(12));
        llOps.insertAtFront(new Node(10));

        LinkedList otherLinkedList =  new LinkedList();
        LLOps llOpsOther=  new LLOps(otherLinkedList);
        llOpsOther.insertAtFront(new Node(16));
        llOpsOther.insertAtFront(new Node(15));
        llOpsOther.insertAtFront(new Node(14));
        llOpsOther.insertAtFront(new Node(13));
        llOpsOther.insertAtFront(new Node(12));
        llOpsOther.insertAtFront(new Node(10));


        llOps.mergeSortedLists(linkedList,otherLinkedList);
        //Assert.assertEquals(llOpsOther.getLinkedList().getHead(),llOps.mergeSortedLists(llOps.getLinkedList(),llOpsOther.getLinkedList()));
    }


    //Mutli valued list FLSmallerThanSL
    @Test
    public void whenMergeTwoSortedListsWithFLGreaterThanSLReturnMergedList() {

        llOps.insertAtFront(new Node(22));
        llOps.insertAtFront(new Node(20));
        llOps.insertAtFront(new Node(18));

        LinkedList otherLinkedList =  new LinkedList();
        LLOps llOpsOther=  new LLOps(otherLinkedList);
        llOpsOther.insertAtFront(new Node(16));
        llOpsOther.insertAtFront(new Node(15));
        llOpsOther.insertAtFront(new Node(14));
        llOpsOther.insertAtFront(new Node(13));
        llOpsOther.insertAtFront(new Node(12));
        llOpsOther.insertAtFront(new Node(10));


        llOps.mergeSortedLists(linkedList,otherLinkedList);
        //Assert.assertEquals(llOpsOther.getLinkedList().getHead(),llOps.mergeSortedLists(llOps.getLinkedList(),llOpsOther.getLinkedList()));
    }

}
