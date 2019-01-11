package com.selfevaluation.LL.stack;

import com.selfevaluation.LL.DoubleLinkedListOps;
import com.selfevaluation.LL.LLOps;
import com.selfevaluation.base.DoubleLinkedList;
import com.selfevaluation.base.LinkedList;
import com.selfevaluation.base.Stack;
import com.selfevaluation.base.Stack.Node;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Setter
@Getter
public class StackOps {

    private Stack stack;

    private int top1, top2;
    private int[] stackArray;

    private List<Node> queue1, queue2;

    private LinkedList list1, list2;
    private LLOps llOps1;
    private LLOps llOps2;

    private Stack minStack;
    private DoubleLinkedListOps doubleLinkedListOps;
    private DoubleLinkedList.Node middle;

    int totalStacks, currentTracker;
    int[] kStacks, topTracker, nextTracker;

    //@Getter
    private Stack auxStack;

    public StackOps(Stack stack)
    {
        this.stack = stack;
        if(this.stack!=null)
        {
            this.stack.setTop(null);
        }
        this.stackArray  = new int[100];
        Arrays.fill(stackArray, -1);
        top1 = -1;
        top2 = 100;

        totalStacks = 10;
        currentTracker = -1;
        kStacks = new int[100];
        Arrays.fill(kStacks,-1);
        topTracker = new int[totalStacks];
        Arrays.fill(topTracker,-1);
        nextTracker = new int[100];
        Arrays.fill(nextTracker,-1);
    }

    /* LIFO seems to be simulated using. Sometimes the question is direct.
     Other cases, depending on the use-case and more importantly constraints, determine the structure to use

        - List representation
        - Array representation

        - using 1 queue (LL with fifo discipline)
        - 2 queues ()
        - Priority queue

        - Array
        - K stacks in 1 array (using multiple arrays)

        - List  (with merge-able stacks)
        - DLL (if operations on middle )
        - Multiple stacks for 1 stack (to keep track of min)*/

    //O(1) time requirement implies extra space.
    /* With Lifo O(1) time constraints  :
        - pop
        - push

        - min/max
        - middle
        - */

    /* With Lifo O(1) time & O(1) space constraints  :
        - min : in that crazy formula : https://www.geeksforgeeks.org/design-a-stack-that-supports-getmin-in-o1-time-and-o1-extra-space */

    //isEmpty
    public boolean isEmpty(Stack stack) {
        //distinguishing between null and empty case here is critical unlike add/delete since we want to check for emptiness
        if(stack==null)
        {
            throw new  RuntimeException("Stack is null or empty");
        }
        return (stack.getTop()==null);
    }

    public Node peek(Stack stack)
    {
        //zero element (null or empty)
        if(stack==null || isEmpty(stack))
        {
            throw new  RuntimeException("Stack is null or empty");
        }
        return stack.getTop();
    }

    //push
    public void push(Node node, Stack stack) {
        if(node==null)
        {
            throw new  IllegalArgumentException("Invalid input");
        }

        //Zero-element case (null or empty)
        if(stack==null || isEmpty(stack))
        {
            if(stack==null)
            {
                stack = new Stack();
                setStack(stack);
            }

            stack.setTop(node);
            //*********** just not setting the node to null, was causing infinite loops  ***********
            node.setNext(null);
            incrementSize(stack);
            return;
        }

        //current = first element
        Node current = stack.getTop();
        //no prev needed

        //much like insertAtFront, no-looping needed since we have access for the head pointer

        //########### WRONG ###########
//        current.setNext(node);
//        stack.setTop(current);
        //########### WRONG ###########

        //########### RIGHT ###########
        node.setNext(current);
        stack.setTop(node);
        //########### RIGHT ###########
        incrementSize(stack);
        return;
    }

    public Node pop(Stack stack) {
        //null or empty
        if(stack==null || isEmpty(stack))
        {
            throw  new RuntimeException("Stack is null or empty");
        }

        //single-element case
        if(stack.getTop().getNext()==null)
        {
            Node current = stack.getTop();
            stack.setTop(null);
            decrementSize(stack);
            return current;
        }

        //current = first element
        Node current = stack.getTop();
        stack.setTop(current.getNext());
        decrementSize(stack);
        return current;
    }

        //Using extra-space since its a string
    public String reverse(String input)
    {
        //null or empty
        if(input==null || input.trim().isEmpty())
        {
            throw new IllegalArgumentException("Invalid input");
        }
        StringBuilder reversedString = new StringBuilder();
        for (int i = input.length()-1; i>=0 ; i--) {
            reversedString.append((char)input.codePointAt(i));
        }
        return reversedString.toString();
    }

    //In-place & recursive
    public char[] recursiveReverse(char[] input, int i)
    {
        //null or empty
        if(input == null || input.length == 0)
        {
            throw new IllegalArgumentException("Invalid input");

        }

        //Recursion limiting condition
        if(i>=input.length/2)
        {
            return input;
        }
            char tmp = input[(input.length-1)-i];
            input[(input.length-1)-i] = input[i];
            input[i] = tmp;
            recursiveReverse(input,++i);
            return input;
    }

    //Using extra space with a new stack
    public Stack recursiveReverseStack(Stack inputStack)
    {
        //null or empty
        if(inputStack == null || inputStack.getTop()== null)
        {
            return stack;
            //throw new IllegalArgumentException("Invalid input");
        }
        push(pop(inputStack),stack);
        recursiveReverseStack(inputStack);
        return stack;
    }

    //In-place with no extra stack but intermidiate place of O(n)
    public void recursiveReverseStack()
    {
        //whats the limiting condition
        int times = 0;

        if(stack ==null || isEmpty(stack))
        {
            return;
        }

            //popFromTop
            Node x = pop(stack);
            recursiveReverseStack();

            //This will push in same order, what we need is reverse/inverted push
            //push(x,stack);
            pushAtBottom(x);
    }

    private void pushAtBottom(Node x) {

        //pop all elements, insert and then push back all the popped elements
        if(stack==null || stack.getTop()==null)
        {
            push(x,stack);
            x.setNext(null);
            // If recursion, returning is critical, or else infinite loop
            return;
        }

        Node y = pop(stack);
        pushAtBottom(x);
        push(y,stack);
    }

    //TODO : Tricky
    //In-place with no extra stack but intermediate place of O(n)
    public void recursiveSortStack()
    {
        if(stack ==null || isEmpty(stack))
        {
            // If recursion, returning is critical, or else infinite loop
            return;
        }

        //popFromTop
        Node x = pop(stack);
        recursiveSortStack();

        //This will push in same order, what we need is reverse/inverted push
        //push(x,stack);

        //Option 1:
        //pushInSortedStackNonRecurrsion(x);

        //Option 2:
        pushInSortedStackWithRecurrsion(x);

    }

    public void pushInSortedStackWithRecurrsion(Node x) {

        //if x is greater than the top, just push it to the top of stack
        if(stack.getTop()==null || x.getData()>=stack.getTop().getData())
        {
            push(x,stack);
            // If recursion, returning is critical, or else infinite loop
            return;
        }

        Node y = pop(stack);
        pushInSortedStackWithRecurrsion(x);
        push(y,stack);
    }

    public void pushInSortedStackNonRecurrsion(Node x) {

        //if x is greater than the top, just push it to the top of stack
       if(stack.getTop()!=null && x.getData()>=stack.getTop().getData())
       {
            push(x,stack);
       }
        else
       {
           //keep popping & pushing to AuxStack until we hit an element for which x.data >= element.data
           while(stack.getTop()!=null && x.getData()<stack.getTop().getData())
           {
               if(auxStack==null)
               {
                   auxStack = new Stack();
               }
               //***IMPORTANT : don't push full nodes, since the next pointers will cause problems. ****
               //keep popping & pushing to AuxStack
               push(pop(stack),auxStack);
           }

           //Then push the element of interest
           push(x,stack);

           //Push back all the popped elements
           while (auxStack!=null && auxStack.getTop()!=null)
           {
               push(pop(auxStack),stack);
           }
           //set the auxStack back to null
           auxStack = null;
       }
    }

    //Good to use of Optional since input may not be present/needed for all ops. Not sure if it makes sense for the output
    public Optional<Integer> twoStacksInArray(int stackNumber, OperationName operationName, Optional<Integer> input)
    {
        if((stackNumber<=0 || stackNumber>2) || operationName==null || (operationName.equals(OperationName.PUSH) && (input.isPresent() && input.get()<0)))
        {
            throw new IllegalArgumentException("Invalid input");
        }

        Optional<Integer> element = Optional.empty();

        switch (operationName){
            //0 shows true or else empty
            case ISEMPTY:
                if(stackNumber==1 && top1==-1)
                {
                    element = Optional.of(0);
                }
                if(stackNumber ==2 && top2 == 100)
                {
                    element = Optional.of(0);
                }
                break;
            case PEEK:
                if(stackNumber ==1 && !twoStacksInArray(1,OperationName.ISEMPTY,null).isPresent())
                {
                    element = Optional.of(stackArray[top1]);
                }
                else if(stackNumber ==2 && !twoStacksInArray(2,OperationName.ISEMPTY,null).isPresent())
                {
                    element = Optional.of(stackArray[top2]);
                }
                break;
            case PUSH:
                //0 shows true or else empty
                if((stackNumber==1 && top1==99))
                {
                    throw new RuntimeException("Stack1 is full");
                }
                if((stackNumber ==2 && top2 == 0))
                {
                    throw new RuntimeException("Stack2 is full");
                }
                if(stackNumber==1 && top1<99 && stackArray[top1+1]==(-1))
                {
                    stackArray[++top1]=input.get();
                    element = Optional.of(0);
                }
                else if(stackNumber==2 && top2>0 && stackArray[top2-1]==(-1))
                {
                    stackArray[--top2]=input.get();
                    element = Optional.of(0);
                }
                break;
            case POP:
                if((stackNumber==1 && top1==-1))
                {
                    throw new RuntimeException("Stack1 is empty");
                }
                if(stackNumber ==2 && top2 == 100)
                {
                    throw new RuntimeException("Stack2 is empty");
                }
                if(stackNumber==1 && top1>=0)
                {
                    element = Optional.of(stackArray[top1]);
                    --top1;
                }
                else if(stackNumber==2 && top2<=99)
                {
                    element = Optional.of(stackArray[top2]);
                    ++top2;
                }
                break;
            default:
                break;
        }

        return element;
    }

    //TODO : Tricky
    public boolean isMisMatch(String input)
    {
        int bracketOpenCount = 0;
        //null or empty
        if(input==null || input.trim().isEmpty())
        {
            throw new IllegalArgumentException("Invalid input");
        }

        boolean isLastCharOpenBracket = false;
        char[] inputArray = input.toCharArray();
        for (int i = 0; i < inputArray.length; i++) {
            if(inputArray[i]=='(')
            {
                isLastCharOpenBracket = true;
                bracketOpenCount++;
                continue;
            }
            if(inputArray[i]==')')
            {
                if(bracketOpenCount>0 && isLastCharOpenBracket==false)
                {
                    bracketOpenCount--;
                    continue;
                }
                else
                {
                    throw new IllegalArgumentException("Invalid input");
                }
            }
            else {
                isLastCharOpenBracket = false;
                continue;
            }
        }
        return (bracketOpenCount==0);
    }

    //Remember array is not sorted
    //We are trying to find the first greater element to the right, not the greatest or the smallest greatest
    public int[] nextGreaterElement(int[] input)
    {
        if(input==null || input.length ==0)
        {
            throw new IllegalArgumentException("Invalid input");
        }
        int[] nextGreaterElements = new int[input.length];
        Arrays.fill(nextGreaterElements, -1);

        nextGreaterElements[input.length-1] = -1;

        /*caught by {11, 13, 21, 3} case , where last element was smaller than second-last. Remember array is not sorted
          so last element may or may not be greater*/
        //nextGreaterElements[input.length-2] = input[input.length-1];

        for (int i = (input.length-2); i >= 0 ; i--) {
            // if immidiate-next element is greater than current, we are good
            if(input[i+1] >= input[i])
            {
                nextGreaterElements[i] = input[i+1];
                /*if(nextGreaterElements[i+1] >= input[i] && nextGreaterElements[i+1] >= input[i+1])
                {

                }
                else //if(nextGreaterElements[i+1] >= input[i] && nextGreaterElements[i+1] <= input[i+1])
                {
                    nextGreaterElements[i] = nextGreaterElements[i+1];
                }*/
            }
            else //if(input[i+1] < input[i])
            {
                /*if immediate-next element is not greater than current, then see if the next-greatest-element
                  which is representative for the rest of the stack greater than current*/
                if(nextGreaterElements[i+1] >= input[i])
                {
                    nextGreaterElements[i] = nextGreaterElements[i+1];
                }
                else
                {   //if not , then search for the first element in the next-greatest array (not the input array) to
                    // to find the nearest greater element
                    for (int j = (i+2); j < nextGreaterElements.length ; j++) {
                        if(nextGreaterElements[j] >= input[i])
                        {
                            nextGreaterElements[i] = nextGreaterElements[j];
                        }
                    }
                }
            }
        }
        return nextGreaterElements;
    }

    //TODO: *** Very Tricky ***
    public int evaluatePostfix(String postFixExp)
    {
        //null or empty
        if(postFixExp==null || postFixExp.trim().isEmpty() || postFixExp.length()<=2)
        {
            throw new IllegalArgumentException("Invalid input");
        }

        Node node = null;
        int bracketOperationCount = 0, value = 0;
        char token = '\0';

        //operand case (assuming limited operator support)
        List<Character> operatorList = new ArrayList<>(Arrays.asList('%','/','*','+','-'));

        //In order to avoid NPE before null check, better to initialize after input validation
        char[] postFixExpArray = postFixExp.toCharArray();
        //Traverse from left to right
        for (int i = 0; i < postFixExpArray.length; i++) {

            token = postFixExpArray[i];

            //Order of operations is important while looping: Operands, '(', ')', operators.
            // Then go for remaining elements in the stack checking for bad-input cases

            //operand case
            if(operatorList.contains(token))
            {
                if (evaluateExpressionAndPushValueToStack(token)) {
                    continue;
                }
            }
            // '(' case
            if(token == '(')
            {
                push(new Node('('),stack);
                //infix.append(token);
                bracketOperationCount++;
                continue;
            }
            // ')' case
            if(token == ')')
            {
                if(bracketOperationCount<1)
                {
                    throw new IllegalArgumentException("Incomplete brackets-of detected");
                }

                //if its first bracket-of, then the sub-expression would already be evaluated but our greedy approach above
                if (!isEmpty(stack) && ((char)peek(stack).getData())!='(' && !operatorList.contains((char)peek(stack).getData()))
                {
                    /*This is a BIG find. Popping value node and then pushing it back later below was causing inconsistent
                      results as it was getting the older references for node->next, even when stack size was 0.
                      Better to operate at primitive level than references*/
                    //node = pop();

                    value = pop(stack).getData();
                    if(!isEmpty(stack) && ((char)peek(stack).getData())=='(')
                    {
                        //Pop the '('
                        pop(stack);

                        /*This is a BIG find. Popping value node and then pushing it back later below was causing inconsistent
                          results as it was getting the older references for node->next, even when stack size was 0*/
                        //push(node);
                        push(new Node(value),stack);
                        bracketOperationCount --;
                        continue;
                    }
                }
            }

            //Important to have it here, since its only guarded by 'else' & not a specific if check
            //operator case
            else
            {
                if(Character.isDigit(token))
                {
                    //Character.getNumericValue only applies if the char value is 0-9
                    push(new Node(Character.getNumericValue(token)),stack);
                }
                else if (Character.isLetter(token))
                {
                    push(new Node(token),stack);
                }
                continue;
            }
        }

        //Bad input cases. If '(' are more, then bracketOpenCount would be >0 and if ')' are more, then bracketOpenCount <0.
        // So (!=0) is a good check
        if(bracketOperationCount!=0)
        {
            throw new IllegalArgumentException("Incomplete brackets-of detected");
        }
        //There is always 1 value expected in the stack.
        if(isEmpty(stack))
        {
            throw new IllegalArgumentException("Invalid input");
        }
        value = pop(stack).getData();

        //There is just 1 node expected in the stack.
        ////Unit tests indeed keep you humble. See how this issue was discovered as part of whenPostfixExpContainingOnlyOperandsThenThrowException
        if(!isEmpty(stack))
        {
            throw new IllegalArgumentException("Invalid input");
        }
        return value;
    }

    //TODO: *** Very Tricky ***
    public String infixToPostfix(String infixExp)
    {
        //null or empty
        if(infixExp==null || infixExp.trim().isEmpty())
        {
            throw new IllegalArgumentException("Invalid input");
        }
        //single-element case
        if(infixExp.length()==1)
        {
            return infixExp;
        }

        StringBuilder postFix = new StringBuilder();
        //this keeps track of bracketOps open count , which shall help us with mis-match
        int bracketOpenCount = 0;

        //operand case (assuming limited operator support)
        List<Character> operatorList = new ArrayList<>(Arrays.asList('%','/','*','+','-'));

        //In order to avoid NPE before null check, better to initialize after input validation
        char[] infixExpArray = infixExp.toCharArray();
        //Traverse from left to right
        for (int i = 0; i < infixExpArray.length; i++) {

            char token = infixExpArray[i] ;

            //Order of operations is important while looping: Operands, '(', ')', operators.
            // Then go for remaining elements in the stack checking for bad-input cases

            //operands
            if((token>='a' && token<='z') || (token>='A' && token<='Z'))
            {
                postFix.append(token);
                continue;
            }

            // '(' case
            if(token == '(')
            {
                /*trick here was to print-it out just like operand, but also to push it to a stack so that when we discover
                 ')' we would know where to de-limit*/
                postFix.append('(');
                push(new Node('('),stack);
                bracketOpenCount++;
                continue;
            }

            // ')' case
            if(token == ')')
            {
                while ((!isEmpty(stack) && stack.getTop().getData() != '('))
                {
                    postFix.append(popAndTransformToChar());
                }

                //first-condition-check
                //always check for emptiness before checking for the other condition, since you if reverse the order,
                // you will have to consider the empty case for the other case.
                if(isEmpty(stack))
                {
                    throw new IllegalArgumentException("Incomplete brackets-of detected");
                }

                //second-condition-check
                //')' is used to de-limit
                if(stack.getTop().getData() == '(')
                {
                    pop(stack);
                    bracketOpenCount--;
                    postFix.append(')'); //'(' is already appended while we push it to the stack
                }
                continue;
            }

            //operator with un-conditional-push case
            /*if  stack-top is empty or current top is scanned operator is greater than the precedence of the top operator in the stack
            , then push it*/
            if(isEmpty(stack) || (operatorList.contains(token) && isInputPrecedenceGreaterThanStackTop(token)))
            {
                push(new Node(token),stack);
                continue;
            }

            //operator with conditional-push case (pop until input precedence is not greater than stackTop)
            if((operatorList.contains(token) && !isInputPrecedenceGreaterThanStackTop(token)))
            {
                //'(' check is needed since the only time, we factor them for popping is in response to discovering ')'
                while (!isEmpty(stack) && !isInputPrecedenceGreaterThanStackTop(token) && !((char)peek(stack).getData()=='('))
                {
                    postFix.append(popAndTransformToChar());
                }
                push(new Node(token),stack);
                continue;
            }
        }

        //Bad input cases. If '(' are more, then bracketOpenCount would be >0 and if ')' are more, then bracketOpenCount <0.
        // So (!=0) is a good check
        if(bracketOpenCount!=0)
        {
            throw new IllegalArgumentException("Incomplete brackets-of detected");
        }

        //Pending operators
        while (!isEmpty(stack))
        {
            char operator = popAndTransformToChar();
            if((operatorList.contains(operator)))
            {
                postFix.append(operator);
            }
        }
        return postFix.toString();
    }

    //TODO: Tricky
    public void pushUsingTwoQueues(Node node,Stack auxStack) {
        if(node==null)
        {
            throw new IllegalArgumentException("Invalid input");
        }

        //Zero-or empty stack
        if(auxStack==null /*|| isEmpty(stackToPushTo)*/) {

            auxStack = new Stack();
            setAuxStack(auxStack);
            queue1 = new java.util.LinkedList();
            queue2 = new java.util.LinkedList();

            /* //the point is not to directly push to a stack but using two queues
            queue1.add(node);
            //no need to set any stack pointers, since we are simulating using queues */
        }

        //the point is not to directly push to a stack but using two queues
        queue1.add(node);
        //no need to set any stack pointers, since we are simulating using queues
    }

    //TODO: Tricky
    public Node popUsingTwoQueues(Stack auxStack) {
        if(queue1==null || queue1.size()==0)
        {
            throw  new RuntimeException("Stack is null or empty");
        }

        //Single element case
        if(queue1.size()==1)
        {
            return queue1.remove(0);
        }

        /* Move all but the last element to the other queue
           Also much better than looping in a for-loop on a size-decreasing list resulting in to concurrentModificationException
           and also since size is decreasing, just i++ will cause you to skip elements */
        do {
            queue2.add(queue1.remove(0));
        }while (queue1.size()>1);

        //return the only element left in queue1
        Node node =  queue1.remove(0);

        //Critical else, you would have to keep track of which queue is has elements and which one is aux
        List tmp = queue2;
        queue2 = queue1;
        queue1 = tmp;

        return node;
    }

    /**
     * TODO : Tricky
     *
     * @param data
     */
    public void pushToStackUsingOneQ(int data)
    {
        if(data<-1)
        {
            throw new IllegalArgumentException("Invalid input");
        }

        //Zero element or null stack
        if(queue1 ==null || queue1.isEmpty())
        {
            if(queue1==null)
            {
                queue1 = new java.util.LinkedList<>();
            }
            queue1.add(new Node(data));
            return;
        }

        //enqueue at the end of the queue
        queue1.add(new Node(data));

        //now deque from the front and enqueue everything but for the last element, to support O(1) LIFO pop,
        // but expensive push
        for(int i = 0; i< queue1.size()-1;i++)
        {
            queue1.add(queue1.remove(0));
        }
        return;
        }

    public int popFromStackUsingOneQ() {
        int data ;
        if(queue1==null || queue1.isEmpty())
        {
            throw new RuntimeException("Null or empty stack");
        }

        //Single element case
        if(queue1.size()==1)
        {
            data = queue1.remove(0).getData();
            queue1 = null;
            return data;
        }

        //Popping is O(1) due to heavy push
        return queue1.remove(0).getData();
    }

    //Insert to the priority queue with size counter::: pop should O(1) for minHeap with
    // (max counter has min value and therefore available for instant pop)
    public void pushToStackUsingPriority(int data)
    {
        if(data < 0)
        {
            throw new IllegalArgumentException("Invalid data");
        }

        //TODO : Implement the min heap
    }

    //TODO: Tricky
    /* Special DS -> Maintain two stacks in order to support getMin in O(1) time.
       If something is needed in constant time, its a given we need extra space to support O(1) constraint.
       Also only push to the minStack only when there is an update to the newMin. Will help in space savings as
       compared to 1-element corresponding to each stack element */
    public void pushKeepingTrackOfMin(Node node)
    {
        //null
        if(node == null)
        {
            throw new IllegalArgumentException("Invalid input");
        }

        //Zero or empty case
        if(stack == null || isEmpty(stack))
        {
            if(stack==null)
            {
                stack = new Stack();
                setStack(stack);
            }
            stack.setTop(node);
            incrementSize(stack);

            /* Need to keep this stack, since need to support getMin in O(1) time.
              Else we could have popped out remaining elements, found min and then pushed them back */
            minStack = new Stack();
            minStack.setTop(node);

            return;
        }

        //other cases
        Node top = stack.getTop();
        node.setNext(top);
        stack.setTop(node);

        //Only update min, when there is a lesser element recorded. Not a min, for every stack element
        if(minStack.getTop().getData()>node.getData())
        {
            //other cases
            Node minTop = minStack.getTop();
            node.setNext(minTop);
            minStack.setTop(node);
        }
    }

    //TODO: Tricky
    /* Special DS -> Maintain two stacks in order to support getMin in O(1) time.
       If something is needed in constant time, its a given we need extra space to support O(1) constraint.
       While popping from primary stack, only pop the minStack when element being popped is equal to the current min-stack top. */
    public Node popKeepingTrackOfMin() throws Exception
    {
        Node top ;
        //Zero or empty case
        if(stack == null || isEmpty(stack))
        {
           throw new RuntimeException("Stack is null");
        }

        //Zero or empty case for an internal stack that keeps track of min.
        //So any misbehavior should result in to (??? 4xx/5xx ???) status code and is captured with (??? checked/unchecked ???)

        //in my thought : 5xx since client cannot fix it since he is not aware of minStack internals.

        /* *** But then we are mixing 5xx with checked/unchecked exceptions : https://enterprisecraftsmanship.com/2017/01/31/rest-api-response-codes-400-vs-500/
        https://stackoverflow.com/questions/212239/what-java-exception-class-to-use-for-http-errors : 5xx & 4xx : All are modelled as Unchecked exception
        https://www.baeldung.com/java-exceptions
        https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html **** */
        if(minStack==null || isEmpty(minStack))
        {
            throw new Exception("Tracking Stack is null");
        }

        //Single-element
        if(stack.getTop().getNext()==null)
        {
            top = stack.getTop();
            stack.setTop(null);
            decrementSize(stack);
            if(minStack==null || isEmpty(minStack))
            {
                throw new Exception("Tracking Stack is null");
            }
             else if(minStack.getTop().getData()==top.getData())
            {
                //other cases
                minStack.setTop(null);
            }
            return top;
        }

        //other cases
        top = stack.getTop();
        decrementSize(stack);
        stack.setTop(top.getNext());

        //Only update min, when there is a lesser element recorded. Not a min, for every stack element
        if(minStack.getTop().getData()==top.getData())
        {
            //other cases
            minStack.setTop(minStack.getTop().getNext());
        }
        return top;
    }

    //TODO: Tricky
     /* Special DS -> Maintain two stacks in order to support getMiddle in O(1) time.
     If something is needed in constant time, its a given we need extra space to support O(1) constraint.

     Aux stack -> on every push to main stack, corresponding entry in aux stack -> might need some pop-push cycles to find the mid (which is now buried under) & similarly for deletion
     Aux array -> O(1) findMiddle random access, but deletion wont be in O(1) since shifting would be needed
     *** Stack as DLL *** -> O(1) findMiddle by keeping track of middle element while pushing. Pointer adjustment around deleteMiddle
     */
    public void pushTrackingMiddleElement(Node node, Stack stack)
    {
        if(node==null)
        {
            throw new IllegalArgumentException("Invalid input");
        }

        //Zero element/ empty stack case
        //if(stack == null || isEmpty(stack))
        if(doubleLinkedListOps==null || doubleLinkedListOps.getDoubleLinkedList()==null)
        {
            doubleLinkedListOps = new DoubleLinkedListOps(new DoubleLinkedList());
            //to simulate stack (LIFO) ...push and pop happen at head node in O(1) time
            doubleLinkedListOps.insertAtFront(node.getData());
            //only element case
            middle = doubleLinkedListOps.getDoubleLinkedList().getHead();
            return;
        }

        //to simulate stack (LIFO) ...push and pop happen at head node in O(1) time
        doubleLinkedListOps.insertAtFront(node.getData());

        //since insertion is at the start, list is expanding to the left. So mid will move left (prev)
        //*** IMPORTANT ***
        if(doubleLinkedListOps.getDoubleLinkedList().getSize()%2 != 0)
        {
            middle = middle.getPrevious();
        }
        return;
    }

    public int findMiddle()
    {
        if(doubleLinkedListOps==null || doubleLinkedListOps.getDoubleLinkedList()==null)
        {
            throw new RuntimeException("Stack is null");
        }
        return middle.getData();
    }

    public int deleteMiddle()
    {
        int data = -1;
        if(doubleLinkedListOps==null ||  doubleLinkedListOps.getDoubleLinkedList()==null)
        {
            throw new RuntimeException("Stack is null");
        }

        //Single element case
        if(doubleLinkedListOps.getDoubleLinkedList().getSize()==1)
        {
            //to simulate stack (LIFO) ...push and pop happen at head node in O(1) time
            data = middle.getData();
            //assuming no duplicates
            doubleLinkedListOps.deleteNode(data);

            middle = null;
            return data;
        }

        //to simulate stack (LIFO) ...push and pop happen at head node in O(1) time
        data = middle.getData();
        //assuming no duplicates
        doubleLinkedListOps.deleteNode(data);

        if(doubleLinkedListOps.getDoubleLinkedList().getSize()%2 != 0)
        {
            middle = middle.getNext();
        }

        return data;
    }

    public int popTrackingMiddleElement()
    {
        if(doubleLinkedListOps==null || doubleLinkedListOps.getDoubleLinkedList()==null)
        {
            throw new RuntimeException("Stack is null");
        }

        //Single element case
        if(doubleLinkedListOps.getDoubleLinkedList().getSize()==1)
        {
            //to simulate stack (LIFO) ...push and pop happen at head node in O(1) time
            int data = doubleLinkedListOps.getDoubleLinkedList().getHead().getData();
            doubleLinkedListOps.deleteFirstNode();

            middle = null;
            return data;
        }

        //to simulate stack (LIFO) ...push and pop happen at head node in O(1) time
        int data = doubleLinkedListOps.getDoubleLinkedList().getHead().getData();
        doubleLinkedListOps.deleteFirstNode();

        //since deletion is at the start, list is contracting from the left. So mid will move right (next)
        //*** IMPORTANT ***
        if(doubleLinkedListOps.getDoubleLinkedList().getSize()%2 != 0)
        {
            middle = middle.getNext();
        }
        return data;
    }


    //TODO : Tricky
      /* Special DS -> Maintain Use 3 arrays unlike implement 2 stacks in 1 array
     If something is needed in constant time, its a given we need extra space to support O(1) constraint.*/
    public void pushInKStacks(int data, int stackNumber)
    {
        if(data<0)
        {
            throw new IllegalArgumentException("Invalid data input");
        }

        if(stackNumber<0 || stackNumber>totalStacks )
        {
            throw new IllegalArgumentException("Invalid stack input");
        }

        //un-optimized implementation
        if(currentTracker==99)
        {
            throw new RuntimeException("Stack is full");
        }

        //kStacks contains data
        kStacks[++currentTracker] = data;
        //nextTracker tracks ***index*** of the next element
        nextTracker[currentTracker] = topTracker[stackNumber];
        //nextTracker tracks ***index*** of the top element
        topTracker[stackNumber] = currentTracker;
    }


    public int popFromKStacks(int stackNumber)
    {
        int data = -1;
        if(stackNumber<0 || stackNumber > totalStacks)
        {
            throw new IllegalArgumentException("Invalid stack number");
        }

        if(topTracker[stackNumber]==-1)
        {
            throw new RuntimeException("Stack is empty");
        }

        int currentTopIndex = topTracker[stackNumber];
        topTracker[stackNumber] = nextTracker[topTracker[stackNumber]];
        nextTracker[topTracker[stackNumber]] = -1;
        data = kStacks[currentTopIndex];
        kStacks[currentTopIndex] = -1;

        return data;
    }

    public int getTopFromStack(int stackNumber)
    {
        return kStacks[topTracker[stackNumber]];
    }


    public void pushToMergableStack(int data, int stackNumber)
    {
        if(data<0 || stackNumber < 1 || stackNumber > 2)
        {
            throw new IllegalArgumentException("Invalid data");
        }

        if(stackNumber == 1)
        {
            //Empty or null stack case
            if(list1==null || list1.getSize()==0)
            {
                if(list1==null)
                {
                    list1 = new LinkedList();
                    llOps1 = new LLOps(list1);
                }
                llOps1.insertAtFront(new LinkedList.Node(data));
                return;
            }

            llOps1.insertAtFront(new LinkedList.Node(data));
            return;
        }

        if(stackNumber == 2)
        {
            //Empty or null stack case
            if(list2==null || list2.getSize()==0)
            {
                if(list2==null)
                {
                    list2 = new LinkedList();
                    llOps2 = new LLOps(list2);
                }
                llOps2.insertAtFront(new LinkedList.Node(data));
                return;
            }

            llOps2.insertAtFront(new LinkedList.Node(data));
            return;
        }
    }

    public int popFromMergableStack(int stackNumber)
    {
        int data = -1;
        if(stackNumber<1 || stackNumber>2)
        {
            throw new IllegalArgumentException("Invalid stack");
        }

        if(stackNumber ==1)
        {
            //null or empty case
            if(list1 ==null || list1.getSize()==0)
            {
                throw  new RuntimeException("null or empty stack");
            }
            //Single element case
            if(list1.getSize()==1)
            {
                data = llOps1.deleteAtPosition(0);
                list1 = null;
                return data;
            }
        }

        if(stackNumber ==2)
        {
            //null or empty case
            if(list2 ==null || list2.getSize()==0)
            {
                throw  new RuntimeException("null or empty stack");
            }
            //Single element case
            if(list2.getSize()==1)
            {
                data = llOps2.deleteAtPosition(0);
                list2 = null;
                return data;
            }
        }

        return  (stackNumber == 1) ?llOps1.deleteAtPosition(0):llOps1.deleteAtPosition(0);
    }

    public LinkedList.Node mergeStacks()
    {
        if((list1 ==null || list1.getSize()==0 ) && (list2 ==null|| list2.getSize()==0 ))
        {
            throw new RuntimeException("Both lists are null/empty");
        }
        if(list1==null)
        {
            return llOps2.getLinkedList().getHead();
        }
        if(list2 ==null)
        {
            return llOps1.getLinkedList().getHead();
        }

        LinkedList.Node node = llOps1.getLinkedList().getHead();
        LinkedList.Node firstListLastNode = null;
        int i = 0;
        //since we dont have access to the last node, iterate and then link
        do {
            if(i == llOps1.getLinkedList().getSize()-1)
            {
                firstListLastNode = node;
                break;
            }

            node = node.getNext();
            i++;
            continue;

        }while (i<llOps1.getLinkedList().getSize());

        firstListLastNode.setNext(llOps2.getLinkedList().getHead());

        return list1.getHead();
    }


    public void pushToStackImplementedUsingDLL(int x)
    {
        if(x<0)
        {
            throw new IllegalArgumentException("Invalid input");
        }

        //null or empty case
        if(doubleLinkedListOps.getDoubleLinkedList()==null || doubleLinkedListOps.getDoubleLinkedList().getHead()==null)
        {
            if(doubleLinkedListOps.getDoubleLinkedList()==null)
            {
                doubleLinkedListOps.setDoubleLinkedList(new DoubleLinkedList());
            }
            doubleLinkedListOps.insertAtEnd(x);
            return;
        }

        doubleLinkedListOps.insertAtEnd(x);
    }

    public DoubleLinkedList.Node popFromStackImplementedUsingDLL()
    {
        DoubleLinkedList.Node node = null;

        if(doubleLinkedListOps.getDoubleLinkedList()==null || doubleLinkedListOps.getDoubleLinkedList().getHead()==null)
        {
            throw new RuntimeException("Invalid input");
        }

        //single element case
        if(doubleLinkedListOps.getDoubleLinkedList().getHead().getNext()==null)
        {
            node =  doubleLinkedListOps.getDoubleLinkedList().getHead();
            doubleLinkedListOps.setDoubleLinkedList(null);
            return node;
        }

        node = doubleLinkedListOps.deleteLastNode();

        return node;
    }

    public void enqueToQueueImplementedUsingDLL(Node x)
    {
        //same as pushToStackImplementedUsingDLL
    }

    public DoubleLinkedList.Node dequeFromQueueImplementedUsingDLL()
    {
        DoubleLinkedList.Node node = null;

        if(doubleLinkedListOps.getDoubleLinkedList()==null || doubleLinkedListOps.getDoubleLinkedList().getHead()==null)
        {
            throw new RuntimeException("Queue is already null or empty");
        }

        //single node case
        if(doubleLinkedListOps.getDoubleLinkedList().getHead().getNext()==null)
        {
            node = doubleLinkedListOps.getDoubleLinkedList().getHead();
            doubleLinkedListOps.setDoubleLinkedList(null);
            return node;
        }

        node = doubleLinkedListOps.deleteFirstNode();

        return node;
    }
    
    //TODO: Tricky
    //operand case (assuming limited operator support)
    private boolean isInputPrecedenceGreaterThanStackTop(char token) {
         boolean isInputPrecedenceGreater = false;
         char stackTopOperator = (char) peek(stack).getData();

         switch (stackTopOperator){
             case '%':
                 isInputPrecedenceGreater = false;
                 break;
             case '/':
                 if(token == '%'){
                    isInputPrecedenceGreater = true;
                 }
                 else {
                     isInputPrecedenceGreater = false;
                 }
                 break;
             case '*':
                 //forgot the self-case was doing [if(token == '+' || token == '-')] before
                 if(token == '*' || token == '+' || token == '-'){
                     isInputPrecedenceGreater = false;
                 }
                 else {
                     isInputPrecedenceGreater = true;
                 }
                 break;
             case '+':
                 if(token == '+' || token == '-'){
                     isInputPrecedenceGreater = false;
                 }
                 else {
                     isInputPrecedenceGreater = true;
                 }
                 break;
             case '-':
                     isInputPrecedenceGreater = true;
                 break;
             default:
                 break;
         }
        return isInputPrecedenceGreater;
    }

    private boolean evaluateExpressionAndPushValueToStack(char token) {

        if(!isEmpty(stack) && (char)peek(stack).getData()!= '(')
        {
            //First pop
            int rhsOperand = pop(stack).getData();
            if(!isEmpty(stack) && (char)peek(stack).getData()!= '(')
            {
                //Second pop
                int lhsOperand = pop(stack).getData();
                //push calculated value to stack
                push(new Node(evaluateExpression(lhsOperand, rhsOperand, token)),stack);
                return true;
            }
        }
        //Both LHS and RHS operands need to be present for relational operators
        if(isEmpty(stack) || (char)peek(stack).getData()!= '(')
        {
            throw new IllegalArgumentException("Invalid input");
        }
        return false;
    }

    private int evaluateExpression(int lhsOperand,int rhsOperand,char operator)
    {
        int value = 0;
        switch (operator){
            case '%':
                value = lhsOperand%rhsOperand;
                break;
            case '/':
                value = lhsOperand/rhsOperand;
                break;
            case '*':
                value = lhsOperand*rhsOperand;
                break;
            case '+':
                value = lhsOperand+rhsOperand;
                break;
            case '-':
                value = lhsOperand-rhsOperand;
                break;
            default:
                break;
        }
        return value;
    }

    private char popAndTransformToChar() {
        return (char) pop(stack).getData();
    }

    private void incrementSize(Stack stack) {
        if(stack==null) return;
        stack.setSize(stack.getSize()+1);
    }

    private void decrementSize(Stack stack) {
        if(stack==null) return;
        if(stack.getSize()>0)
        {
            stack.setSize(stack.getSize()-1);
        }
    }
}
