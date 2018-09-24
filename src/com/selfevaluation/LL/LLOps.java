package com.selfevaluation.LL;

import com.selfevaluation.base.LinkedList;
import com.selfevaluation.base.LinkedList.Node;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LLOps {

    private LinkedList linkedList;

    public LLOps(LinkedList linkedList) {
        this.linkedList = linkedList;
        if(linkedList!=null)
        {
            this.linkedList.setHead(null);
        }
    }

    /* TODO : Insert at start does not need any loop. Only pointer modification.
              Insert in the middle/last does need loop. */
    /* TODO : Its ok for the container to not exist for insert operations.*/
    //Time Complexity : O(1)
    public void insertAtFront(Node nodeToBeAdded) {
        if(linkedList == null)
        {
            linkedList =  new LinkedList();
            linkedList.setHead(nodeToBeAdded);
            incrementListSize();
            return;
        }
        nodeToBeAdded.setNext(linkedList.getHead());

        //Modifying the list pointer
        linkedList.setHead(nodeToBeAdded);
        incrementListSize();
    }

    //Time : O(n) since involves traversing the list
    public void insertAfterGivenNode(Node nodeToBeAdded, int value) {

        if(nodeToBeAdded == null)
        {
            throw new IllegalArgumentException("Input is empty");
        }

        //Zero-element case
        if (linkedList == null)
        {
            linkedList =  new LinkedList();
            linkedList.setHead(nodeToBeAdded);
            incrementListSize();
            return;
        }

        //current = 1st  element (head)
        Node current = linkedList.getHead();

        // Its a bit easier to handle 1-element cases, separately. But leads to code duplication and hairy if-else
        // Better to have a standard SOP for handling them as part of normal addition
        // May be check (getNext == null) at the last ?

        //handle 1 element case separately. NOT ANYMORE due to above guidance
        /*if(current.getNext()==null && current.getData() == value)
        {
            current.setNext(nodeToBeAdded);
            nodeToBeAdded.setNext(null);
            incrementListSize();
            return;
        }
        else if(current.getNext()!=null)
        {*/
            //don't want to make use of size here, since its not standard
            //(current != last->next)
            while(current!=null && current.getData() != value)
            {
                current = current.getNext();
            }

            //first-if-condition-check
            if(current==null)
            {
                throw new RuntimeException("Reached end of the list but element not found");
            }

            //second-if-condition-check
            if(current.getData()==value)
            {
                //This looks like a standard thing for single linked list
                Node tmp = current.getNext();
                current.setNext(nodeToBeAdded);
                nodeToBeAdded.setNext(tmp);
                incrementListSize();
            }
        //}

        printList(linkedList.getHead());
        System.out.println("\n########");
    }

    public void insertAtEnd(Node nodeToBeAdded) {
        if(nodeToBeAdded == null)
        {
            throw new IllegalArgumentException("Input is empty");
        }

        //zero-element case
        if(linkedList == null)
        {
            linkedList =  new LinkedList();
            linkedList.setHead(nodeToBeAdded);
            incrementListSize();
            return;
        }

        //current = 1st element
        Node current = linkedList.getHead();
        Node prev = null;

        //handle 1 element case separately. NOT ANYMORE due to guidance:
        //TODO: The battle between curr and prev again.Lets just standardize our use on the lines of https://www.geeksforgeeks.org/linked-list-set-3-deleting-node
        /*if(current.getNext()==null)
        {
            current.setNext(nodeToBeAdded);
            nodeToBeAdded.setNext(null);
            incrementListSize();
            return ;
        }
        //Multi element case
        else
        {*/
            //(current != last->next)
            while (current!=null)
            {
                prev = current;
                current = current.getNext();
            }

            //This looks like a standard thing for single linked list
            Node tmp = prev.getNext();
            prev.setNext(nodeToBeAdded);
            nodeToBeAdded.setNext(tmp);
            incrementListSize();
        //}

        printList(linkedList.getHead());
        System.out.println("\n########");
    }

    public void delete(int value) {
        if (linkedList == null || linkedList.getHead()==null || value < 0) {
            throw new RuntimeException("Input is empty");
        }

        //current = 1st element
        Node current = linkedList.getHead();
        Node prev = null;

        //TODO: The battle between curr and prev again.Lets just standardize our use on the lines of https://www.geeksforgeeks.org/linked-list-set-3-deleting-node
        //                        ##############
        //        while (current.getNext() != null && current.getData() != value) {
        //            prev = current;
        //            current = current.getNext();
        //        }
        //
        //        if (current.getNext() == null) {
        //            if (current.getData() != value) {
        //                throw new IllegalArgumentException("Reached end of the list but element not found");
        //            } else if (current.getData() == value) {
        //                    /*//single element
        //                    if(prev == null) {
        //                        current = null;
        //                        decrementListSize();
        //                    }
        //                    else {
        //                        //deleted last element
        //                        current =null;
        //                        prev.setNext(current.getNext());
        //                        decrementListSize();
        //                    }*/
        //
        //                    if(prev != null)
        //                    {
        //                        prev.setNext(current.getNext());
        //                    }
        //                    current = null;
        //                    decrementListSize();
        //            }
        //        }
        //        else
        //        {
        //            prev.setNext(current.getNext());
        //            current = null;
        //            decrementListSize();
        //        }
        // ##############

        //TODO: As prescribed at https://www.geeksforgeeks.org/linked-list-set-3-deleting-node/
        //1-element case ...thre is no zero-element list case in deletion
        if(current!=null && current.getData() == value)
        {
            linkedList.setHead(current.getNext());
            decrementListSize();
            return;
        }

        //current != last->next)
        while (current!=null && current.getData()!=value)
        {
            prev = current;
            current = current.getNext();
        }

        //first-if-condition-check
        if(current==null)
        {
            throw new RuntimeException("Reached end of the list but element not found");
        }

        //Implicit check for second part of the 'if-condition'
        //Last node, middle node all treated here
        prev.setNext(current.getNext());
        decrementListSize();

        printList(linkedList.getHead());
        System.out.println("\n########");
    }

    //TODO: Got simpler due to logic as prescribed at https://www.geeksforgeeks.org/linked-list-set-3-deleting-node/
    public void deleteAtPosition(int position) {
        if (linkedList == null || linkedList.getHead()==null || position < 0) {
            throw new RuntimeException("Input is invalid");
        }

        //current = 1st element
        Node current = linkedList.getHead();
        Node prev = null;

        int i = 0;
        //Single element list
        if(current!=null && i==position)
        {
            linkedList.setHead(current.getNext());
            decrementListSize();
            return;
        }

        //(current != last->next)
        while(current!=null && i<position)
        {
            prev = current;
            current = current.getNext();
            i++;
        }

        //first-if-condition-check
        if(current==null)
        {
            throw new RuntimeException("Reached end of the list but element not found");
        }

        //second-if-condition-check
        //de-link
        //implicit
        if(i==position)
        {
            prev.setNext(current.getNext());
            decrementListSize();
        }
        printList(linkedList.getHead());
        System.out.println("\n########");
    }


    public int findListSize() {
        long startTime = System.currentTimeMillis();
        if (linkedList == null) {
            throw new RuntimeException("Input is invalid");
        }

        Node current = linkedList.getHead();

        //TODO: Prev only needed when there is update/mutation(add/delete) of list. Not while just traversing
        int i=0;
        while (current!=null)
        {
            i++;
            current = current.getNext();
        }

        System.out.println("List size is: "+ i);
        System.out.println("Time taken by findListSize "+ Long.toString(System.currentTimeMillis()-startTime));

        return i;
    }

    public int findListSizeRecursively() {
        long startTime = System.currentTimeMillis();
        if (linkedList == null) {
            throw new RuntimeException("Input is invalid");
        }

        Node current = linkedList.getHead();

        //TODO: Prev only needed when there is update/mutation(add/delete) of list. Not while just traversing
        int size  =  findListSizeRecursively(current,0);
        System.out.println("Time taken by findListSizeRecursively "+ Long.toString(System.currentTimeMillis()-startTime));
        System.out.println("List size is: "+ size);
        return  size;
    }

    private int findListSizeRecursively(Node current, int i) {
        if(current==null)
        {
            return i;
        }
        return findListSizeRecursively(current.getNext(),++i);
    }

    //:::Tricky:::
    public boolean swapElements(int element1, int element2) {

        if(linkedList == null || element1<0 || element2<0)
        {
            throw new IllegalArgumentException("Invalid input");
        }

        Node current = linkedList.getHead();
        Node prev, prev1, prev2, element1Tracker, element2Tracker;
        prev = prev1 = prev2 = element1Tracker = element2Tracker =null;

        //if(current!=null && (current.getData()==element1 || current.getData()==element2))
        //(current != last->next)
        while(current!=null)
        {
            if(current.getData()==element1)
            {
                element1Tracker = current;
                prev1 = prev;
            }
            if(current.getData() == element2)
            {
                element2Tracker = current;
                prev2 = prev;
            }

            prev = current;
            current = current.getNext();

            //find the nodes
            //find their prev
            //swap prev1.next =  new & new.next = curr1.next & similarly for prev2
         }

         if(element1Tracker == null || element2Tracker == null)
         {
             throw new RuntimeException("Reached end of the list but element not found");
         }

         if(element1Tracker!=null && element2Tracker!=null)
         {
             Node tmp = element2Tracker.getNext();
             if(prev1!=null)
             {
                 prev1.setNext(element2Tracker);
             }
             else{
                 linkedList.setHead(element2Tracker);
             }
             if(prev2!=null)
             {
                 prev2.setNext(element1Tracker);
             }
             else{
                 linkedList.setHead(element1Tracker);
             }

            element2Tracker.setNext(element1Tracker.getNext());
            element1Tracker.setNext(tmp);

            //Also set head
            if(element1Tracker.getData() == linkedList.getHead().getData())
            {
                linkedList.setHead(element2Tracker);
            }

            printList(linkedList.getHead());
            System.out.println("\n########");

            return true;
         }
        return false;
    }

    public boolean reverseList() {
        if(linkedList==null)
        {
            throw new RuntimeException("Invalid Input");
        }

        Node current = linkedList.getHead();
        Node prev = null, next = null;

        //1-element case
        if(current.getNext()==null)
        {
            return true;
        }

        //(current != last->next)
        while (current!=null)
        {
            next = current.getNext();
            current.setNext(prev);

            prev = current;
            current = next;
        }
        //Set head since that is the only pointer that the lists are aware of
        linkedList.setHead(prev);

        printList(linkedList.getHead());
        System.out.println("\n########");

        return true;
    }

    //:::Tricky::: See other variants of this at : https://www.geeksforgeeks.org/merge-two-sorted-linked-lists/
    //Did inplace here
    public Node mergeSortedLists(LinkedList linkedList, LinkedList otherLinkedList) {
        if(linkedList==null && otherLinkedList==null)
        {
            throw new IllegalArgumentException("Invalid Input");
        }

        if(linkedList==null)
        {
            return otherLinkedList.getHead();
        }

        if(otherLinkedList==null)
        {
            return linkedList.getHead();
        }

        Node currentFirst = null, currentSecond = null;
        Node  prevFirst = null;
        Node prevSecond = null;
        Node  nextFirst = null;
        Node nextSecond = null;

        Node start = null;

        if(linkedList.getHead().getData()<= otherLinkedList.getHead().getData())
        {
            currentFirst= linkedList.getHead();
            currentSecond = otherLinkedList.getHead();
        }
        else {

            currentFirst = otherLinkedList.getHead();
            currentSecond = linkedList.getHead();
        }
        start = currentFirst;

        //Trying in-place merging
        //(current != last->next)
        while (currentFirst!=null && currentSecond!=null)
        {
            if(currentFirst.getData()<=currentSecond.getData())
            {
                prevFirst = currentFirst;
                currentFirst = currentFirst.getNext();

                if((prevFirst!=null && prevFirst.getData()<= currentSecond.getData()) && (currentFirst!=null && currentFirst.getData()>=currentSecond.getData()))
                {
                    nextSecond = currentSecond.getNext();
                    nextFirst = currentFirst.getNext();

                    prevFirst.setNext(currentSecond);
                    currentSecond.setNext(currentFirst);

                    prevFirst = prevFirst.getNext();
                    currentSecond = nextSecond;
                }
            }
        }

        //linkedList  is exhausted
        if(currentSecond!=null)
        {
            prevFirst.setNext(currentSecond);
            currentSecond = currentSecond.getNext();
        }

        printList(start);
        System.out.println("\n########");

        return linkedList.getHead();
    }

    private void incrementListSize() {
        linkedList.setSize(linkedList.getSize()+1);
    }

    private void decrementListSize() {
        if(linkedList.getSize()>0)
        {
            linkedList.setSize(linkedList.getSize()-1);
        }
    }

    public static void printList(Node node)
    {
        if(node != null)
        {
            System.out.println();
            while (node != null)
            {
                System.out.print(node.getData());
                if(node.getNext()!=null)
                {
                    System.out.print("-->");
                }
                node = node.getNext();
            }
        }
    }
}


//Concrete
/*
public Node mergeSortedLists(LinkedList firstLinkedList, LinkedList secondLinkedList) {
    if(firstLinkedList==null && secondLinkedList==null)
    {
        throw new IllegalArgumentException("Invalid input");
    }

    if(firstLinkedList==null || firstLinkedList.getHead()==null)
    {
        return secondLinkedList.getHead();
    }
    else if (secondLinkedList ==null || secondLinkedList.getHead()==null)
    {
        return firstLinkedList.getHead();
    }

    Node currentFirst = firstLinkedList.getHead();
    Node prevFirst = null;
    Node nextFirst = null;
    Node currentSecond = secondLinkedList.getHead();
    Node prevSecond = null;
    Node nextSecond = null;

    //merging second in to the first while both last
    while(currentFirst!=null && currentSecond!=null)
    {
        if(currentFirst.getData()<currentSecond.getData())
        {
            prevFirst = currentFirst;
            currentFirst = currentFirst.getNext();
        }

        else
        {
            nextFirst = currentFirst.getNext();
            if(prevFirst!=null)
            {
                prevFirst.setNext(currentSecond);
            }
            nextSecond = currentSecond.getNext();
            currentSecond.setNext(currentFirst);
            if(prevSecond!=null)
            {
                prevSecond.setNext(nextSecond);
            }

            prevFirst = currentSecond;
            currentFirst = nextFirst;
            currentSecond = nextSecond;

                */
/*nextFirst = currentFirst.getNext();
                nextSecond = currentSecond.getNext();
                currentFirst.setNext(currentSecond);
                currentSecond.setNext(nextFirst);

                prevFirst = currentSecond;
                currentFirst = nextFirst;
                currentSecond = nextSecond;*//*

        }
    }

    //Append un-finished list
    if(currentSecond!=null)
    {
        while(currentSecond!=null)
        {
            prevFirst.setNext(currentSecond);
            prevFirst = currentSecond;
            currentSecond = currentSecond.getNext();
        }
    }
    firstLinkedList.setHead(firstLinkedList.getHead());
    printList(firstLinkedList);
    System.out.println("\n########");

    return firstLinkedList.getHead();
}*/
