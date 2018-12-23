package selfevaluation.LL.stack;

import com.selfevaluation.LL.stack.OperationName;
import com.selfevaluation.LL.stack.StackOps;
import com.selfevaluation.base.Stack;
import com.selfevaluation.base.Stack.Node;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

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
        stackOps.isEmpty(stackOps.getStack());
    }

    //non-null but empty(non-null stack with empty top (no-elements stack) which is different from null stack) stack
    @Test
    public void whenStackIsEmptyThenReturnTrue()
    {
        Assert.assertTrue(stackOps.isEmpty(stackOps.getStack()));
    }

    //isEmpty on non-null non-empty stack
    @Test
    public void whenStackIsNonEmptyThenReturnFalse()
    {
        stackOps.push(node,stackOps.getStack());
        Assert.assertFalse(stackOps.isEmpty(stackOps.getStack()));
    }

    //push null element
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenPushNullElementToStackThenThrowException()
    {
        stackOps.push(null,stackOps.getStack());
    }

    //push to null stack
    @Test
    public void whenPushElementToNullStackThenAddElement()
    {
        stackOps.setStack(null);
        stackOps.push(node,stackOps.getStack());
        Assert.assertEquals(stackOps.peek(stackOps.getStack()),node);
        Assert.assertFalse(stackOps.isEmpty(stackOps.getStack()));
        Assert.assertEquals(stackOps.getStack().getSize(),1);
    }

    //push to null or empty(non-null stack with empty top (no-elements stack) which is different from null stack) stack
    @Test
    public void whenPushElementToEmptyStackThenAddElement()
    {
        stackOps.push(node,stackOps.getStack());
        Assert.assertEquals(stackOps.peek(stackOps.getStack()),node);
        Assert.assertFalse(stackOps.isEmpty(stackOps.getStack()));
        Assert.assertEquals(stackOps.getStack().getSize(),1);
    }

    //push to non-empty stack
    @Test
    public void whenPushElementToNonEmptyStackThenAddElement()
    {
        stackOps.push(node,stackOps.getStack());

        Node node1 = new Node(1);
        stackOps.push(node1,stackOps.getStack());
        Assert.assertEquals(stackOps.peek(stackOps.getStack()),node1);
        Assert.assertFalse(stackOps.isEmpty(stackOps.getStack()));
        Assert.assertEquals(stackOps.getStack().getSize(),2);
    }

    //null stack
    @Test(expectedExceptions = RuntimeException.class)
    public void whenPopElementFromNullStackThrowException()
    {
        stackOps.setStack(null);
        stackOps.pop(stackOps.getStack());
    }

    //empty stack
    @Test(expectedExceptions = RuntimeException.class)
    public void whenPopElementFromEmptyStackThrowException()
    {
        stackOps.pop(stackOps.getStack());
    }

    //non-empty, non-null but 1-element stack
    @Test
    public void whenPopElementFromSingleElementStackThenReturnElement()
    {
        stackOps.push(node,stackOps.getStack());
        Node poppedElement = stackOps.pop(stackOps.getStack());
        Assert.assertTrue(stackOps.isEmpty(stackOps.getStack()));
        Assert.assertEquals(poppedElement,node);
    }

    //non-empty, non-null but multi-element stack
    @Test
    public void whenPopElementFromMultiElementStackThenReturnElement()
    {
        stackOps.push(node,stackOps.getStack());
        Node node1 = new Node(1);
        stackOps.push(node1,stackOps.getStack());

        Node poppedElement = stackOps.pop(stackOps.getStack());
        Assert.assertFalse(stackOps.isEmpty(stackOps.getStack()));
        Assert.assertEquals(poppedElement,node1);
        Assert.assertEquals(stackOps.peek(stackOps.getStack()),node);
        Assert.assertEquals(stackOps.getStack().getSize(),1);
    }

    // ########### ########### ########### ###########

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

    // ########### ########### ########### ###########

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenNullorEmptyInputThrowException()
    {
        stackOps.reverse(null);
        stackOps.reverse("   ");
    }

    @Test
    public void whenInputReturnReverse()
    {
        Assert.assertEquals(stackOps.reverse("amit"),"tima");
        Assert.assertEquals(stackOps.reverse("amit k rane"),"enar k tima");
    }

    // ########### ########### ########### ###########

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenNullOrInvalidInputsToTwoStacksInArrayThrowException()
    {
        stackOps.twoStacksInArray(-1,OperationName.ISEMPTY, null);
        stackOps.twoStacksInArray(1,null,Optional.of(-1));
        stackOps.twoStacksInArray(1,OperationName.PUSH, null);
    }

    @Test
    public void whenIsEmptyOnEmptyStack1ThenReturnOne()
    {
        Assert.assertEquals(Optional.of(stackOps.twoStacksInArray(1, OperationName.ISEMPTY, null).get()),Optional.of(0));
    }

    @Test
    public void whenIsEmptyOnNonEmptyStack1ThenReturnEmpty()
    {
        stackOps.twoStacksInArray(1,OperationName.PUSH,Optional.of(1));
        Assert.assertFalse(stackOps.twoStacksInArray(1, OperationName.ISEMPTY, null).isPresent());
    }

    @Test
    public void whenIsEmptyOnEmptyStack2ThenReturnOne()
    {
        Assert.assertEquals(Optional.of(stackOps.twoStacksInArray(2, OperationName.ISEMPTY, null).get()),Optional.of(0));
    }

    @Test
    public void whenIsEmptyOnNonEmptyStack2ThenReturnEmpty()
    {
        stackOps.twoStacksInArray(2,OperationName.PUSH,Optional.of(1));
        //stackOps.twoStacksInArray(2,OperationName.POP,null);
        Assert.assertFalse(stackOps.twoStacksInArray(2, OperationName.ISEMPTY, null).isPresent());
    }

    @Test
    public void whenPeekOnEmptyStack1ThenReturnEmpty()
    {
        Assert.assertFalse(stackOps.twoStacksInArray(1, OperationName.PEEK, null).isPresent());
    }

    @Test
    public void whenPeekOnNonEmptyStack1ThenReturnElement()
    {
        stackOps.twoStacksInArray(1,OperationName.PUSH,Optional.of(2));
        Assert.assertEquals(stackOps.twoStacksInArray(1, OperationName.PEEK, null).get().intValue(),2);
    }

    @Test
    public void whenPeekOnEmptyStack2ThenReturnEmpty()
    {
        Assert.assertFalse(stackOps.twoStacksInArray(2, OperationName.PEEK, null).isPresent());
    }

    @Test
    public void whenPeekOnNonEmptyStack2ThenReturnElement()
    {
        stackOps.twoStacksInArray(2,OperationName.PUSH,Optional.of(3));
        Assert.assertEquals(stackOps.twoStacksInArray(2, OperationName.PEEK, null).get().intValue(),3);
    }

    @Test
    public void whenPushOnEmptyStack1ThenReturn0()
    {
        Assert.assertEquals(stackOps.twoStacksInArray(1, OperationName.PUSH, Optional.of(1)).get().intValue(),0);
    }

    @Test
    public void whenPushOnNonEmptyStack1ThenReturnElement()
    {
        stackOps.twoStacksInArray(1,OperationName.PUSH,Optional.of(2));
        Assert.assertEquals(stackOps.twoStacksInArray(1, OperationName.PUSH, Optional.of(2)).get().intValue(),0);
    }

    @Test
    public void whenPushOnEmptyStack2ThenReturn0()
    {
        Assert.assertEquals(stackOps.twoStacksInArray(2, OperationName.PUSH, Optional.of(2)).get().intValue(),0);
    }

    @Test
    public void whenPushOnNonEmptyStack2ThenReturnElement()
    {
        stackOps.twoStacksInArray(2,OperationName.PUSH,Optional.of(3));
        Assert.assertEquals(stackOps.twoStacksInArray(2, OperationName.PEEK, Optional.of(2)).get().intValue(),3);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void whenPopOnEmptyStack1ThenThrowException()
    {
        stackOps.twoStacksInArray(1, OperationName.POP, null);
    }

    @Test
    public void whenPopOnNonEmptyStack1ThenReturnElement()
    {
        stackOps.twoStacksInArray(2,OperationName.PUSH,Optional.of(2));
        Assert.assertEquals(stackOps.twoStacksInArray(2, OperationName.POP, null).get().intValue(),2);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void whenPopOnEmptyStack2ThenThrowException()
    {
        stackOps.twoStacksInArray(2, OperationName.POP, null);
    }

    @Test
    public void whenPopOnNonEmptyStack2ThenReturnElement()
    {
        stackOps.twoStacksInArray(2,OperationName.PUSH,Optional.of(3));
        Assert.assertEquals(stackOps.twoStacksInArray(2, OperationName.POP, null).get().intValue(),3);
    }

    // ########### ########### ########### ###########
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenNullOrEmptyInputToIsMismatchThrowException()
    {
        stackOps.isMisMatch(null);
        stackOps.isMisMatch("");
    }

    @Test
    public void whenBalancedInputToIsMismatchThenReturnTrue()
    {
        Assert.assertTrue(stackOps.isMisMatch("(a)"));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenUnBalancedInputToIsMismatch1ThenReturnFalse()
    {
        stackOps.isMisMatch("))((");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenUnBalancedInputToIsMismatch2ThenReturnFalse()
    {
        Assert.assertFalse(stackOps.isMisMatch("()"));
    }
    // ########### ########### ########### ###########

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenNullOrEmptyInputToNextGreaterElementThrowException()
    {
        stackOps.nextGreaterElement(null);
        stackOps.nextGreaterElement(new int[] {});
    }

    @Test
    public void whenNextGreaterElementThenFindNGE()
    {
        int[] returnValue = stackOps.nextGreaterElement(new int[]{4,5,2,25});
        for (int i = 0; i <returnValue.length; i++) {
            System.out.print("\t"+returnValue[i]);
        }
        System.out.println();

        returnValue =  stackOps.nextGreaterElement(new int[] {13,7,6,12});
        for (int i = 0; i <returnValue.length; i++) {
            System.out.print("\t"+returnValue[i]);
        }
        System.out.println();


        returnValue =  stackOps.nextGreaterElement(new int[] {11, 13, 21, 3});
        for (int i = 0; i <returnValue.length; i++) {
            System.out.print("\t"+returnValue[i]);
        }
        System.out.println();
    }

    // ########### ########### ########### ###########

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenNullOrEmptyInputToRecursiveReverseThrowException()
    {
        stackOps.recursiveReverse(null,0);
        stackOps.recursiveReverse(new char[] {},0);
    }

    @Test
    public void whenInputToRecursiveReverse()
    {
        char[] returnValue =  stackOps.recursiveReverse(new char[] {'a','m','e','i','t'},0);
        for (int i = 0; i <returnValue.length; i++) {
            System.out.print("\t"+returnValue[i]);
        }
        System.out.println();

        returnValue =  stackOps.recursiveReverse(new char[] {'a','m','i','t',' ','k',' ','r','a','n','e'},0);
        for (int i = 0; i <returnValue.length; i++) {
            System.out.print("\t"+returnValue[i]);
        }
        System.out.println();
    }
    // ########### ########### ########### ###########
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenNullOrEmptyInputToRecursiveReverseStackThrowException()
    {
        stackOps.recursiveReverseStack(null);
        stackOps.recursiveReverseStack(new Stack());
    }

    @Test
    public void whenRecursiveReverseStackThenReverse()
    {
        stackOps.push(node,stackOps.getStack());
        stackOps.push(new Node(1),stackOps.getStack());
        stackOps.push(new Node(2),stackOps.getStack());
        stackOps.push(new Node(3),stackOps.getStack());

        Stack returnStack = stackOps.recursiveReverseStack(stackOps.getStack());
        while(!stackOps.isEmpty(returnStack))
        {
            System.out.print("\t"+stackOps.pop(returnStack));
        }
        System.out.println();
    }

    // ########### ########### ########### ###########
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenNullOrEmptyInputToPushKeepingTrackOfMinThenThrowException()
    {
        stackOps.pushKeepingTrackOfMin(null);
    }

    @Test
    public void whenInputToPushKeepingTrackOfMinToNullStackThenAddElement()
    {
        stackOps.setStack(null);
        stackOps.pushKeepingTrackOfMin(node);
        Assert.assertNotNull(stackOps.getStack().getTop().getData());
        Assert.assertEquals(stackOps.getStack().getTop().getData(),stackOps.getMinStack().getTop().getData());
    }

    @Test
    public void whenInputToPushKeepingTrackOfMinToSingleElementStackThenAddElement()
    {
        stackOps.pushKeepingTrackOfMin(node);
        stackOps.pushKeepingTrackOfMin(new Node(1));
        Assert.assertEquals(stackOps.getStack().getTop().getData(),1);
        Assert.assertEquals(stackOps.getMinStack().getTop().getData(),0);
    }

    @Test
    public void whenInputToPushKeepingTrackOfMinToNonSingleElementStackThenAddElement()
    {
        stackOps.pushKeepingTrackOfMin(new Node(2));
        stackOps.pushKeepingTrackOfMin(node);
        stackOps.pushKeepingTrackOfMin(new Node(1));
        Assert.assertEquals(stackOps.getStack().getTop().getData(),1);
        Assert.assertEquals(stackOps.getMinStack().getTop().getData(),0);
    }

    // ########### ########### ########### ###########

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenPopKeepingTrackOfMinFromNullStackThenThrowException() throws Exception
    {
        stackOps.setStack(null);
        stackOps.popKeepingTrackOfMin();
    }

    @Test()
    public void whenPopKeepingTrackOfMinFromSingleElementStackThenReturnElement() throws Exception
    {
        stackOps.pushKeepingTrackOfMin(node);
        Node node = stackOps.popKeepingTrackOfMin();
        Assert.assertEquals(node.getData(),0);
        Assert.assertNull(stackOps.getMinStack().getTop());
    }

    @Test()
    public void whenPopKeepingTrackOfMinFromNonSingleElementStackThenReturnElement() throws Exception
    {
        stackOps.pushKeepingTrackOfMin(new Node(1));
        stackOps.pushKeepingTrackOfMin(node);
        stackOps.pushKeepingTrackOfMin(new Node(2));

        Assert.assertEquals(stackOps.getMinStack().getTop().getData(),0);
        stackOps.popKeepingTrackOfMin();
        Assert.assertEquals(stackOps.getStack().getTop().getData(),0);
        Assert.assertEquals(stackOps.getMinStack().getTop().getData(),0);

        stackOps.popKeepingTrackOfMin();
        Assert.assertEquals(stackOps.getStack().getTop().getData(),1);
        Assert.assertEquals(stackOps.getMinStack().getTop().getData(),1);
    }

    @Test()
    public void whenKeepingMinTrackThenPerformAllOperationsInConstantTime() throws Exception
    {
        stackOps.pushKeepingTrackOfMin(new Node(1));
        stackOps.pushKeepingTrackOfMin(new Node(2));
        stackOps.pushKeepingTrackOfMin(new Node(3));

        stackOps.pushKeepingTrackOfMin(node);

        Assert.assertEquals(stackOps.getMinStack().getTop().getData(),0);

        stackOps.popKeepingTrackOfMin();
        Assert.assertEquals(stackOps.getMinStack().getTop().getData(),1);

        Assert.assertFalse(stackOps.isEmpty(stackOps.getStack()));
    }

    // ########### ########### ########### ###########

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenNullInputPushingToStackUsingTwoQueuesThenThrowException()
    {
        stackOps.pushUsingTwoQueues(null, null);
    }

    @Test()
    public void whenPushingToStackUsingTwoQueuesToEmptyStackThenAddElement()
    {
        stackOps.pushUsingTwoQueues(node,stackOps.getAuxStack());
    }

    @Test()
    public void whenPushingToStackUsingTwoQueuesToNonEmptyStackThenAddElement()
    {
        stackOps.pushUsingTwoQueues(node,stackOps.getAuxStack());
        stackOps.pushUsingTwoQueues(new Node(1),stackOps.getAuxStack());
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void whenPopToStackUsingTwoQueuesToEmptyStackThenThrowException()
    {
        stackOps.popUsingTwoQueues(stackOps.getAuxStack());
    }

    @Test()
    public void whenPopToStackUsingTwoQueuesToSingleStackThenPopElement()
    {
        stackOps.pushUsingTwoQueues(node,stackOps.getAuxStack());
        stackOps.popUsingTwoQueues(stackOps.getAuxStack());
    }

    @Test()
    public void whenPopToStackUsingTwoQueuesToNonSingleStackThenPopElement()
    {
        stackOps.pushUsingTwoQueues(node,stackOps.getAuxStack());
        stackOps.pushUsingTwoQueues(new Node(1),stackOps.getAuxStack());
        stackOps.pushUsingTwoQueues(new Node(2),stackOps.getAuxStack());

        Assert.assertEquals(stackOps.popUsingTwoQueues(stackOps.getAuxStack()).getData(),2);
        Assert.assertEquals(stackOps.popUsingTwoQueues(stackOps.getAuxStack()).getData(),1);
    }

    // ########### ########### ########### ###########

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenPushNullInputToPushTrackingMiddleElementThenThrowException()
    {
        stackOps.pushTrackingMiddleElement(null,stackOps.getStack());
    }

    @Test()
    public void whenPushInputToEmptyPushTrackingMiddleElementThenAddElement()
    {
        stackOps.pushTrackingMiddleElement(node,stackOps.getStack());
        Assert.assertEquals(stackOps.getDoubleLinkedListOps().getDoubleLinkedList().getHead().getData(),node.getData());
    }

    @Test()
    public void whenPushInputToNonEmptyPushTrackingMiddleElementThenAddElement()
    {
        stackOps.pushTrackingMiddleElement(node,stackOps.getStack());
        stackOps.pushTrackingMiddleElement(new Node(1),stackOps.getStack());
        stackOps.pushTrackingMiddleElement(new Node(2),stackOps.getStack());
        Assert.assertEquals(stackOps.getDoubleLinkedListOps().getDoubleLinkedList().getHead().getData(),2);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void whenFindMiddleInNullStackThenThrowException()
    {
        Assert.assertEquals(stackOps.findMiddle(), 0);
    }

    @Test()
    public void whenFindMiddleInSingleElementStackThenGetMiddle()
    {
        stackOps.pushTrackingMiddleElement(node,stackOps.getStack());
        Assert.assertEquals(stackOps.findMiddle(), 0);
    }

    @Test()
    public void whenFindMiddleInNonSingleElementStackThenGetMiddle()
    {
        stackOps.pushTrackingMiddleElement(node,stackOps.getStack());
        stackOps.pushTrackingMiddleElement(new Node(1),stackOps.getStack());
        stackOps.pushTrackingMiddleElement(new Node(2),stackOps.getStack());
        stackOps.pushTrackingMiddleElement(new Node(3),stackOps.getStack());
        stackOps.pushTrackingMiddleElement(new Node(4),stackOps.getStack());
        Assert.assertEquals(stackOps.findMiddle(), 2);
    }

    // ########### ########### ########### ###########

    @Test(expectedExceptions = RuntimeException.class)
    public void whenPopTrackingMiddleElementStackFromEmptyStackThenThrowException()
    {
        stackOps.popTrackingMiddleElement();
    }

    @Test()
    public void whenPopTrackingMiddleElementStackFromSingleStackThenReturnElement()
    {
        stackOps.pushTrackingMiddleElement(node,stackOps.getStack());
        Assert.assertEquals(stackOps.popTrackingMiddleElement(),node.getData());
        Assert.assertEquals(stackOps.getDoubleLinkedListOps().getDoubleLinkedList().getSize(),0);
        Assert.assertNull(stackOps.getMiddle());
    }

    @Test()
    public void whenPopTrackingMiddleElementStackFromStackThenReturnElement()
    {
        stackOps.pushTrackingMiddleElement(node,stackOps.getStack());
        stackOps.pushTrackingMiddleElement(new Node(1),stackOps.getStack());
        stackOps.pushTrackingMiddleElement(new Node(2),stackOps.getStack());
        stackOps.pushTrackingMiddleElement(new Node(3),stackOps.getStack());
        stackOps.pushTrackingMiddleElement(new Node(4),stackOps.getStack());
        Assert.assertEquals(stackOps.popTrackingMiddleElement(),4);
    }

    // ########### ########### ########### ###########

    @Test(expectedExceptions = RuntimeException.class)
    public void whenDeleteMiddleElementFromEmptyStackThenThrowException()
    {
        stackOps.deleteMiddle();
    }

    @Test()
    public void whenDeleteMiddleFromSingleStackThenReturnElement()
    {
        stackOps.pushTrackingMiddleElement(node,stackOps.getStack());
        Assert.assertEquals(stackOps.deleteMiddle(),node.getData());
        Assert.assertEquals(stackOps.getDoubleLinkedListOps().getDoubleLinkedList().getSize(),0);
        Assert.assertNull(stackOps.getMiddle());
    }

    @Test()
    public void whenDeleteMiddleFromMiddleElementFromStackThenReturnElement()
    {
        stackOps.pushTrackingMiddleElement(node,stackOps.getStack());
        stackOps.pushTrackingMiddleElement(new Node(1),stackOps.getStack());
        stackOps.pushTrackingMiddleElement(new Node(2),stackOps.getStack());
        stackOps.pushTrackingMiddleElement(new Node(3),stackOps.getStack());
        stackOps.pushTrackingMiddleElement(new Node(4),stackOps.getStack());
        Assert.assertEquals(stackOps.deleteMiddle(),2);
        Assert.assertEquals(stackOps.getDoubleLinkedListOps().getDoubleLinkedList().getSize(),4);
    }

    // ########### ########### ########### ###########

}
