package com.selfevaluation.ops.deque;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://www.geeksforgeeks.org/queue-set-1introduction-and-array-implementation/
//https://www.geeksforgeeks.org/heap-data-structure/
//https://www.geeksforgeeks.org/priority-queue-set-1-introduction/
//https://www.geeksforgeeks.org/deque-set-1-introduction-applications/


//https://www.geeksforgeeks.org/implementation-deque-using-circular-array/
@Getter
@Setter
public class ArrayDeQueueOps {

    public static final int MAX = 100;
    ArrayDeqeusAttempt maxArrayDeqeusAttempt;
    ArrayDeqeusAttempt minArrayDeqeusAttempt;
    /*private Integer[] deque;
    private Integer front , rear, anotherFront, anotherRear;

    //!!!! life is so much easier with keeping track of size !!!!
    private Integer size, anotherSize;*/

    public ArrayDeQueueOps()
    {
        /*this.deque = new Integer[MAX];
        Arrays.fill(deque,-1);

        this.anotherDeque = new Integer[MAX];
        Arrays.fill(anotherDeque,-1);

        //!!! Initialization is important !!!...different from the standard enqueue-dequeue
        rear = anotherRear = -1;
        front = anotherFront = MAX;
        size = anotherSize = 0;*/
        maxArrayDeqeusAttempt = new ArrayDeqeusAttempt();
        minArrayDeqeusAttempt = new ArrayDeqeusAttempt();
    }


    //By passing an instance of ArrayDeqeusAttempt around, we are able to modify/update its attributes
    @Getter
    @Setter
    public class ArrayDeqeusAttempt{
        private int[] deque;
        private int front , rear;

        //!!!! life is so much easier with keeping track of size !!!!
        private Integer size;

        public ArrayDeqeusAttempt()
        {
            this.deque = new int[MAX];
            Arrays.fill(deque,-1);

            //!!! Initialization is important !!!...different from the standard enqueue-dequeue
            rear = -1;
            front = MAX;
            size = 0;
        }
    }

    //Rear keeps incrementing as we push and we start from -1
    public void pushToRear(ArrayDeqeusAttempt arrayDeqeusAttempt, Integer data)
    {
        if(data<0)
        {
            throw new IllegalArgumentException("Invalid data");
        }

        if(isFull(arrayDeqeusAttempt))
        {
            throw new RuntimeException("Stack is full");
        }

        if(arrayDeqeusAttempt.deque==null || isEmpty(arrayDeqeusAttempt))
        {
            if(arrayDeqeusAttempt.deque==null)
            {
                arrayDeqeusAttempt.deque = new int[MAX];
            }

            arrayDeqeusAttempt.deque[(++arrayDeqeusAttempt.rear)%MAX] = data;

            //This assumes that for a single element push, front is still initialized to MAX unless we explicitly pushToFront
//            if(front == -1)
//            {
//                //also initialize front for the first element, so that neither rear nor front are -1
//                ++front;
//            }

            if(arrayDeqeusAttempt.front == MAX)
            {
                //also initialize front for the first element, so that neither rear nor front are -1
                arrayDeqeusAttempt.front = arrayDeqeusAttempt.rear;
            }

            arrayDeqeusAttempt.size++;
            return;
        }

        arrayDeqeusAttempt.deque[(++arrayDeqeusAttempt.rear)%MAX] = data;
        arrayDeqeusAttempt.size++;
        return;
    }

    //Front keeps decrementing as we push and we start from MAX
    public void pushFromFront(ArrayDeqeusAttempt arrayDeqeusAttempt, Integer data)
    {
        if(data<0)
        {
            throw new IllegalArgumentException("Invalid data");
        }

        if(isFull(arrayDeqeusAttempt))
        {
            throw new RuntimeException("Stack is full");
        }

        if(arrayDeqeusAttempt.deque==null || isEmpty(arrayDeqeusAttempt))
        {
            if(arrayDeqeusAttempt.deque==null)
            {
                arrayDeqeusAttempt.deque = new int[MAX];
            }

            arrayDeqeusAttempt.deque[Math.abs((--arrayDeqeusAttempt.front/*+MAX*/)%MAX)] = data;

            //This assumes that for a single element push, rear is still initialized to -1 unless we explicitly pushToRear
//            if(rear == -1)
//            {
//                //also initialize front for the first element, so that neither rear nor front are -1
//                ++rear;
//            }

            if(arrayDeqeusAttempt.rear == -1)
            {
                //also initialize front for the first element, so that neither rear nor front are -1
                arrayDeqeusAttempt.rear = arrayDeqeusAttempt.front;
            }
            arrayDeqeusAttempt.size++;
            return;
        }

        arrayDeqeusAttempt.deque[Math.abs((--arrayDeqeusAttempt.front/*+MAX*/)%MAX)] = data;
            arrayDeqeusAttempt.size++;
        return;
    }

    public int removeFromRear(ArrayDeqeusAttempt arrayDeqeusAttempt)
    {
        int data = -1;
        if(arrayDeqeusAttempt.deque ==null || isEmpty(arrayDeqeusAttempt))
        {
            throw new RuntimeException("Queue is empty");
        }

        if(arrayDeqeusAttempt.rear==-1)
        {
            throw new RuntimeException("Stack is already empty from the rear");
        }

        //single element
        if(isSingleElement(arrayDeqeusAttempt))
        {
            //data = deque[(rear--)%MAX];
            data = arrayDeqeusAttempt.deque[(arrayDeqeusAttempt.rear)%MAX];
            arrayDeqeusAttempt.deque[(arrayDeqeusAttempt.rear--)%MAX] = -1;
            arrayDeqeusAttempt.size--;
            return data;
        }

        //data = deque[(rear--)%MAX];
        data = arrayDeqeusAttempt.deque[(arrayDeqeusAttempt.rear)%MAX];
        arrayDeqeusAttempt.deque[(arrayDeqeusAttempt.rear--)%MAX] = -1;
        arrayDeqeusAttempt.size--;
        return data;
    }

    public int removeFromFront(ArrayDeqeusAttempt arrayDeqeusAttempt)
    {
        int data = -1;
        if(arrayDeqeusAttempt.deque ==null || isEmpty(arrayDeqeusAttempt))
        {
            throw new RuntimeException("Queue is empty");
        }

        if(arrayDeqeusAttempt.front==MAX)
        {
            throw new RuntimeException("Stack is already empty from the front");
        }

        //single element
        if(isSingleElement(arrayDeqeusAttempt))
        {
            //data = deque[Math.abs((front++)+MAX)%MAX];
            data = arrayDeqeusAttempt.deque[Math.abs((arrayDeqeusAttempt.front)+MAX)%MAX];
            arrayDeqeusAttempt.deque[Math.abs((arrayDeqeusAttempt.front++)+MAX)%MAX] = -1;
            arrayDeqeusAttempt.size--;
            return data;
        }

        //data = deque[Math.abs((front++)+MAX)%MAX];
        data = arrayDeqeusAttempt.deque[Math.abs((arrayDeqeusAttempt.front)+MAX)%MAX];
        arrayDeqeusAttempt.deque[Math.abs((arrayDeqeusAttempt.front++)+MAX)%MAX] = -1;
        arrayDeqeusAttempt.size--;
        return data;
    }

    private boolean isFull(ArrayDeqeusAttempt arrayDeqeusAttempt) {
        return (arrayDeqeusAttempt.size==MAX);
    }

    private boolean isEmpty(ArrayDeqeusAttempt arrayDeqeusAttempt) {
        return (arrayDeqeusAttempt.size==0);
    }

    private boolean isSingleElement(ArrayDeqeusAttempt arrayDeqeusAttempt) {
        return (arrayDeqeusAttempt.size==1);
    }

    //TODO: Tricky !!!!
    //Only maximum code is below down : half of min and max : https://www.geeksforgeeks.org/sum-minimum-maximum-elements-subarrays-size-k/

    //https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/
    public Map<String, int[]> maxAndMinOfAllSubArrays(int[] inputArray, int interval)
    {
        int[] maxOfAllSubArrays=new int[inputArray.length-interval+1], minOfAllSubArrays= new int[inputArray.length-interval+1];
        Arrays.fill(maxOfAllSubArrays,-1);
        Arrays.fill(minOfAllSubArrays,-1);

        int maxOfAllSubArraysSize, minOfAllSubArraysSize;
        maxOfAllSubArraysSize = minOfAllSubArraysSize = -1;

        //maxAndMinOfAllSubArrays is descending sorted for 'useful' maximum elements
        //maxAndMinOfAllSubArrays is ascending sorted for 'useful' minimum elements

        //Check definition of 'useful' in the links above
        //deque holds 'useful' elements
        int i;
        for(i = 0;i<interval;i++)
        {
            //remove the losers in maxDeque's rear side since we maintain the maxDeque as descending sorted
            //front element > rearer elements
            //this is how we ensure the greatest ones remain, by removing the smaller ones
            while (maxArrayDeqeusAttempt.deque!=null && !isEmpty(
                maxArrayDeqeusAttempt) && inputArray[i]>inputArray[maxArrayDeqeusAttempt.deque[maxArrayDeqeusAttempt.rear]])
            {
                removeFromRear(maxArrayDeqeusAttempt);
            }

            pushToRear(maxArrayDeqeusAttempt, i);

            //remove the losers in minDeque's rear side since we maintain the deque as ascending sorted
            //front element < rearer elements
            //this is how we ensure the smalles ones remain, by removing the smaller ones
            while (minArrayDeqeusAttempt.deque!=null && !isEmpty(
                minArrayDeqeusAttempt) && inputArray[i]<inputArray[minArrayDeqeusAttempt.deque[minArrayDeqeusAttempt.rear]])
            {
                removeFromRear(minArrayDeqeusAttempt);
            }
            pushToRear(minArrayDeqeusAttempt, i);
        }

        for(;i<inputArray.length;i++)
        {
            //for the last iteration, print out the maxOfSubArray
            if(maxArrayDeqeusAttempt.deque!=null && !isEmpty(maxArrayDeqeusAttempt)) //&& front!=MAX
            {
                maxOfAllSubArrays[++maxOfAllSubArraysSize] = inputArray[maxArrayDeqeusAttempt.deque[maxArrayDeqeusAttempt.front]];
            }

            //for the last iteration, print out the maxOfSubArray
            if(minArrayDeqeusAttempt.deque!=null && !isEmpty(minArrayDeqeusAttempt)) //&& front!=MAX
            {
                minOfAllSubArrays[++minOfAllSubArraysSize] = inputArray[minArrayDeqeusAttempt.deque[minArrayDeqeusAttempt.front]];
            }

            //remove useless elements from the deque if they are outside of (i-interval)
            //since front will contain the greater elements, again ensure only valid-in-window remain
            while (maxArrayDeqeusAttempt.deque!=null && !isEmpty(
                maxArrayDeqeusAttempt) && (i- maxArrayDeqeusAttempt.deque[maxArrayDeqeusAttempt.front])>=interval) //&& front!=MAX
            {
                //problem here, since the current deque implementations need to explicitly add/remove elements. Rear and front have nothing to do with each other
                removeFromFront(maxArrayDeqeusAttempt);
            }

            //remove useless elements from the deque if they are outside of (i-interval)
            //since front will contain the smaller elements, again ensure only valid-in-window remain
            while (minArrayDeqeusAttempt.deque!=null && !isEmpty(
                minArrayDeqeusAttempt) && (i- minArrayDeqeusAttempt.deque[minArrayDeqeusAttempt.front])>=interval) //&& front!=MAX
            {
                //problem here, since the current deque implementations need to explicitly add/remove elements. Rear and front have nothing to do with each other
                removeFromFront(minArrayDeqeusAttempt);
            }

            //remove the losers in deque's rear side since we maintain the deque as descending sorted
            //front element > rear-er elements
            //this is how we ensure the greatest ones remain, by removing the smaller ones
            while (maxArrayDeqeusAttempt.deque!=null && !isEmpty(
                maxArrayDeqeusAttempt) && inputArray[i]>inputArray[maxArrayDeqeusAttempt.deque[maxArrayDeqeusAttempt.rear]])
            {
                removeFromRear(maxArrayDeqeusAttempt);
            }

            //finally push the element to the rear
            pushToRear(maxArrayDeqeusAttempt, i);

            //remove the losers in deque's rear side since we maintain the deque as ascending sorted
            //front element < rear-er elements
            //this is how we ensure the smalles ones remain, by removing the smaller ones
            while (minArrayDeqeusAttempt.deque!=null && !isEmpty(
                minArrayDeqeusAttempt) && inputArray[i]<inputArray[minArrayDeqeusAttempt.deque[minArrayDeqeusAttempt.rear]])
            {
                removeFromRear(minArrayDeqeusAttempt);
            }

            //finally push the element to the rear
            pushToRear(minArrayDeqeusAttempt, i);
        }

        while (maxArrayDeqeusAttempt.deque!=null && !isEmpty(
            maxArrayDeqeusAttempt) && (i- maxArrayDeqeusAttempt.deque[maxArrayDeqeusAttempt.front])>interval) //&& front!=MAX
        {
            //problem here, since the current deque implementations need to explicitly add/remove elements. Rear and front have nothing to do with each other
            removeFromFront(maxArrayDeqeusAttempt);
        }
        maxOfAllSubArrays[++maxOfAllSubArraysSize] = inputArray[removeFromFront(maxArrayDeqeusAttempt)];

        while (minArrayDeqeusAttempt.deque!=null && !isEmpty(
            minArrayDeqeusAttempt) && (i- minArrayDeqeusAttempt.deque[minArrayDeqeusAttempt.front])>interval) //&& front!=MAX
        {
            //problem here, since the current deque implementations need to explicitly add/remove elements. Rear and front have nothing to do with each other
            removeFromFront(minArrayDeqeusAttempt);
        }
        minOfAllSubArrays[++minOfAllSubArraysSize] = inputArray[removeFromFront(minArrayDeqeusAttempt)];

        Map returnValue = new HashMap<>();
        returnValue.put("max",maxOfAllSubArrays);
        returnValue.put("min",minOfAllSubArrays);

        return returnValue;
    }

    // ************* // *************  // ************* // *************  // *************  // *************

    /*//Rear keeps incrementing as we push and we start from -1
    public void pushToRear(Integer data)
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
                deque = new Integer[MAX];
            }

            deque[(++rear)%MAX] = data;

            //This assumes that for a single element push, front is still initialized to MAX unless we explicitly pushToFront
//            if(front == -1)
//            {
//                //also initialize front for the first element, so that neither rear nor front are -1
//                ++front;
//            }

            if(front == MAX)
            {
                //also initialize front for the first element, so that neither rear nor front are -1
                front = rear;
            }

            size++;
            return;
        }

        deque[(++rear)%MAX] = data;
        size++;
        return;
    }

    //Front keeps decrementing as we push and we start from MAX
    public void pushFromFront(Integer[] deque, Integer front, Integer rear, Integer size, Integer data)
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
                deque = new Integer[MAX];
            }

            deque[Math.abs((--front*//*+MAX*//*)%MAX)] = data;

            //This assumes that for a single element push, rear is still initialized to -1 unless we explicitly pushToRear
//            if(rear == -1)
//            {
//                //also initialize front for the first element, so that neither rear nor front are -1
//                ++rear;
//            }

            if(rear == -1)
            {
                //also initialize front for the first element, so that neither rear nor front are -1
                rear = front;
            }
            size++;
            return;
        }

        deque[Math.abs((--front*//*+MAX*//*)%MAX)] = data;
        size++;
        return;
    }

    public int removeFromRear(Integer[] deque, Integer front, Integer rear, Integer size)
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
            //data = deque[(rear--)%MAX];
            data = deque[(rear)%MAX];
            deque[(rear--)%MAX] = -1;
            size--;
            return data;
        }

        //data = deque[(rear--)%MAX];
        data = deque[(rear)%MAX];
        deque[(rear--)%MAX] = -1;
        size--;
        return data;
    }

    public int removeFromFront(Integer[] deque, Integer front, Integer rear, Integer size)
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
            //data = deque[Math.abs((front++)+MAX)%MAX];
            data = deque[Math.abs((front)+MAX)%MAX];
            deque[Math.abs((front++)+MAX)%MAX] = -1;
            size--;
            return data;
        }

        //data = deque[Math.abs((front++)+MAX)%MAX];
        data = deque[Math.abs((front)+MAX)%MAX];
        deque[Math.abs((front++)+MAX)%MAX] = -1;
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


    //TODO: Tricky
    //Maximum of all sub-arrays of size k : https://www.geeksforgeeks.org/maximum-of-all-subarrays-of-size-k/
    //Sum of minimum and maximum elements of all subarrays of size k : https://www.geeksforgeeks.org/sum-minimum-maximum-elements-subarrays-size-k/
    public int[] maxAndMinOfAllSubArrays(int[] inputArray, int interval)
    {
        int[] sumOfMaxAndMinOfAllSubArrays = new int[inputArray.length-interval];
        Arrays.fill(sumOfMaxAndMinOfAllSubArrays,-1);
        int[] maxAndMinOfAllSubArrays=new int[inputArray.length-interval+1], minOfAllSubArrays= new int[inputArray.length-interval+1];
        Arrays.fill(maxAndMinOfAllSubArrays,-1);
        Arrays.fill(minOfAllSubArrays,-1);

        int maxOfAllSubArraysSize, minOfAllSubArraysSize;
        maxOfAllSubArraysSize = minOfAllSubArraysSize = -1;

        //maxAndMinOfAllSubArrays is descending sorted for 'useful' maximum elements
        //maxAndMinOfAllSubArrays is ascending sorted for 'useful' minimum elements


        //Check definition of 'useful' in the links above
        //deque holds 'useful' elements
        int i;
        for(i = 0;i<interval;i++)
        {
            //remove the losers in deque's rear side since we maintain the deque as descending sorted
            //front element > rearer elements
            //this is how we ensure the greatest ones remain, by removing the smaller ones
            while (deque!=null && !isEmpty() && inputArray[i]>inputArray[deque[rear]])
            {
                removeFromRear();
            }
            pushToRear(i);
        }

        for(;i<inputArray.length;i++)
        {
            //for the last iteration, print out the maxOfSubArray
            if(deque!=null && !isEmpty() *//*&& front!=MAX*//*)
            {
                maxAndMinOfAllSubArrays[++maxOfAllSubArraysSize] = inputArray[deque[front]];
            }

            //remove useless elements from the deque if they are outside of (i-interval)
            //since front will contain the greater elements, again ensure only valid-in-window remain
            while (deque!=null && !isEmpty() *//*&& front!=MAX*//* && (i-deque[front])>=interval)
            {
                //problem here, since the current deque implementations need to explicitly add/remove elements. Rear and front have nothing to do with each other
                removeFromFront();
            }

            //remove the losers in deque's rear side since we maintain the deque as descending sorted
            //front element > rear-er elements
            //this is how we ensure the greatest ones remain, by removing the smaller ones
            while (deque!=null && !isEmpty() && inputArray[i]>inputArray[deque[rear]])
            {
                removeFromRear();
            }

            //finally push the element to the rear
            pushToRear(i);
        }

        while (deque!=null && !isEmpty() *//*&& front!=MAX*//* && (i-deque[front])>interval)
        {
            //problem here, since the current deque implementations need to explicitly add/remove elements. Rear and front have nothing to do with each other
            removeFromFront();
        }
            maxAndMinOfAllSubArrays[++maxOfAllSubArraysSize] = inputArray[removeFromFront()];

        return maxAndMinOfAllSubArrays;
    }*/

    // ************* // *************  // ************* // *************  // *************  // *************


}
