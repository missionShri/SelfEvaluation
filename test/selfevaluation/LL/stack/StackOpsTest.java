package selfevaluation.LL.stack;

import com.selfevaluation.LL.stack.StackOps;
import com.selfevaluation.base.Stack;
import com.selfevaluation.base.Stack.Node;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StackOpsTest {

    StackOps stackOps;
    Node node;

    @BeforeMethod
    public void setup()
    {
        stackOps = new StackOps(new Stack());
        node = new Node(0);
    }

    // null element
    // null stack
    // empty stack
    // non-null & non-empty stack

    //null stack
    @Test(expectedExceptions = RuntimeException.class)
    public void whenStackIsNullThenThrowException()
    {
        stackOps.setStack(null);
        stackOps.isEmpty();
    }

    //non-null but empty(non-null stack with empty top (no-elements stack) which is different from null stack) stack
    @Test
    public void whenStackIsEmptyThenReturnTrue()
    {
        Assert.assertTrue(stackOps.isEmpty());
    }

    //isEmpty on non-null non-empty stack
    @Test
    public void whenStackIsNonEmptyThenReturnFalse()
    {
        stackOps.push(node);
        Assert.assertFalse(stackOps.isEmpty());
    }

    //push null element
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenPushNullElementToStackThenThrowException()
    {
        stackOps.push(null);
    }

    //push to null stack
    @Test
    public void whenPushElementToNullStackThenAddElement()
    {
        stackOps.setStack(null);
        stackOps.push(node);
        Assert.assertEquals(stackOps.peek(),node);
        Assert.assertFalse(stackOps.isEmpty());
        Assert.assertEquals(stackOps.getStack().getSize(),1);
    }

    //push to null or empty(non-null stack with empty top (no-elements stack) which is different from null stack) stack
    @Test
    public void whenPushElementToEmptyStackThenAddElement()
    {
        stackOps.push(node);
        Assert.assertEquals(stackOps.peek(),node);
        Assert.assertFalse(stackOps.isEmpty());
        Assert.assertEquals(stackOps.getStack().getSize(),1);
    }

    //push to non-empty stack
    @Test
    public void whenPushElementToNonEmptyStackThenAddElement()
    {
        stackOps.push(node);

        Node node1 = new Node(1);
        stackOps.push(node1);
        Assert.assertEquals(stackOps.peek(),node1);
        Assert.assertFalse(stackOps.isEmpty());
        Assert.assertEquals(stackOps.getStack().getSize(),2);
    }

    //null stack
    @Test(expectedExceptions = RuntimeException.class)
    public void whenPopElementFromNullStackThrowException()
    {
        stackOps.setStack(null);
        stackOps.pop();
    }

    //empty stack
    @Test(expectedExceptions = RuntimeException.class)
    public void whenPopElementFromEmptyStackThrowException()
    {
        stackOps.pop();
    }

    //non-empty, non-null but 1-element stack
    @Test
    public void whenPopElementFromSingleElementStackThenReturnElement()
    {
        stackOps.push(node);
        Node poppedElement = stackOps.pop();
        Assert.assertTrue(stackOps.isEmpty());
        Assert.assertEquals(poppedElement,node);
    }

    //non-empty, non-null but multi-element stack
    @Test
    public void whenPopElementFromMultiElementStackThenReturnElement()
    {
        stackOps.push(node);
        Node node1 = new Node(1);
        stackOps.push(node1);

        Node poppedElement = stackOps.pop();
        Assert.assertFalse(stackOps.isEmpty());
        Assert.assertEquals(poppedElement,node1);
        Assert.assertEquals(stackOps.getStack().getSize(),1);
    }
}
