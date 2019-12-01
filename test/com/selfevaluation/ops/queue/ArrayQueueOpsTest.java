package com.selfevaluation.ops.queue;

import com.selfevaluation.ops.queue.ArrayQueueOps;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ArrayQueueOpsTest {

    ArrayQueueOps arrayQueueOps;

    @BeforeMethod
    public void setup()
    {
        arrayQueueOps = new ArrayQueueOps();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenEnqueueInvalidDataInQueueThrowException()
    {
        arrayQueueOps.enqueue(-1);
    }

    @Test
    public void whenEnqueueInEmptyInQueueAddElement()
    {
        arrayQueueOps.enqueue(0);
        Assert.assertEquals(arrayQueueOps.getRear(),0);
        Assert.assertEquals(arrayQueueOps.getQueue()[arrayQueueOps.getRear()],0);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void whenEnqueueInFullInQueueThrowException()
    {
        arrayQueueOps.enqueue(0);
        arrayQueueOps.enqueue(1);
        arrayQueueOps.enqueue(2);
        arrayQueueOps.enqueue(3);
        arrayQueueOps.enqueue(4);
        arrayQueueOps.enqueue(5);
        arrayQueueOps.enqueue(6);
        arrayQueueOps.enqueue(7);
        arrayQueueOps.enqueue(8);
        arrayQueueOps.enqueue(9);
        arrayQueueOps.enqueue(10);
    }

    @Test
    public void whenEnqueueInMultiElementInQueueAddElement()
    {
        arrayQueueOps.enqueue(0);
        arrayQueueOps.enqueue(1);
        arrayQueueOps.enqueue(2);
        arrayQueueOps.enqueue(3);
        Assert.assertEquals(arrayQueueOps.getRear(),3);
        Assert.assertEquals(arrayQueueOps.getQueue()[arrayQueueOps.getRear()],3);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void whenDequeFromEmptyQueueThrowException()
    {
        arrayQueueOps.dequeue();
    }

    @Test
    public void whenDequeFromSingleElementQueueThrowException()
    {
        arrayQueueOps.enqueue(0);
        arrayQueueOps.dequeue();
    }

    @Test
    public void whenDequeueFromFullQueueThenRemoveFromFront()
    {
        arrayQueueOps.enqueue(0);
        arrayQueueOps.enqueue(1);
        arrayQueueOps.enqueue(2);
        arrayQueueOps.enqueue(3);
        arrayQueueOps.enqueue(4);
        arrayQueueOps.enqueue(5);
        arrayQueueOps.enqueue(6);
        arrayQueueOps.enqueue(7);
        arrayQueueOps.enqueue(8);
        arrayQueueOps.enqueue(9);
        arrayQueueOps.dequeue();
        Assert.assertEquals(arrayQueueOps.getFront(),1);
    }

    @Test
    public void whenDequeueInMultiElementInQueueRemoveFromFront()
    {
        for(int i = 0;i< arrayQueueOps.getMAX();i++)
        {
            arrayQueueOps.enqueue(i);
        }
        Assert.assertEquals(arrayQueueOps.getRear(),9);
        Assert.assertEquals(arrayQueueOps.getQueue()[arrayQueueOps.getRear()],9);

        for(int i = 0;i< arrayQueueOps.getMAX();i++)
        {
            arrayQueueOps.dequeue();
        }
        Assert.assertEquals(arrayQueueOps.getFront(),9);
        Assert.assertTrue(arrayQueueOps.isEmpty());

    }

    //@Test
    public void whenEnqueueFollowedByDeQueueAllAndEnQueueAgainThenThrowException()
    {
        for(int i = 0;i<4;i++)
        {
            arrayQueueOps.enqueue(i);
        }
        Assert.assertTrue(arrayQueueOps.isFull());
        for(int i = 0;i< arrayQueueOps.getMAX();i++)
        {
            arrayQueueOps.dequeue();
        }
        Assert.assertTrue(arrayQueueOps.isEmpty());

        for(int i = 0;i<4;i++)
        {
            arrayQueueOps.enqueue(i);
        }
        Assert.assertEquals(arrayQueueOps.getRear(),3);
        Assert.assertEquals(arrayQueueOps.getQueue()[arrayQueueOps.getRear()],3);
    }

    @Test
    public void whenEnqueueInMultiElementAndDeque()
    {
        for(int i = 0;i< arrayQueueOps.getMAX();i++)
        {
            arrayQueueOps.enqueue(i);
        }
        for(int i = 0;i< arrayQueueOps.getMAX();i++)
        {
            arrayQueueOps.dequeue();
        }
        Assert.assertEquals(arrayQueueOps.getRear(),9);
        Assert.assertEquals(arrayQueueOps.getFront(),9);
    }

    // ########## // ########## // ########## // ########## // ##########

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenEnqueueInvalidElementThenThrowException()
    {
        arrayQueueOps.enqueueTokQueueArray(-1,0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenEnqueueInvalidQueueThenThrowException()
    {
        arrayQueueOps.enqueueTokQueueArray(0,-1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenEnqueueInMoreThanMaxQueueThenThrowException()
    {
        arrayQueueOps.enqueueTokQueueArray(0,arrayQueueOps.getMAX()+1);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void whenEnqueueInFullQueueThenThrowException()
    {
        arrayQueueOps.setMAX(1);
        arrayQueueOps.enqueueTokQueueArray(0,0);
        arrayQueueOps.enqueueTokQueueArray(1,0);
    }

    @Test
    public void whenEnqueueInNullQueueThenAddElement()
    {
        arrayQueueOps.setKQueueArray(null);
        arrayQueueOps.enqueueTokQueueArray(0,0);
        arrayQueueOps.enqueueTokQueueArray(1,0);
        //data
        Assert.assertEquals(arrayQueueOps.getKQueueArray()[0],0);
        //front index
        Assert.assertEquals(arrayQueueOps.getFrontTracker()[0],0);
        //rear index
        Assert.assertEquals(arrayQueueOps.getRearTracker()[0],0);
        //next index
        Assert.assertEquals(arrayQueueOps.getNextTracker()[0],-1);
    }

    // ########## // ########## // ########## // ########## // ##########

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenDequeFromInvalidQueueThenThrowException()
    {
        arrayQueueOps.dequeFromkQueueArray(-1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenDequeuFromMoreThanMaxQueueQueueThenThrowException()
    {
        arrayQueueOps.setMAX(10);
        arrayQueueOps.dequeFromkQueueArray(arrayQueueOps.getMAX()+1);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void whenDequeFromEmptyQueueThenThrowException()
    {
        arrayQueueOps.dequeFromkQueueArray(0);
    }

    @Test()
    public void whenDequeFromSingleElementQueueThenDeleteElement()
    {
        arrayQueueOps.enqueueTokQueueArray(0,0);
        arrayQueueOps.dequeFromkQueueArray(0);

        Assert.assertEquals(arrayQueueOps.getFrontTracker()[0],-1);
        Assert.assertEquals(arrayQueueOps.getNextTracker()[0],-1);
    }

    @Test()
    public void whenDequeFromNonSingleElementQueueThenDeleteElement()
    {
        arrayQueueOps.enqueueTokQueueArray(0,0);
        arrayQueueOps.enqueueTokQueueArray(1,0);
        arrayQueueOps.dequeFromkQueueArray(0);

        Assert.assertEquals(arrayQueueOps.getFrontTracker()[0],1);
        Assert.assertEquals(arrayQueueOps.getNextTracker()[0],-1);
        Assert.assertEquals(arrayQueueOps.getKQueueArray()[arrayQueueOps.getFrontTracker()[0]],1);
    }

    @Test()
    public void whenEnqueDequeFromNonSingleElementQueueThenDeleteElement()
    {
        arrayQueueOps.enqueueTokQueueArray(0,0);
        arrayQueueOps.enqueueTokQueueArray(1,0);
        arrayQueueOps.dequeFromkQueueArray(0);
        arrayQueueOps.enqueueTokQueueArray(2,0);
        arrayQueueOps.enqueueTokQueueArray(3,0);

        Assert.assertEquals(arrayQueueOps.getFrontTracker()[0],1);
        Assert.assertEquals(arrayQueueOps.getNextTracker()[arrayQueueOps.getFrontTracker()[0]],2);
        Assert.assertEquals(arrayQueueOps.getKQueueArray()[arrayQueueOps.getFrontTracker()[0]],1);

        Assert.assertEquals(arrayQueueOps.getRearTracker()[0],3);
        Assert.assertEquals(arrayQueueOps.getNextTracker()[arrayQueueOps.getRearTracker()[0]],-1);
        Assert.assertEquals(arrayQueueOps.getKQueueArray()[arrayQueueOps.getRearTracker()[0]],3);
    }
}
