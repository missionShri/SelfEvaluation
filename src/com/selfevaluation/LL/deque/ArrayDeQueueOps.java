package com.selfevaluation.LL.deque;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;


//https://www.geeksforgeeks.org/queue-set-1introduction-and-array-implementation/
//https://www.geeksforgeeks.org/heap-data-structure/
//https://www.geeksforgeeks.org/priority-queue-set-1-introduction/
//https://www.geeksforgeeks.org/deque-set-1-introduction-applications/


//https://www.geeksforgeeks.org/implementation-deque-using-circular-array/
@Getter
@Setter
public class ArrayDeQueueOps {

    public static final int MAX = 100;
    private int[] deque;
    private int front , rear;

    //!!!! life is so much easier with keeping track of size !!!!
    private int size;

    public ArrayDeQueueOps()
    {
        this.deque = new int[MAX];
        Arrays.fill(deque,-1);

        //!!! Initialization is important !!!...different from the standard enqueue-dequeue
        rear = -1;
        front = MAX;
        size = 0;
    }

    //Rear keeps incrementing as we push and we start from -1
    public void pushToRear(int data)
    {
        if(data<0)
        {
            throw new IllegalArgumentException("Invalid data");
        }

        if(isFull())
        {
            throw new RuntimeException("Stack is full");
        }

        if(deque==null || isEmpty())
        {
            if(deque==null)
            {
                deque = new int[MAX];
            }

            deque[(++rear)%MAX] = data;

//            if(front == -1)
//            {
//                //also initialize front for the first element, so that neither rear nor front are -1
//                ++front;
//            }
            size++;
            return;
        }

        deque[(++rear)%MAX] = data;
        size++;
        return;
    }

    //Front keeps decrementing as we push and we start from MAX
    public void pushFromFront(int data)
    {
        if(data<0)
        {
            throw new IllegalArgumentException("Invalid data");
        }

        if(isFull())
        {
            throw new RuntimeException("Stack is full");
        }

        if(deque==null || isEmpty())
        {
            if(deque==null)
            {
                deque = new int[MAX];
            }

            deque[Math.abs((--front+MAX)%MAX)] = data;
//            if(rear == -1)
//            {
//                //also initialize front for the first element, so that neither rear nor front are -1
//                ++rear;
//            }
            size++;
            return;
        }

        deque[Math.abs((--front+MAX)%MAX)] = data;
        size++;
        return;
    }

    public int removeFromRear()
    {
        int data = -1;
        if(deque ==null || isEmpty())
        {
            throw new RuntimeException("Queue is empty");
        }

        if(rear==-1)
        {
            throw new RuntimeException("Stack is already empty from the rear");
        }

        //single element
        if(isSingleElement())
        {

            data = deque[(rear--)%MAX];
            size--;
            return data;
        }

        data = deque[(rear--)%MAX];
        size--;
        return data;
    }

    public int removeFromFront()
    {
        int data = -1;
        if(deque ==null || isEmpty())
        {
            throw new RuntimeException("Queue is empty");
        }

        if(front==MAX)
        {
            throw new RuntimeException("Stack is already empty from the front");
        }

        //single element
        if(isSingleElement())
        {
            data = deque[Math.abs((front++)+MAX)%MAX];
            size--;
            return data;
        }

        data = deque[Math.abs((front++)+MAX)%MAX];
        size--;
        return data;
    }

    private boolean isFull() {
        return (size==MAX);
    }

    private boolean isEmpty() {
        return (size==0);
    }

    private boolean isSingleElement() {
        return (size==1);
    }
}
