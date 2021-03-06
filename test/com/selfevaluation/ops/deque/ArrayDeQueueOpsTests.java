package com.selfevaluation.ops.deque;

import com.selfevaluation.ops.deque.ArrayDeQueueOps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class ArrayDeQueueOpsTests {

    private ArrayDeQueueOps arrayDeQueueOps;

    @BeforeMethod
    public void setup()
    {
        arrayDeQueueOps = new ArrayDeQueueOps();
    }

    /*@Test(expectedExceptions = IllegalArgumentException.class)
    public void whenPushFromRearInvalidDataToRearThenThrowException()
    {
        arrayDeQueueOps.pushToRear(-1);
    }

    @Test
    public void whenPushFromRearToNullDequeThenAddElement()
    {
        arrayDeQueueOps.pushToRear(0);
        Assert.assertEquals(arrayDeQueueOps.getSize(),1);
        Assert.assertEquals(arrayDeQueueOps.getRear(),0);
    }

    @Test
    public void whenPushFromRearToEmptyDequeThenAddElement()
    {
        arrayDeQueueOps.pushToRear(0);
        Assert.assertEquals(arrayDeQueueOps.getSize(),1);
        Assert.assertEquals(arrayDeQueueOps.getRear(),0);
    }

    @Test
    public void whenPushFromRearToNonEmptyDequeThenAddElement()
    {
        arrayDeQueueOps.pushToRear(0);
        arrayDeQueueOps.pushToRear(1);

        arrayDeQueueOps.pushToRear(2);
        Assert.assertEquals(arrayDeQueueOps.getSize(),3);
        Assert.assertEquals(arrayDeQueueOps.getRear(),2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenPushFromFrontInvalidDataFromFrontThenThrowException()
    {
        arrayDeQueueOps.pushFromFront(-1);
    }

    @Test
    public void whenPushFromFrontToNullDequeThenAddElement()
    {
        arrayDeQueueOps.pushFromFront(0);

        Assert.assertEquals(arrayDeQueueOps.getSize(),1);
        Assert.assertEquals(Math.abs((arrayDeQueueOps.getFront()) % ArrayDeQueueOps.MAX),99);
    }

    @Test
    public void whenPushFromFrontToEmptyDequeThenAddElement()
    {
        arrayDeQueueOps.pushFromFront(0);

        Assert.assertEquals(arrayDeQueueOps.getSize(),1);
        Assert.assertEquals(Math.abs((arrayDeQueueOps.getFront()) % ArrayDeQueueOps.MAX),99);
    }

    @Test
    public void whenPushFromFrontToNonEmptyDequeThenAddElement()
    {
        arrayDeQueueOps.pushFromFront(0);
        arrayDeQueueOps.pushToRear(1);
        arrayDeQueueOps.pushFromFront(2);

        Assert.assertEquals(arrayDeQueueOps.getSize(),3);
        //Assert.assertEquals(arrayDeQueueOps.getRear(),0);
        Assert.assertEquals(arrayDeQueueOps.getRear(),1);
        Assert.assertEquals(Math.abs((arrayDeQueueOps.getFront()) % ArrayDeQueueOps.MAX),98);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void whenRemoveFromRearInNullDequeueThenThrowException()
    {
        arrayDeQueueOps.removeFromRear();
    }

    @Test
    public void whenRemoveFromRearInSingleDequeThenRemoveElement()
    {
        arrayDeQueueOps.pushToRear(0);
        int data = arrayDeQueueOps.removeFromRear();

        Assert.assertEquals(arrayDeQueueOps.getSize(),0);
        Assert.assertEquals(arrayDeQueueOps.getRear(),-1);
    }

    @Test
    public void whenRemoveFromRearInNonEmptyDequeThenAddElement()
    {
        arrayDeQueueOps.pushToRear(0);
        arrayDeQueueOps.pushToRear(1);
        int data = arrayDeQueueOps.removeFromRear();

        Assert.assertEquals(arrayDeQueueOps.getSize(),1);
        Assert.assertEquals(arrayDeQueueOps.getRear(),0);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void whenRemoveFromFrontInNullDequeueThenThrowException()
    {
        arrayDeQueueOps.removeFromFront();
    }

    @Test
    public void whenRemoveFromFrontInSingleDequeThenRemoveElement()
    {
        arrayDeQueueOps.pushFromFront(0);
        int data = arrayDeQueueOps.removeFromFront();

        Assert.assertEquals(arrayDeQueueOps.getSize(),0);
        Assert.assertEquals(arrayDeQueueOps.getFront(),ArrayDeQueueOps.MAX);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void whenRemoveFromFrontInNonEmptyDequeThenAddElement()
    {
        arrayDeQueueOps.pushToRear(0);
        arrayDeQueueOps.pushToRear(1);
        int data = arrayDeQueueOps.removeFromFront();

    }*/

    @Test
    public void whenMaxOfAllSubArraysFromNonEmptyDequeThenReturnArray()
    {
        int input[] = {12, 1, 78, 90, 4,97, 57, 102, 213, -2, 4};
        Map<String, int[]> returnValue = arrayDeQueueOps.maxAndMinOfAllSubArrays(input,3);

        System.out.println("Max: ");
        for (int i = 0; i < returnValue.get("max").length; i++) {
            System.out.print(returnValue.get("max")[i]+"\t");
        }

        System.out.println();
        System.out.println("Min: ");
        for (int i = 0; i < returnValue.get("min").length; i++) {
            System.out.print(returnValue.get("min")[i]+"\t");
        }
    }

}
