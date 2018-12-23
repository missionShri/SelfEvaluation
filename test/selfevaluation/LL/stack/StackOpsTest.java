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
        Assert.assertEquals(stackOps.peek(),node);
        Assert.assertEquals(stackOps.getStack().getSize(),1);
    }

    // ###########

    //null or empty
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenNullorEmptyInfixExpThenThrowException()
    {
        stackOps.infixToPostfix(null);
    }

    //null or empty
    @Test
    public void whenSingleInfixExpThenReturn()
    {
        stackOps.infixToPostfix("a");
    }

    @Test
    public void whenNonSingleInfixExpThenReturn()
    {
        Assert.assertEquals(stackOps.infixToPostfix("a+b*c"),"abc*+");
        Assert.assertEquals(stackOps.infixToPostfix("a+b*c+d"),"abc*+d+");
        //Assert.assertEquals(stackOps.infixToPostfix("a+b*c-d+e"),);
    }

    @Test
    public void whenInfixExpContainingOperandsOnlyThenReturnAsIs()
    {
        Assert.assertEquals(stackOps.infixToPostfix("abcde"),"abcde");
    }

    @Test
    public void whenInfixExpContainingOperandsAndSingleOperatorThenPostFixExpression()
    {
        Assert.assertEquals(stackOps.infixToPostfix("a+b"),"ab+");
    }

    @Test
    public void whenInfixExpContainingOperandsAndMultipleOperatorThenPostFixExpression()
    {
        Assert.assertEquals(stackOps.infixToPostfix("a+b*c+d"),"abc*+d+");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenInfixExpContainingMisMatchingBracketThenThrowException()
    {
        stackOps.infixToPostfix("a+b(c+d");
        stackOps.infixToPostfix("(a+b+c+d");

        stackOps.infixToPostfix("((a+b)+c+d");
        stackOps.infixToPostfix("(a+b))+c+d");
    }

    @Test
    public void whenInfixExpBracketThenReturnPostFix()
    {
        Assert.assertEquals(stackOps.infixToPostfix("((A*B)+(C/D))"), "((AB*)(CD/)+)");
        Assert.assertEquals(stackOps.infixToPostfix("((A*(B+C))/D)"), "((A(BC+)*)D/)");
        Assert.assertEquals(stackOps.infixToPostfix("(A*(B+(C/D)))"), "(A(B(CD/)+)*)");
    }

    // ###########

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenNullPostfixExpThenThrowException()
    {
        stackOps.evaluatePostfix(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenEmptyPostfixExpThenThrowException()
    {
        stackOps.evaluatePostfix("");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenTwoCharPostfixExpThenThrowException()
    {
        stackOps.evaluatePostfix("ab");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenPostfixExpContainingOnlyOperandsThenThrowException()
    {
        stackOps.evaluatePostfix("1234");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenIncompletePostfixExpContainingThenThrowException()
    {
        stackOps.evaluatePostfix("12+4");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenPostfixExpContainingOperandsThenThrowException()
    {
        //stackOps.evaluatePostfix("a++");
        stackOps.evaluatePostfix("ab++");
    }

    @Test
    public void whenNonBracketOfPostfixExpThenReturnValue()
    {
        Assert.assertEquals(stackOps.evaluatePostfix("ab+"),195);
        Assert.assertEquals(stackOps.evaluatePostfix("231*+9-"),-4);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenPostfixExpContainingMisMatchingBracketThenThrowException()
    {
        stackOps.evaluatePostfix("(((34*)(82/)+)");
        stackOps.evaluatePostfix("((3(48+)*)2/))");

        stackOps.evaluatePostfix("(3(4(82/)+)))*)");
        stackOps.evaluatePostfix("(3(4(82/))+)*)");
    }

    @Test
    public void whenBracketOfPostfixExpThenReturnValue()
    {
        Assert.assertEquals(stackOps.evaluatePostfix("((34*)(82/)+)"),16);
        Assert.assertEquals(stackOps.evaluatePostfix("((3(48+)*)2/)"), 18);
        Assert.assertEquals(stackOps.evaluatePostfix("(3(4(82/)+)*)"), 24);
        Assert.assertEquals(stackOps.evaluatePostfix("12+2/5*7+"), 12);
    }
}
