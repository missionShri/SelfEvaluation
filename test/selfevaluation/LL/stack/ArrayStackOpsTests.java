package selfevaluation.LL.stack;

import com.selfevaluation.LL.stack.ArrayStackOps;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ArrayStackOpsTests {

    ArrayStackOps arrayStackOps;
    int element;

    @BeforeMethod
    public void setup()
    {
        arrayStackOps = new ArrayStackOps();
        element = 0;
    }

    //isEmpty null stack
    @Test(expectedExceptions = RuntimeException.class)
    public void whenIsEmptyOnNullStackThrowException()
    {
        arrayStackOps.setStack(null);
        arrayStackOps.isEmpty();
    }

    //isEmpty empty stack
    @Test
    public void whenIsEmptyOnEmptyStackThrowException()
    {
        Assert.assertTrue(arrayStackOps.isEmpty());
    }

    //- non-null, non-empty
    @Test
    public void whenIsEmptyOnNonEmptyStackThrowException()
    {
        arrayStackOps.push(element);
        Assert.assertFalse(arrayStackOps.isEmpty());
    }

    //Invalid element
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenInvalidElementPushToStackThrowException()
    {
        arrayStackOps.push(-1);
    }

    //Null Stack
    @Test
    public void whenElementPushedToNullStackThenAdd()
    {
        arrayStackOps.setStack(null);
        arrayStackOps.push(element);

        Assert.assertFalse(arrayStackOps.isEmpty());
        Assert.assertEquals(arrayStackOps.getTop(),0);
        Assert.assertEquals(arrayStackOps.getStack()[arrayStackOps.getTop()],element);
    }

    //push to null or empty(non-null stack with empty top (no-elements stack) which is different from null stack) stack
    @Test
    public void whenElementPushedToEmptyStackThenAdd()
    {
        arrayStackOps.push(element);

        Assert.assertFalse(arrayStackOps.isEmpty());
        Assert.assertEquals(arrayStackOps.getTop(),0);
        Assert.assertEquals(arrayStackOps.getStack()[arrayStackOps.getTop()],element);
    }

    //Non-Empty Stack
    @Test
    public void whenElementPushedFromNonEmptyStackThenAdd()
    {
        arrayStackOps.push(element);
        arrayStackOps.push(2);

        Assert.assertFalse(arrayStackOps.isEmpty());
        Assert.assertEquals(arrayStackOps.getTop(),1);
        Assert.assertEquals(arrayStackOps.getStack()[arrayStackOps.getTop()],2);
    }

    //Null Stack
    @Test(expectedExceptions = RuntimeException.class)
    public void whenElementPoppedFromNullStackThenAdd()
    {
        arrayStackOps.setStack(null);
        arrayStackOps.pop();

    }

    //push to null or empty(non-null stack with empty top (no-elements stack) which is different from null stack) stack
    @Test(expectedExceptions = RuntimeException.class)
    public void whenElementPoppedFromEmptyStackThenAdd()
    {
        arrayStackOps.pop();
    }

    //Non-Empty Stack
    @Test
    public void whenElementPoppedFromSingleStackThenAdd()
    {
        arrayStackOps.push(element);
        int poppedElement = arrayStackOps.pop();

        Assert.assertTrue(arrayStackOps.isEmpty());
        Assert.assertEquals(arrayStackOps.getTop(),-1);
    }

    //Non-Empty Stack
    @Test
    public void whenElementPoppedFromNonEmptyStackThenAdd()
    {
        arrayStackOps.push(element);
        arrayStackOps.push(2);

        int poppedElement = arrayStackOps.pop();

        Assert.assertFalse(arrayStackOps.isEmpty());
        Assert.assertEquals(arrayStackOps.getTop(),0);
        Assert.assertEquals(arrayStackOps.getStack()[arrayStackOps.getTop()],element);
    }


}
