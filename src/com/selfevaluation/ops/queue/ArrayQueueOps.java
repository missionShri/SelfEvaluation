package com.selfevaluation.ops.queue;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class ArrayQueueOps {


    //not marking it as static final for test
    private  int MAX = 100;
    int[] queue;
    int front, rear;

    int[] kQueueArray, frontTracker, nextTracker, rearTracker;
    int currentIndex, totalQueues, currentSize;

    //As described here: https://www.geeksforgeeks.org/queue-set-1introduction-and-array-implementation/ , could also have tracking of size
    public ArrayQueueOps()
    {
        queue = new int[MAX];
        Arrays.fill(queue,-1);

        //TODO : Check initialization policy
        //front = rear =0;
        front = rear = -1;

        initializeKQueueArrayAndRelatedTrackers();
    }

    private void initializeKQueueArrayAndRelatedTrackers() {
        totalQueues = 10;
        kQueueArray = new int[MAX];
        Arrays.fill(kQueueArray,-1);
        nextTracker = new int[MAX];
        Arrays.fill(nextTracker,-1);
        frontTracker= new int[totalQueues];
        Arrays.fill(frontTracker,-1);
        rearTracker= new int[totalQueues];
        Arrays.fill(rearTracker,-1);
        //All initialized to -1 & not 0
        currentIndex = -1;
        //Size needs to start from 0
        currentSize = 0;
    }

    //Circular array to keep it space-optimized else if front it stays-fixed at 0 ==>
    //then we are looking to shift all the elements in the array upon dequeue from the front...better to keep the front ahead , without having to shift all remaining elements a position ahead to fill-up the newly created slot at the start
    //after marking the element to be deleted as (-1) empty slot ==> much like linked-list

    //Enqueue only happens at the rear
    //Keeping track of 2
    public void enqueue(int data)
    {
        if(data<0)
        {
            throw new IllegalArgumentException("Invalid data");
        }

        //full case.
        //with the exception of empty & single-element case ==> rear is  at-least one ahead of front (from ) , front is at-least one ahead of rear
        if(isFull())
        {
            throw new RuntimeException("Stack is already full");
        }

        //null or empty case
        if(queue==null || isEmpty())
        {
            queue[(++rear)%MAX]=data;

            //also initialize front since its the first element
            if(front==-1)
            {
                ++front;
            }
            return;
        }

        //rear pointer keeps moving, always incremented since its %_ed even
        queue[(++rear)%MAX]=data;
        return;
    }

    public boolean isEmpty() {
        //to distinguish between empty and 1-element case.
        return (/*front%MAX==rear%MAX && (*/(front >= 0 && queue[front%MAX]==-1) || front==-1);
    }

    public boolean isFull() {
        //full-case, later cases
        return (((rear > front) && (rear+1)%MAX==front%MAX) || ((front > rear) && (front-rear)%MAX == 1));
    }

    public boolean hasSingleElement() {
        return (!isEmpty() && rear==front);
    }

    //Deque only happens from the start
    public int dequeue()
    {
        int data = -1;

        if(queue==null || isEmpty())
        {
            throw new RuntimeException("Queue is null/empty");
        }

        //single element
        if(hasSingleElement())
        {
            data = queue[front%MAX];
            queue[(front)%MAX]=-1;
            return data;
        }

        data = queue[front%MAX];
        //front pointer keeps moving, always incremented since its %_ed ...so will never fall of the cliff of MAX
        queue[(front++)%MAX]=-1;
        return data;
    }

    public void enqueueTokQueueArray(int data, int queueNumber)
    {
        if(data<0)
        {
            throw new IllegalArgumentException("Invalid input");
        }

        if(queueNumber<0 || queueNumber>=totalQueues)
        {
            throw new IllegalArgumentException("Invalid queue number");
        }

        if(currentSize==MAX)
        {
            throw new RuntimeException("Stack is full");
        }

        if(kQueueArray==null)
        {
            initializeKQueueArrayAndRelatedTrackers();
        }
        //keep track of kQueueArray.occupancy
        kQueueArray[++currentIndex] = data;

        //Zero or empty element case (-1 on initialization & pop After push case)
        if(frontTracker[queueNumber]==-1 && rearTracker[queueNumber]==-1)
        {
            frontTracker[queueNumber] = currentIndex;
            rearTracker[queueNumber] = currentIndex;
            currentSize++;
            return;
        }

        /*this has become more of a prevTracker since at nextTracker[currentIndex] location, we keep track of the previous rear element
          Not natural to fifo, where the rear pointer does not have access to previous rear.
          The natural order is to move next from front onwards
        nextTracker[currentIndex] = rearTracker[queueNumber]; */

        /*what I want to do is keep linking ahead starting from front, instead of keep linking up prevs starting from rear
         even though insertions happen in the rear*/
        nextTracker[rearTracker[queueNumber]] = currentIndex;

        //Direct access to rear.element by kQueueArray[rearTracker[queueNumber]]
        rearTracker[queueNumber] = currentIndex;
        currentSize++;
        return;
    }

    public int dequeFromkQueueArray(int queueNumber)
    {
        int data = -1;
        if(queueNumber<0 || queueNumber>=totalQueues)
        {
            throw new IllegalArgumentException("Invalid queue number");
        }

        if(currentSize==0)
        {
            throw new RuntimeException("Queue is already empty");
        }

        //single element
        if(frontTracker[queueNumber]!=-1 && frontTracker[queueNumber]==rearTracker[queueNumber])
        {
            data = kQueueArray[frontTracker[queueNumber]];
            //mark the core-element
            kQueueArray[frontTracker[queueNumber]] = -1;
            //mark the next element of the rear ... even though its dequeue, rear element gets impacted due to single-element case
            nextTracker[rearTracker[queueNumber]] = -1;
            //mark front element
            frontTracker[queueNumber] = -1;
            //mark rear element
            rearTracker [queueNumber] = -1;
            //decrease the kQueueArray size
            currentSize--;
            return data;
        }


        //back-up data to be returned
        data = kQueueArray[frontTracker[queueNumber]];
        //mark the core-element as -1
        kQueueArray[frontTracker[queueNumber]] = -1;

        int previousFrontIndex = frontTracker[queueNumber];
        //front-tracker gets updated to the new front
        frontTracker[queueNumber]= nextTracker[frontTracker[queueNumber]];

        // mark next of the current (to-be) deleted node
        nextTracker[previousFrontIndex] = -1;

        //decrease the kQueueArray size
        currentSize--;

        return data;
    }

}
