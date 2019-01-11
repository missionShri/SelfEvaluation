package com.selfevaluation.LL.stack;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArrayStackOps {

    public static final int MAX = 100;

    //No other DS maintaining required
    int[] stack;
    //top tracks index as opposed to the node as in list implementation
    int top;

    public ArrayStackOps()
    {
        stack = new int[MAX];
        top = -1;
    }

    //isEmpty
    public boolean isEmpty()
    {
        //distinguishing between null and empty case here is critical unlike add/delete since we want to check for emptiness
        if(stack==null)
        {
            throw new  RuntimeException("Stack is null");
        }

        //top index is the only decider
        return top== (-1);
    }

    public int peek()
    {
        //zero element (null or empty)
        if(stack==null || isEmpty())
        {
            throw new  RuntimeException("Stack is null or empty");
        }

        return stack[top];

    }

    //push
    public void push(int element) {
        if (element == -1) {
            throw new IllegalArgumentException("Invalid input");
        }

        //zero element (null or empty)
        if(stack==null || isEmpty())
        {
            if(stack==null)
            {
                stack = new int[MAX];
            }
            stack[++top] = element;

            //no need to increment-decrement and maintain size as top index is the size
            //incrementSize();
            return;
        }

        if(top<=MAX)
        {
            stack[++top] = element;
        }

        //no need to increment-decrement and maintain size as top index is the size
        //incrementSize();
        return;
    }

    //pop
    public int pop() {
        //null or empty
        if(stack==null || isEmpty())
        {
            throw new RuntimeException("Stack is null/empty");
        }

        //single element stack
        if(top==0)
        {
            //no need to increment-decrement and maintain size as top index is the size
            //decrementSize();
            return stack[top--];
        }

        //no need to increment-decrement and maintain size as top index is the size
        //decrementSize();
        return stack[top--];
    }






}
