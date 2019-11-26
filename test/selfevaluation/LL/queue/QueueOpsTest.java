package selfevaluation.LL.queue;

import com.selfevaluation.ops.queue.QueueOps;
import com.selfevaluation.base.Queue;
import com.selfevaluation.base.Queue.Node;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class QueueOpsTest {

    QueueOps queueOps;
    Node node;

    @BeforeMethod
    public void setup()
    {
        queueOps = new QueueOps(new Queue());
        node = new Node(0);
    }

    @Test
    public void whenEnQueueInvalidDataThenThrowException()
    {
        queueOps.enqueue(new Node(-1));
    }

    @Test
    public void whenEnQueueToNullQueuThenAddNode()
    {
        queueOps.setQueue(null);
        queueOps.enqueue(node);
        Assert.assertEquals(queueOps.getQueue().getSize(),1);
    }

    @Test
    public void whenEnQueueToMultiElementQueuThenAddNode()
    {
        queueOps.enqueue(node);
        queueOps.enqueue(new Node(1));
        Assert.assertEquals(queueOps.getQueue().getSize(),2);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void whenDeQueueFromNullQueueThenThrowException()
    {
        queueOps.setQueue(null);
        queueOps.dequeue();
    }

    @Test
    public void whenDeQueueFromSingleElementQueueThenRemoveElement()
    {
        queueOps.enqueue(node);
        queueOps.dequeue();
        Assert.assertEquals(queueOps.getQueue().getSize(),0);
        Assert.assertEquals(queueOps.getQueue().getFront(),null);
    }

    @Test
    public void whenDeQueueFromMultiElementQueueThenRemoveElement()
    {
        queueOps.enqueue(node);
        queueOps.enqueue(new Node(1));
        Node front = queueOps.dequeue();
        Assert.assertEquals(front,node);
        Assert.assertEquals(queueOps.getQueue().getSize(),1);
    }

    // ########### ########### ########### ###########

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenInvalidInputToGenerateBinaryNumberThenThrowException()
    {
        queueOps.generateBinaryNumber(-1);
    }

    @Test
    public void whenInput0ToGenerateBinaryNumberThenReturn0()
    {
        Assert.assertNull(queueOps.generateBinaryNumber(0));
    }

    @Test
    public void whenInput1ToGenerateBinaryNumberThenReturn1()
    {
        String[] generatedNumbers = queueOps.generateBinaryNumber(1);
        Assert.assertEquals(queueOps.generateBinaryNumber(1)[0], generatedNumbers[0]);
        Assert.assertEquals(queueOps.generateBinaryNumber(1).length, 1);
    }

    //@Test
    public void whenInputGreaterThan1ToGenerateBinaryNumberThenReturnAllGeneratedNumbers()
    {

    }
}
