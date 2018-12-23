package com.selfevaluation.LL.stack;

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

    @Getter
    private Stack stack;

    private int top1, top2;
    private int[] stackArray;

    private List<Node> queue1, queue2;
    private Stack minStack;
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
    }

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
            incrementSize();
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
        incrementSize();
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
            decrementSize();
            return current;
        }

        //current = first element
        Node current = stack.getTop();
        stack.setTop(current.getNext());
        decrementSize();
        return current;
    }

    public void pushUsingTwoQueues(Node node) {
        if(node==null)
        {
            throw new IllegalArgumentException("Invalid input");
        }

        //Zero-or empty stack
        if(auxStack==null /*|| isEmpty(stackToPushTo)*/) {

                auxStack = new Stack();
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

    public Node popUsingTwoQueues() {
        Node node = null;
        if(queue1==null || queue1.size()==0)
        {
            throw  new RuntimeException("Stack is null or empty");
        }

        //Single element case
        if(queue1.size()==1)
        {
            return  queue1.get(0);
        }

        /* Move all but the last element to the other queue
           Also much better than looping in a for-loop on a size-decreasing list resulting in to concurrentModificationException
           and also since size is decreasing, just i++ will cause you to skip elements */
        do {
            queue2.add(queue1.remove(0));
        }while (queue1.size()>1);

        //return the only element left in queue1
        node =  queue1.remove(0);

        //Critical else, you would have to keep track of which queue is has elements and which one is aux
        List tmp = queue2;
        queue2 = queue1;
        queue1 = tmp;

        return node;
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

    //Tricky
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
        List<Character> operandsList = new ArrayList<>(Arrays.asList('%','/','*','+','-'));

        //In order to avoid NPE before null check, better to initialize after input validation
        char[] postFixExpArray = postFixExp.toCharArray();
        //Traverse from left to right
        for (int i = 0; i < postFixExpArray.length; i++) {

            token = postFixExpArray[i];

            //Order of operations is important while looping: Operands, '(', ')', operators.
            // Then go for remaining elements in the stack checking for bad-input cases

            //operand case
            if(operandsList.contains(token))
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
                if (!isEmpty(stack) && ((char)peek(stack).getData())!='(' && !operandsList.contains((char)peek(stack).getData()))
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

    //Tricky
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
        List<Character> operandsList = new ArrayList<>(Arrays.asList('%','/','*','+','-'));

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
                //always check for emptiness before checking for the other condition, since you if reverse the order,
                // you will have to consider the empty case for the other case.
                if(isEmpty(stack))
                {
                    throw new IllegalArgumentException("Incomplete brackets-of detected");
                }

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
            if(isEmpty(stack) || (operandsList.contains(token) && isInputPrecedenceGreaterThanStackTop(token)))
            {
                push(new Node(token),stack);
                continue;
            }

            //operator with conditional-push case (pop until input precedence is not greater than stackTop)
            if((operandsList.contains(token) && !isInputPrecedenceGreaterThanStackTop(token)))
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
            if((operandsList.contains(operator)))
            {
                postFix.append(operator);
            }
        }
        return postFix.toString();
    }

    /* Special DS -> Maintain two stacks in order to support getMin in O(1) time.
       If something is needed in constant time, its a given we need extra space to support O(1) constraint.
       Also only push to the minStack only when there is an update to the newMin. Will help in space savings */
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
            incrementSize();

            minStack = new Stack();
            minStack.setTop(node);

            return;
        }

        //other cases
        Node top = stack.getTop();
        node.setNext(top);
        stack.setTop(node);

        if(minStack.getTop().getData()>node.getData())
        {
            //other cases
            Node minTop = minStack.getTop();
            node.setNext(minTop);
            minStack.setTop(node);
        }
    }

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
            decrementSize();
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
        decrementSize();
        stack.setTop(top.getNext());

        if(minStack.getTop().getData()==top.getData())
        {
            //other cases
            minStack.setTop(minStack.getTop().getNext());
        }
        return top;
    }

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

    private void incrementSize() {
        if(stack==null) return;
        stack.setSize(stack.getSize()+1);
    }

    private void decrementSize() {
        if(stack==null) return;
        if(stack.getSize()>0)
        {
            stack.setSize(stack.getSize()-1);
        }
    }
}
