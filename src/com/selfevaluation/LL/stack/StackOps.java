package com.selfevaluation.LL.stack;

import com.selfevaluation.base.Stack;
import com.selfevaluation.base.Stack.Node;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Setter
@Getter
public class StackOps {

    private Stack stack;

    public StackOps(Stack stack)
    {
        this.stack = stack;
        if(this.stack!=null)
        {
            this.stack.setTop(null);
        }
    }

    //isEmpty
    public boolean isEmpty() {
        //distinguishing between null and empty case here is critical unlike add/delete since we want to check for emptiness
        if(stack==null)
        {
            throw new  RuntimeException("Stack is null or empty");
        }
        return (stack.getTop()==null);
    }

    public Node peek()
    {
        //zero element (null or empty)
        if(stack==null || isEmpty())
        {
            throw new  RuntimeException("Stack is null or empty");
        }
        return stack.getTop();
    }

    //push
    public void push(Node node) {
        if(node==null)
        {
            throw new  IllegalArgumentException("Invalid input");
        }

        //Zero-element case (null or empty)
        if(stack==null || isEmpty())
        {
            if(stack==null)
            {
                stack = new Stack();
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

    public Node pop() {
        //null or empty
        if(stack==null || isEmpty())
        {
            throw  new RuntimeException("Stack is null");
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


    //Tricky
    public int evaluatePostfix(String postFixExp)
    {
        Node node = null;
        int bracketOperationCount = 0, value = 0;
        char token = '\0';

        //operand case (assuming limited operator support)
        List<Character> operandsList = new ArrayList<>(Arrays.asList('%','/','*','+','-'));

        //null or empty
        if(postFixExp==null || postFixExp.trim().isEmpty() || postFixExp.length()<=2)
        {
            throw new IllegalArgumentException("Invalid input");
        }

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
                push(new Node('('));
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
                if (!isEmpty() && ((char)peek().getData())!='(' && !operandsList.contains((char)peek().getData()))
                {
                    /*This is a BIG find. Popping value node and then pushing it back later below was causing inconsistent
                      results as it was getting the older references for node->next, even when stack size was 0.
                      Better to operate at primitive level than references*/
                    //node = pop();

                    value = pop().getData();
                    if(!isEmpty() && ((char)peek().getData())=='(')
                    {
                        //Pop the '('
                        pop();

                        /*This is a BIG find. Popping value node and then pushing it back later below was causing inconsistent
                          results as it was getting the older references for node->next, even when stack size was 0*/
                        //push(node);
                        push(new Node(value));
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
                    push(new Node(Character.getNumericValue(token)));
                }
                else if (Character.isLetter(token))
                {
                    push(new Node(token));
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
        if(isEmpty())
        {
            throw new IllegalArgumentException("Invalid input");
        }
        value = pop().getData();

        //There is just 1 node expected in the stack.
        ////Unit tests indeed keep you humble. See how this issue was discovered as part of whenPostfixExpContainingOnlyOperandsThenThrowException
        if(!isEmpty())
        {
            throw new IllegalArgumentException("Invalid input");
        }
        return value;
    }


    //Tricky
    public String infixToPostfix(String infixExp)
    {
        StringBuilder postFix = new StringBuilder();
        //this keeps track of bracketOps open count , which shall help us with mis-match
        int bracketOpenCount = 0;

        //operand case (assuming limited operator support)
        List<Character> operandsList = new ArrayList<>(Arrays.asList('%','/','*','+','-'));

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
                push(new Node('('));
                bracketOpenCount++;
                continue;
            }

            // ')' case
            if(token == ')')
            {
                while ((!isEmpty() && stack.getTop().getData() != '('))
                {
                    postFix.append(popAndTransformToChar());
                }
                //always check for emptiness before checking for the other condition, since you if reverse the order,
                // you will have to consider the empty case for the other case.
                if(isEmpty())
                {
                    throw new IllegalArgumentException("Incomplete brackets-of detected");
                }

                //')' is used to de-limit
                if(stack.getTop().getData() == '(')
                {
                    pop();
                    bracketOpenCount--;
                    postFix.append(')'); //'(' is already appended while we push it to the stack
                }
                continue;
            }

            //operator with un-conditional-push case
            /*if  stack-top is empty or current top is scanned operator is greater than the precedence of the top operator in the stack
            , then push it*/
            if(isEmpty() || (operandsList.contains(token) && isInputPrecedenceGreaterThanStackTop(token)))
            {
                push(new Node(token));
                continue;
            }

            //operator with conditional-push case (pop until input precedence is not greater than stackTop)
            if((operandsList.contains(token) && !isInputPrecedenceGreaterThanStackTop(token)))
            {
                //'(' check is needed since the only time, we factor them for popping is in response to discovering ')'
                while (!isEmpty() && !isInputPrecedenceGreaterThanStackTop(token) && !((char)peek().getData()=='('))
                {
                    postFix.append(popAndTransformToChar());
                }
                push(new Node(token));
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
        while (!isEmpty())
        {
            char operator = popAndTransformToChar();
            if((operandsList.contains(operator)))
            {
                postFix.append(operator);
            }
        }
        return postFix.toString();
    }

    //operand case (assuming limited operator support)
    private boolean isInputPrecedenceGreaterThanStackTop(char token) {
         boolean isInputPrecedenceGreater = false;
         char stackTopOperator = (char) peek().getData();

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

        if(!isEmpty() && (char)peek().getData()!= '(')
        {
            //First pop
            int rhsOperand = pop().getData();
            if(!isEmpty() && (char)peek().getData()!= '(')
            {
                //Second pop
                int lhsOperand = pop().getData();
                //push calculated value to stack
                push(new Node(evaluateExpression(lhsOperand, rhsOperand, token)));
                return true;
            }
        }
        //Both LHS and RHS operands need to be present for relational operators
        if(isEmpty() || (char)peek().getData()!= '(')
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
        return (char) pop().getData();
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
