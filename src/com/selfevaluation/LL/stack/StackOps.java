package com.selfevaluation.LL.stack;

import com.selfevaluation.base.Stack;
import com.selfevaluation.base.Stack.Node;
import lombok.Getter;
import lombok.Setter;

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
