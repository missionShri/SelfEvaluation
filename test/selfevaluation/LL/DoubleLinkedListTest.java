package selfevaluation.LL;

import com.selfevaluation.LL.DoubleLinkedListOps;
import com.selfevaluation.base.DoubleLinkedList;
import com.selfevaluation.base.DoubleLinkedList.Node;
import java.util.Arrays;
import java.util.Collection;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DoubleLinkedListTest {

    DoubleLinkedListOps doubleLinkedListOps;
    Node node0;

    @BeforeMethod
    public void setup() {
        doubleLinkedListOps = new DoubleLinkedListOps(new DoubleLinkedList());
        node0 = new DoubleLinkedList.Node(0);
    }

    //invalid
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenInsertInvalidDataAtFrontOfDLLThenThrowException() {
        doubleLinkedListOps.setDoubleLinkedList(null);
        doubleLinkedListOps.insertAtFront(-1);
    }

    //empty
    @Test()
    public void whenInsertDataAtFrontOfEmptyDLLThenAddNode() {
        doubleLinkedListOps.setDoubleLinkedList(null);
        doubleLinkedListOps.insertAtFront(0);
        Assert.assertEquals(1, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //happy-case
    @Test()
    public void whenInsertDataAtFrontOfSingleDLLThenAddNode() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.insertAtFront(1);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(2, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //happy-case
    @Test()
    public void whenInsertDataAtFrontOfMultipleElementDLLThenAddNode() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.insertAtFront(1);
        doubleLinkedListOps.insertAtFront(2);
        doubleLinkedListOps.insertAtFront(3);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(4, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    // ##########

    //invalid

    @DataProvider(name = "InvalidData")
    public static Object[][] invalidData() {
        return new Object[][] {
            {-1, -1, null}
        };
    }

    @Test(dataProvider = "InvalidData", expectedExceptions = IllegalArgumentException.class)
    public void whenInsertInvalidDataAfterGivenNodeInDLLThenThrowException(int nodeValueToBeAdded,
        int nodeValueToBeAddedAfter, DoubleLinkedList list) {
        doubleLinkedListOps.setDoubleLinkedList(list);
        doubleLinkedListOps.insertAfterGivenNode(nodeValueToBeAdded, nodeValueToBeAddedAfter);
    }

    @DataProvider(name = "EmptyData")
    public static Object[][] emptydata() {
        return new Object[][] {
            {1, 1, null}
        };
    }

    //empty
    @Test(dataProvider = "EmptyData")
    public void whenInsertDataInEmptyInDLLThenAddNode(int nodeValueToBeAdded, int nodeValueToBeAddedAfter,
        DoubleLinkedList list) {
        doubleLinkedListOps.setDoubleLinkedList(list);
        doubleLinkedListOps.insertAfterGivenNode(nodeValueToBeAdded, nodeValueToBeAddedAfter);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(1, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    @DataProvider(name = "nonExistantData")
    public static Object[][] nonExistantData() {
        return new Object[][] {
            {1, 1, 0}
        };
    }

    @Test(dataProvider = "nonExistantData", expectedExceptions = RuntimeException.class)
    public void whenInsertDataAfterNonExistantNodeInDLLThenThrowException(int nodeValueToBeAdded,
        int nodeValueToBeAddedAfter, int seedValue) {
        doubleLinkedListOps.insertAtFront(seedValue);
        doubleLinkedListOps.insertAfterGivenNode(nodeValueToBeAdded, nodeValueToBeAddedAfter);
    }

    @DataProvider(name = "listData")
    public static Object[][] listData() {
        return new Object[][] {
            {1, 0, 0}
        };
    }

    @Test(dataProvider = "listData")
    public void whenInsertDataAfterFirstDLLThenAddNode(int nodeValueToBeAdded, int nodeValueToBeAddedAfter,
        int seedValue) {
        doubleLinkedListOps.insertAtFront(seedValue);
        doubleLinkedListOps.insertAfterGivenNode(nodeValueToBeAdded, nodeValueToBeAddedAfter);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(2, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    @Test(dataProvider = "listData")
    public void whenInsertDataAfterEndDLLThenAddNode(int nodeValueToBeAdded, int nodeValueToBeAddedAfter,
        int seedValue) {
        doubleLinkedListOps.insertAtFront(seedValue);
        doubleLinkedListOps.insertAfterGivenNode(nodeValueToBeAdded, nodeValueToBeAddedAfter);
        doubleLinkedListOps.insertAfterGivenNode(2, 1);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(3, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    // ##########

    //invalid
    @Test(dataProvider = "InvalidData", expectedExceptions = IllegalArgumentException.class)
    public void whenInsertInvalidDataBeforeGivenNodeInDLLThenThrowException(int nodeValueToBeAdded,
        int nodeValueToBeAddedAfter, DoubleLinkedList list) {
        doubleLinkedListOps.setDoubleLinkedList(list);
        doubleLinkedListOps.insertBeforeGivenNode(nodeValueToBeAdded, nodeValueToBeAddedAfter);
    }

    //empty
    @Test(dataProvider = "EmptyData")
    public void whenInsertDataBeforeInEmptyInDLLThenAddNode(int nodeValueToBeAdded, int nodeValueToBeAddedAfter,
        DoubleLinkedList list) {
        doubleLinkedListOps.setDoubleLinkedList(list);
        doubleLinkedListOps.insertBeforeGivenNode(nodeValueToBeAdded, nodeValueToBeAddedAfter);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(1, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    @Test(dataProvider = "nonExistantData", expectedExceptions = RuntimeException.class)
    public void whenInsertDataBeforeNonExistantNodeInDLLThenThrowException(int nodeValueToBeAdded,
        int nodeValueToBeAddedAfter, int seedValue) {
        doubleLinkedListOps.insertAtFront(seedValue);
        doubleLinkedListOps.insertBeforeGivenNode(nodeValueToBeAdded, nodeValueToBeAddedAfter);
    }

    @Test(dataProvider = "listData")
    public void whenInsertDataBeforeFirstDLLThenAddNode(int nodeValueToBeAdded, int nodeValueToBeAddedAfter,
        int seedValue) {
        doubleLinkedListOps.insertAtFront(seedValue);
        doubleLinkedListOps.insertBeforeGivenNode(nodeValueToBeAdded, nodeValueToBeAddedAfter);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(2, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    @Test(dataProvider = "listData")
    public void whenInsertDataBeforeEndDLLThenAddNode(int nodeValueToBeAdded, int nodeValueToBeAddedAfter,
        int seedValue) {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.insertBeforeGivenNode(1, 0);
        doubleLinkedListOps.insertBeforeGivenNode(2, 1);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(3, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //##################

    //invalid
    @Test(dataProvider = "InvalidData", expectedExceptions = IllegalArgumentException.class)
    public void whenInsertInvalidDataAtEndInDLLThenThrowException(int nodeValueToBeAdded, int nodeValueToBeAddedAfter,
        DoubleLinkedList list) {
        doubleLinkedListOps.setDoubleLinkedList(list);
        doubleLinkedListOps.insertAtEnd(nodeValueToBeAdded);
    }

    //empty
    @Test(dataProvider = "EmptyData")
    public void whenInsertDataAtEndInEmptyInDLLThenAddNode(int nodeValueToBeAdded, int nodeValueToBeAddedAfter,
        DoubleLinkedList list) {
        doubleLinkedListOps.setDoubleLinkedList(list);
        doubleLinkedListOps.insertAtEnd(nodeValueToBeAdded);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(1, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    @Test(dataProvider = "listData")
    public void whenInsertDataAtEndOfSingleElementDLLThenAddNode(int nodeValueToBeAdded, int nodeValueToBeAddedAfter,
        int seedValue) {
        doubleLinkedListOps.insertAtFront(seedValue);
        doubleLinkedListOps.insertAtEnd(nodeValueToBeAdded);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(2, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    @Test(dataProvider = "listData")
    public void whenInsertDataAtEndDLLThenAddNode(int nodeValueToBeAdded, int nodeValueToBeAddedAfter, int seedValue) {
        doubleLinkedListOps.insertAtFront(seedValue);
        doubleLinkedListOps.insertBeforeGivenNode(nodeValueToBeAdded, nodeValueToBeAddedAfter);
        doubleLinkedListOps.insertAtEnd(2);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(3, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    // ################

    //empty
    @Test(dataProvider = "EmptyData", expectedExceptions = RuntimeException.class)
    public void whenDeleteFirstNodeInEmptyInDLLThenThrowException(int nodeValueToBeAdded, int nodeValueToBeAddedAfter,
        DoubleLinkedList list) {
        doubleLinkedListOps.setDoubleLinkedList(list);
        doubleLinkedListOps.deleteFirstNode();

    }

    //single
    @Test(dataProvider = "listData")
    public void whenDeleteFirstNodeInSingleElementDLLThenDeleteList(int nodeValueToBeAdded, int nodeValueToBeAddedAfter,
        int seedValue) {
        doubleLinkedListOps.insertAtFront(seedValue);
        doubleLinkedListOps.deleteFirstNode();

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(0, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //multi element list
    @Test(dataProvider = "listData")
    public void whenDeleteFirstNodeInMultiElementDLLThenDeleteNode(int nodeValueToBeAdded, int nodeValueToBeAddedAfter,
        int seedValue) {
        doubleLinkedListOps.insertAtFront(seedValue);
        doubleLinkedListOps.insertAtEnd(nodeValueToBeAdded);
        doubleLinkedListOps.deleteFirstNode();

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(1, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    // ############

    //empty
    @Test(dataProvider = "EmptyData", expectedExceptions = RuntimeException.class)
    public void whenDeleteLastNodeInEmptyInDLLThenThrowException(int nodeValueToBeAdded, int nodeValueToBeAddedAfter,
        DoubleLinkedList list) {
        doubleLinkedListOps.setDoubleLinkedList(list);
        doubleLinkedListOps.deleteLastNode();

    }

    //single
    @Test(dataProvider = "listData")
    public void whenDeleteLastNodeInSingleElementDLLThenDeleteList(int nodeValueToBeAdded, int nodeValueToBeAddedAfter,
        int seedValue) {
        doubleLinkedListOps.insertAtFront(seedValue);
        doubleLinkedListOps.deleteLastNode();

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(0, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //multi element list
    @Test(dataProvider = "listData")
    public void whenDeleteLastNodeInMultiElementDLLThenDeleteNode(int nodeValueToBeAdded, int nodeValueToBeAddedAfter,
        int seedValue) {
        doubleLinkedListOps.insertAtFront(seedValue);
        doubleLinkedListOps.insertAtEnd(nodeValueToBeAdded);
        doubleLinkedListOps.deleteLastNode();

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(1, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //##################

    //invalid
    @Test(dataProvider = "InvalidData", expectedExceptions = IllegalArgumentException.class)
    public void whenDeleteInvalidDataInDLLThenThrowException(int nodeValueToBeDeleted, int nodeValueToBeDeletedBefore,
        DoubleLinkedList list) {
        doubleLinkedListOps.setDoubleLinkedList(list);
        doubleLinkedListOps.deleteNode(nodeValueToBeDeleted);
    }

    //empty
    @Test(dataProvider = "EmptyData", expectedExceptions = IllegalArgumentException.class)
    public void whenDeleteInEmptyInDLLThenThrowException(int nodeValueToBeDeleted, int nodeValueToBeDeletedBefore,
        DoubleLinkedList list) {
        doubleLinkedListOps.setDoubleLinkedList(list);
        doubleLinkedListOps.deleteNode(nodeValueToBeDeleted);
    }

    //single element case
    @Test(dataProvider = "listData")
    public void whenDeleteInSingleElementDLLThenDeleteList(int nodeValueToBeDeleted, int nodeValueToBeDeletedBefore,
        int seedValue) {
        doubleLinkedListOps.insertAtFront(seedValue);
        doubleLinkedListOps.deleteNode(seedValue);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(0, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //non-existant
    @Test(dataProvider = "listData", expectedExceptions = RuntimeException.class)
    public void whenDeleteBeforeNonExistantInDLLThenThrowException(int nodeValueToBeDeleted,
        int nodeValueToBeDeletedBefore, int seedValue) {
        doubleLinkedListOps.insertAtFront(seedValue);
        doubleLinkedListOps.insertAtEnd(2);
        doubleLinkedListOps.deleteNode(nodeValueToBeDeleted);
    }

    //first
    @Test()
    public void whenDeleteFirstInDLLThenThrowException() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.insertAtFront(1);
        doubleLinkedListOps.insertAtFront(2);
        doubleLinkedListOps.insertAtFront(3);

        doubleLinkedListOps.deleteNode(3);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(3, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //second
    @Test()
    public void whenDeleteSecondInDLLThenDeleteNode() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.insertAtEnd(1);
        doubleLinkedListOps.insertAtEnd(2);
        doubleLinkedListOps.insertAtEnd(3);

        doubleLinkedListOps.deleteNode(1);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(3, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //mid
    @Test()
    public void whenDeleteMiddleInDLLThenDeleteNode() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.insertAtEnd(1);
        doubleLinkedListOps.insertAtEnd(2);
        doubleLinkedListOps.insertAtEnd(3);

        doubleLinkedListOps.deleteNode(2);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(3, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //last
    @Test()
    public void whenDeleteLastInDLLThenDeleteNode() {
        doubleLinkedListOps.insertAtEnd(1);
        doubleLinkedListOps.insertAtEnd(2);
        doubleLinkedListOps.insertAtEnd(3);

        doubleLinkedListOps.deleteNode(3);
        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(2, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //##################

    //invalid
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenDeleteInvalidDataAtPositionInDLLThenThrowException() {
        doubleLinkedListOps.setDoubleLinkedList(null);
        doubleLinkedListOps.deleteNodeAtPositon(-1);
    }

    //empty
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenDeleteAtPositionInEmptyInDLLThenThrowException() {
        doubleLinkedListOps.setDoubleLinkedList(null);
        doubleLinkedListOps.deleteNodeAtPositon(0);
    }

    //single element case
    @Test()
    public void whenDeleteAtPositionInSingleElementDLLThenDeleteList() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.deleteNodeAtPositon(0);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(0, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //non-existant
    @Test(expectedExceptions = RuntimeException.class)
    public void whenDeleteAtPositionNonExistantInDLLThenThrowException() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.deleteNodeAtPositon(1);
    }

    //first
    @Test()
    public void whenDeleteAtPositionFirstInDLLThenThrowException() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.insertAtFront(1);
        doubleLinkedListOps.insertAtFront(2);
        doubleLinkedListOps.insertAtFront(3);

        doubleLinkedListOps.deleteNodeAtPositon(0);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(3, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //second
    @Test()
    public void whenDeleteAtPositionBeforeSecondInDLLThenDeleteNode() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.insertAtEnd(1);
        doubleLinkedListOps.insertAtEnd(2);
        doubleLinkedListOps.insertAtEnd(3);

        doubleLinkedListOps.deleteNodeAtPositon(1);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(3, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //mid
    @Test()
    public void whenDeleteAtPositionBeforeMiddleInDLLThenDeleteNode() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.insertAtEnd(1);
        doubleLinkedListOps.insertAtEnd(2);
        doubleLinkedListOps.insertAtEnd(3);

        doubleLinkedListOps.deleteNodeAtPositon(2);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(3, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //last
    @Test()
    public void whenDeleteAtPositionBeforeLastInDLLThenDeleteNode() {
        doubleLinkedListOps.insertAtEnd(1);
        doubleLinkedListOps.insertAtEnd(2);
        doubleLinkedListOps.insertAtEnd(3);

        doubleLinkedListOps.deleteNodeAtPositon(2);
        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(2, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //##################

    //invalid
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenDeleteInvalidDataBeforeInDLLThenThrowException() {
        doubleLinkedListOps.setDoubleLinkedList(null);
        doubleLinkedListOps.deleteNodeBefore(-1);
    }

    //empty
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenDeleteBeforeInEmptyInDLLThenThrowException() {
        doubleLinkedListOps.setDoubleLinkedList(null);
        doubleLinkedListOps.deleteNodeBefore(0);
    }

    //single element case
    @Test()
    public void whenDeleteBeforeInSingleElementDLLThenDeleteList() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.deleteNodeBefore(0);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(1, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //non-existant
    @Test(expectedExceptions = RuntimeException.class)
    public void whenDeleteBeforeNonExistantInDLLThenThrowException() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.deleteNodeBefore(1);
    }

    //first
    @Test()
    public void whenDeleteBeforeFirstInDLLThenThrowException() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.insertAtFront(1);
        doubleLinkedListOps.insertAtFront(2);
        doubleLinkedListOps.insertAtFront(3);

        doubleLinkedListOps.deleteNodeBefore(3);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(4, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //second ...tricky case
    @Test()
    public void whenDeleteBeforeSecondInDLLThenDeleteNode() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.insertAtEnd(1);
        doubleLinkedListOps.insertAtEnd(2);
        doubleLinkedListOps.insertAtEnd(3);

        doubleLinkedListOps.deleteNodeBefore(1);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(3, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //mid
    @Test()
    public void whenDeleteBeforeMiddleInDLLThenDeleteNode() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.insertAtEnd(1);
        doubleLinkedListOps.insertAtEnd(2);
        doubleLinkedListOps.insertAtEnd(3);

        doubleLinkedListOps.deleteNodeBefore(2);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(3, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //last
    @Test()
    public void whenDeleteBeforeLastInDLLThenDeleteNode() {
        doubleLinkedListOps.insertAtEnd(1);
        doubleLinkedListOps.insertAtEnd(2);
        doubleLinkedListOps.insertAtEnd(3);

        doubleLinkedListOps.deleteNodeBefore(3);
        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(2, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //##################

    //invalid
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenDeleteInvalidDataAfterInDLLThenThrowException() {
        doubleLinkedListOps.setDoubleLinkedList(null);
        doubleLinkedListOps.deleteNodeAfter(-1);
    }

    //empty
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenDeleteAfterInEmptyInDLLThenThrowException() {
        doubleLinkedListOps.setDoubleLinkedList(null);
        doubleLinkedListOps.deleteNodeAfter(0);
    }

    //single element case
    @Test()
    public void whenDeleteAfterInSingleElementDLLThenDeleteList() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.deleteNodeAfter(0);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(1, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //non-existant
    @Test(expectedExceptions = RuntimeException.class)
    public void whenDeleteAfterNonExistantInDLLThenThrowException() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.deleteNodeAfter(1);
    }

    //first
    @Test()
    public void whenDeleteAfterFirstInDLLThenThrowException() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.insertAtFront(1);
        doubleLinkedListOps.insertAtFront(2);
        doubleLinkedListOps.insertAtFront(3);

        doubleLinkedListOps.deleteNodeAfter(3);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(3, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //second ...tricky case
    @Test()
    public void whenDeleteAfterSecondInDLLThenDeleteNode() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.insertAtEnd(1);
        doubleLinkedListOps.insertAtEnd(2);
        doubleLinkedListOps.insertAtEnd(3);

        doubleLinkedListOps.deleteNodeAfter(1);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(3, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //mid
    @Test()
    public void whenDeleteAfterMiddleInDLLThenDeleteNode() {
        doubleLinkedListOps.insertAtFront(0);
        doubleLinkedListOps.insertAtEnd(1);
        doubleLinkedListOps.insertAtEnd(2);
        doubleLinkedListOps.insertAtEnd(3);

        doubleLinkedListOps.deleteNodeAfter(2);

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(3, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //last
    @Test()
    public void whenDeleteAfterLastInDLLThenDeleteNode() {
        doubleLinkedListOps.insertAtEnd(1);
        doubleLinkedListOps.insertAtEnd(2);
        doubleLinkedListOps.insertAtEnd(3);

        doubleLinkedListOps.deleteNodeAfter(3);
        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(3, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    // ############
    //empty list
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenReverseEmptyListThenThrowException() {
        doubleLinkedListOps.setDoubleLinkedList(new DoubleLinkedList());
        //doubleLinkedListOps.reverse();
        doubleLinkedListOps.reverseRecursive(doubleLinkedListOps.getDoubleLinkedList().getHead());

    }

    //single element
    @Test()
    public void whenReverseSingleListThenReturnListAsIs() {
        doubleLinkedListOps.insertAtEnd(0);

        //doubleLinkedListOps.reverse();
        doubleLinkedListOps.reverseRecursive(doubleLinkedListOps.getDoubleLinkedList().getHead());

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(doubleLinkedListOps.getDoubleLinkedList().getSize(), 1);
    }

    //multi-element
    @Test()
    public void whenReverseMultiListThenReturnListAsIs() {
        doubleLinkedListOps.insertAtEnd(0);
        doubleLinkedListOps.insertAtEnd(1);
        doubleLinkedListOps.insertAtEnd(2);

        //doubleLinkedListOps.reverse();
        doubleLinkedListOps.reverseRecursive(doubleLinkedListOps.getDoubleLinkedList().getHead());

        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        Assert.assertEquals(3, doubleLinkedListOps.getDoubleLinkedList().getSize());
    }

    //empty-list
    @Test()
    public void whenMergeSortEmptyThenReturn() {
        doubleLinkedListOps.setDoubleLinkedList(new DoubleLinkedList());
        doubleLinkedListOps.mergeSort(doubleLinkedListOps.getDoubleLinkedList().getHead(), null);
    }

    //single-list
    @Test()
    public void whenMergeSortSingleThenReturnAsId() {
        doubleLinkedListOps.insertAtEnd(1);
        doubleLinkedListOps.mergeSort(doubleLinkedListOps.getDoubleLinkedList().getHead(), null);
        Assert.assertEquals(doubleLinkedListOps.getDoubleLinkedList().getSize(), 1);
    }

    //double-list
    @Test()
    public void whenMergeSortTwoElementThenReturnSorted() {
        doubleLinkedListOps.insertAtEnd(100);
        doubleLinkedListOps.insertAtEnd(10);

        System.out.print("\nBefore\n");
        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        System.out.println();

        Node current = doubleLinkedListOps.getDoubleLinkedList().getHead();
        Node prev = null;
        while(current!=null)
        {
            prev = current;
            current = current.getNext();
        }

        Node newNode = doubleLinkedListOps.mergeSort(doubleLinkedListOps.getDoubleLinkedList().getHead(),prev);

        System.out.print("\nAfter\n");
        DoubleLinkedListOps.printList(newNode);
        System.out.println();

        Assert.assertEquals(doubleLinkedListOps.getDoubleLinkedList().getSize(), 2);

    }

    //multi-list
    @Test()
    public void whenMergeSortMultiThenReturnListSorted() {
        doubleLinkedListOps.insertAtEnd(3);
        doubleLinkedListOps.insertAtEnd(2);
        doubleLinkedListOps.insertAtEnd(1);
        doubleLinkedListOps.insertAtEnd(4);
        doubleLinkedListOps.insertAtEnd(5);
        doubleLinkedListOps.insertAtEnd(6);

        System.out.print("\nBefore\n");
        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        System.out.println();

        Node current = doubleLinkedListOps.getDoubleLinkedList().getHead();
        Node prev = null;
        while(current!=null)
        {
            prev = current;
            current = current.getNext();
        }

        Node newHead = doubleLinkedListOps.mergeSort(doubleLinkedListOps.getDoubleLinkedList().getHead(), prev);
        doubleLinkedListOps.getDoubleLinkedList().setHead(newHead);
        System.out.print("\nAfter\n");
        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        System.out.println();

        Assert.assertEquals(doubleLinkedListOps.getDoubleLinkedList().getSize(), 6);
    }


    //Test mergeSorted (which is one of the steps)_instead of mergeSort
    @Test()
    public void whenMergeSortedMulticurrentFirstThenReturnListSorted() {
        doubleLinkedListOps.insertAtEnd(3);
        doubleLinkedListOps.insertAtEnd(4);

        DoubleLinkedList otherDLL= new DoubleLinkedList() ;
        DoubleLinkedListOps otherDLLOps = new DoubleLinkedListOps(otherDLL);
        otherDLLOps.insertAtEnd(1);
        otherDLLOps.insertAtEnd(2);

        Node newNode = doubleLinkedListOps.mergeSortedLists(doubleLinkedListOps.getDoubleLinkedList().getHead(),otherDLLOps.getDoubleLinkedList().getHead());

        System.out.print("\nAfter\n");
        DoubleLinkedListOps.printList(newNode);
        System.out.println();

    }

}


// ########## ############ ###########
/*//empty-list
    @Test()
    public void whenQuickSortEmptyThenReturn()
    {
        doubleLinkedListOps.setDoubleLinkedList(new DoubleLinkedList());
        doubleLinkedListOps.quickSort(doubleLinkedListOps.getDoubleLinkedList().getHead(), null);
    }

    //single-list
    @Test()
    public void whenQuickSortSingleThenReturnAsId()
    {
        doubleLinkedListOps.insertAtEnd(1);
        doubleLinkedListOps.quickSort(doubleLinkedListOps.getDoubleLinkedList().getHead(), null);
        Assert.assertEquals(doubleLinkedListOps.getDoubleLinkedList().getSize(), 1);
    }*/


//multi-list
   /* @Test()
    public void whenQuickSortMultipleThenReturnSorted()
    {*/
        /*doubleLinkedListOps.insertAtEnd(5);
        doubleLinkedListOps.insertAtEnd(4);
        doubleLinkedListOps.insertAtEnd(3);
        doubleLinkedListOps.insertAtEnd(2);
        doubleLinkedListOps.insertAtEnd(1);
        doubleLinkedListOps.insertAtEnd(0);*/

       /*doubleLinkedListOps.insertAtFront(1);
        doubleLinkedListOps.insertAtFront(2);
        doubleLinkedListOps.insertAtFront(3);
        doubleLinkedListOps.insertAtFront(4);*/

        /*doubleLinkedListOps.insertAtEnd(9);
        doubleLinkedListOps.insertAtEnd(3);
        doubleLinkedListOps.insertAtFront(1);
        doubleLinkedListOps.insertAtFront(13);
        doubleLinkedListOps.insertAtFront(16);
        doubleLinkedListOps.insertAtFront(7);*/

        /*doubleLinkedListOps.insertAtEnd(9);
        doubleLinkedListOps.insertAtEnd(3);
        doubleLinkedListOps.insertAtEnd(18);
        doubleLinkedListOps.insertAtEnd(30);
        doubleLinkedListOps.insertAtFront(1);
        doubleLinkedListOps.insertAtFront(13);
        doubleLinkedListOps.insertAtFront(16);
        doubleLinkedListOps.insertAtFront(7);
        doubleLinkedListOps.insertAtFront(2);
        doubleLinkedListOps.insertAtFront(8);*/

        /*System.out.print("\nBefore\n");
        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());

        //current = first
        Node current = doubleLinkedListOps.getDoubleLinkedList().getHead();
        Node prev = null;

        //current != last->next
        while (current!=null)
        {
            prev = current;
            current = current.getNext();
        }

        //first-if-condition-check
        if(current==null)
        {
            doubleLinkedListOps.quickSort(doubleLinkedListOps.getDoubleLinkedList().getHead(), prev);
        }

        System.out.println("\nAfter\n");
        DoubleLinkedListOps.printList(doubleLinkedListOps.getDoubleLinkedList().getHead());
        //Assert.assertEquals(doubleLinkedListOps.getDoubleLinkedList().getSize(), 3);
    }*/
// ########## ############ ###########
