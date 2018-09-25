package com.selfevaluation.LL;

import com.selfevaluation.base.SingleCircularLinkedList;
import com.selfevaluation.base.SingleCircularLinkedList.Node;
import lombok.Getter;
import lombok.Setter;

//Feels so wierd designing the new data-structure
@Setter
@Getter
public class SingleCircularListOps {

    private SingleCircularLinkedList singleCircularLinkedList;

    public SingleCircularListOps(SingleCircularLinkedList singleCircularLinkedList)
    {
        this.singleCircularLinkedList = singleCircularLinkedList;
        if(singleCircularLinkedList!=null)
        {
            //Keeping the structure same as single linked list
            this.singleCircularLinkedList.setLast(null);
            //this.singleCircularLinkedList.setLast(singleCircularLinkedList.getLast());
        }
    }

    public void insertAtEnd(Node nodeToBeAdded) {
        if(nodeToBeAdded == null)
        {
            throw new IllegalArgumentException("Input is empty.");
        }

        //Zero-element case
        if (singleCircularLinkedList==null || singleCircularLinkedList.getLast()==null)
        {
            singleCircularLinkedList = new SingleCircularLinkedList();
            singleCircularLinkedList.setLast(nodeToBeAdded);
            singleCircularLinkedList.getLast().setNext(nodeToBeAdded);
            incrementListSize();
            return;
        }

        Node prevLast = singleCircularLinkedList.getLast();
        singleCircularLinkedList.setLast(nodeToBeAdded);
        singleCircularLinkedList.getLast().setNext(prevLast.getNext());
        prevLast.setNext(singleCircularLinkedList.getLast());
        incrementListSize();

        //printList(singleCircularLinkedList.getLast());
        return;
    }

    /* TODO : Insert at start does not need any loop. Only pointer modification.
              Insert in the last does not need loop in case of Circular linked list.
              Insert in the middle does need looping in case of Circular linked list */
    public void insertAtStart(Node nodeToBeAdded) {
        if(nodeToBeAdded == null)
        {
            throw new IllegalArgumentException("Input is empty.");
        }

        //Zero element
        if(singleCircularLinkedList==null || singleCircularLinkedList.getLast()==null)
        {
            singleCircularLinkedList =  new SingleCircularLinkedList();
            singleCircularLinkedList.setLast(nodeToBeAdded);
            singleCircularLinkedList.getLast().setNext(nodeToBeAdded);
            incrementListSize();

            //printList(singleCircularLinkedList.getLast());
            return;
        }

        Node tmp = singleCircularLinkedList.getLast().getNext();
        singleCircularLinkedList.getLast().setNext(nodeToBeAdded);
        incrementListSize();
        singleCircularLinkedList.getLast().getNext().setNext(tmp);

        //printList(singleCircularLinkedList.getLast());
        return;
    }

    public void insertAfterNode(Node nodeToBeAdded, int nodeDataToBeAddedAfter) {
        if(nodeToBeAdded == null || nodeToBeAdded.getData()<0 || nodeDataToBeAddedAfter<0 || (nodeDataToBeAddedAfter>=0 && singleCircularLinkedList == null ))
        {
            throw new IllegalArgumentException("Input is empty.");
        }

        //Zero element
        if(singleCircularLinkedList == null || singleCircularLinkedList.getLast()==null)
        {
            singleCircularLinkedList = new SingleCircularLinkedList();
            singleCircularLinkedList.setLast(nodeToBeAdded);
            singleCircularLinkedList.getLast().setNext(nodeToBeAdded);
            incrementListSize();
            return;
        }

        //current = 1st  element (head)
        Node current = singleCircularLinkedList.getLast().getNext();
        /* Generally i like it like the Single connected-LL where we proceed from head to (last->next) null
           However in case of Circular-LL, there last-> next = first . So the while loop wont even start by the logic of
           (current != singleCircularLinkedList.getLast().getNext) */

        /* I don't like this, since we stop at the second-last node and have to handle the last by going from last->next
        (which is the head in the CLL) & going till (current != singleCircularLinkedList.getLast())*/

        //Better to maintain SLL like flow from : head to (last->next) & also isBeginning flag to indicate start or a loop completion
        boolean isBeginning = true;
        //(current != last->next)
        while ((current != singleCircularLinkedList.getLast().getNext() || isBeginning) && current.getData()!=nodeDataToBeAddedAfter)
        {
            isBeginning = false;
            current = current.getNext();
        }

        //first-if-condition-check
        if(current==singleCircularLinkedList.getLast().getNext() && !isBeginning)
        {
            throw new RuntimeException("Reached end of the list but element not found");
        }

        //second-if-condition-check
        if(current.getData() == nodeDataToBeAddedAfter)
        {
            Node tmp = current.getNext();
            current.setNext(nodeToBeAdded);
            nodeToBeAdded.setNext(tmp);
            incrementListSize();
        }

        //DS link manipulation
        if(nodeToBeAdded.getNext()==singleCircularLinkedList.getLast().getNext())
        {
            singleCircularLinkedList.setLast(nodeToBeAdded);
        }
        //printList(singleCircularLinkedList.getLast().getNext());
        //System.out.println("\n########");
    }

    public void deleteNode(int value) {
        if(singleCircularLinkedList == null || singleCircularLinkedList.getLast() == null)
        {
            throw new RuntimeException("Empty list");
        }

        //Zero-1 element linked list
        if(singleCircularLinkedList.getLast()==singleCircularLinkedList.getLast().getNext())
        {
            if(value == singleCircularLinkedList.getLast().getData())
            {
                decrementListSize();
                singleCircularLinkedList.setLast(null);
                return;
            }
        }

        //current = 1st  element (head)
        Node current = singleCircularLinkedList.getLast().getNext();
        //prev = prevToCurrent
        Node prev = singleCircularLinkedList.getLast();
        boolean isStart = true;

        //(current != last->next)
        while ((current != singleCircularLinkedList.getLast().getNext() || isStart) && current.getData()!=value)
        {
            isStart = false;
            prev = current;
            current = current.getNext();
        }

        ////first-if-condition-check  but with (isStart = false) to mark that
        if(current==singleCircularLinkedList.getLast().getNext() && !isStart)
        {
            throw new RuntimeException("Reached end of the list but element not found");
        }

        //second-if-condition-check
        if(current.getData()==value)
        {
            prev.setNext(current.getNext());
            decrementListSize();
        }

        //Modifying the list pointer, since we just deleted the node pointed to by last pointer
        if(current == singleCircularLinkedList.getLast())
        {
            singleCircularLinkedList.setLast(prev);
        }

        return;
    }

    public void deleteNodeAtPosition(int position) {
        if(singleCircularLinkedList == null || singleCircularLinkedList.getLast()==null)
        {
            throw new RuntimeException("Invalid input");
        }
        if(position<0)
        {
            throw new IllegalArgumentException("Invalid input");
        }

        //1-element case
        if(singleCircularLinkedList.getLast() == singleCircularLinkedList.getLast().getNext())
        {
            if(position==0)
            {
                singleCircularLinkedList.setLast(null);
                //dont bother making the node's next as null..just dis-engage from the list
                decrementListSize();
                return;
            }
        }

        //current = 1st  element (head)
        Node current = singleCircularLinkedList.getLast().getNext();
        //prev = prevToCurrent
        Node prev = singleCircularLinkedList.getLast();
        boolean isStart = true;
        int currentPos = 0;

        //(current != last->next)
        while ((current != singleCircularLinkedList.getLast().getNext() || isStart) && (currentPos < position))
        {
            isStart = false;
            prev = current;
            current = current.getNext();
            currentPos++;
        }

        //first-if-condition-check
        if(current == singleCircularLinkedList.getLast().getNext() && !isStart)
        {
            //throwing illegal-argument exception here since position argument was more than list size
            throw new IllegalArgumentException("Reached end of the list but position not found");
        }

        //second-if-condition-check
        if(currentPos == position)
        {
            prev.setNext(current.getNext());
            decrementListSize();
        }

        //Modifying the list pointer, since we just deleted the node pointed to by last pointer
        if(current == singleCircularLinkedList.getLast())
        {
            singleCircularLinkedList.setLast(prev);
        }
        return;
    }

    public void splitInMiddle() {
        if(singleCircularLinkedList == null || singleCircularLinkedList.getLast() == null)
        {
            throw new RuntimeException("Invalid input");
        }

        //1-element case
        if(singleCircularLinkedList.getLast() == singleCircularLinkedList.getLast().getNext())
        {
            return;
        }

        //find size
        //current = 1st  element (head)
        Node current = singleCircularLinkedList.getLast().getNext();
        int size = 0;
        boolean isStart = true;

        //current != last->next
        while (current!= singleCircularLinkedList.getLast().getNext() || isStart)
        {
            isStart = false;
            current = current.getNext();
            size++;
        }

        //traverse till size/2
        current = singleCircularLinkedList.getLast().getNext();
        isStart = true;

        //critical to determine where the odd-size list's middle element will go
        //for middle element to fall in first-list for the odd-numbered list
        int i = (size%2 == 1)?0:1;

        // when measuring against size, better to measure relative to 1 unlike array-indices
        // for e.g.-> i =0, size =4 , size/2 = 2 ..but for 0-startIndex traversal would cover 3 elements till index-2 leaving only 1 element to spare
        while ((current!= singleCircularLinkedList.getLast().getNext() || isStart) && i < (size/2))
        {
            isStart = false;
            current = current.getNext();
            i++;
        }

        //first-condition-check
        if(current == singleCircularLinkedList.getLast().getNext() && !isStart)
        {
            //???
        }

        //second-condition-check
      if (i == (size/2))
        {
            Node secondListLast = singleCircularLinkedList.getLast();
            Node secondListFirst = current.getNext();

            current.setNext(singleCircularLinkedList.getLast().getNext());
            singleCircularLinkedList.setLast(current);
            singleCircularLinkedList.setSize(size/2);

            System.out.print("Original list:\t");
            printList(singleCircularLinkedList.getLast());

            SingleCircularLinkedList secondCircularLinkedList =  new SingleCircularLinkedList();
            secondCircularLinkedList.setLast(secondListLast);
            secondCircularLinkedList.getLast().setNext(secondListFirst);
            secondCircularLinkedList.setSize(size/2);

            System.out.print("Second list:\t");
            printList(secondCircularLinkedList.getLast());
            return;
        }

    }

    //More like insertBeforeTheNextGreaterNodeInSortedList
    public void sortedInsert(int nodeValueToBeAdded) {
        if(nodeValueToBeAdded<0)
        {
            throw new IllegalArgumentException("Invalid input");
        }

        Node newNode = new Node(nodeValueToBeAdded);

        //Zero-list case
        if(singleCircularLinkedList == null || singleCircularLinkedList.getLast() == null)
        {
            singleCircularLinkedList =  new SingleCircularLinkedList();
            singleCircularLinkedList.setLast(newNode);
            singleCircularLinkedList.getLast().setNext(newNode);
            incrementListSize();
            return;
        }

        //current = 1st  element (head)
        Node current = singleCircularLinkedList.getLast().getNext();
        //prev = prevToCurrent
        Node prev = singleCircularLinkedList.getLast();
        boolean isStart = true;

        while ((current!=singleCircularLinkedList.getLast().getNext() || isStart) && nodeValueToBeAdded>=current.getData())
        {
            isStart = false;
            prev = current;
            current = current.getNext();
        }

        //First-condition-check
        if(current == singleCircularLinkedList.getLast().getNext() && !isStart)
        {
            newNode.setNext(singleCircularLinkedList.getLast().getNext());
            singleCircularLinkedList.getLast().setNext(newNode);
            singleCircularLinkedList.setLast(newNode);
            incrementListSize();
            return;
        }

        //Second-condition-check
        if(nodeValueToBeAdded<current.getData())
        {
            Node next = prev.getNext();
            prev.setNext(newNode);
            newNode.setNext(next);
            incrementListSize();
        }

        //modify the list pointer
        if(current == singleCircularLinkedList.getLast())
        {
            singleCircularLinkedList.setLast(newNode);
        }

        return;
    }

    private void decrementListSize() {
        if(singleCircularLinkedList!=null)
        {
            singleCircularLinkedList.setSize(singleCircularLinkedList.getSize()-1);
        }
        return;
    }

    private void incrementListSize() {
        if(singleCircularLinkedList!=null)
        {
            singleCircularLinkedList.setSize(singleCircularLinkedList.getSize()+1);
        }
        return;
    }

    public static void printList(Node last) {
        if(last==null)
        {
            return;
        }

        System.out.println("\n Printing the list");

        //Single list
        if(last.getNext()==last)
        {
            System.out.println(last.getData());
            return;
        }

        //This is one technique to traverse a singly-connected circular linked list, but would need to handle last node case separately
        /*Node originalLast = last;
        Node start = last.getNext();

        while(start!=originalLast)
        {
            System.out.print(start.getData());
            start = start.getNext();
            System.out.print(" => ");
        }
        System.out.print(originalLast.getData());*/

        Node current = last.getNext();
        boolean isStart = true;

        while ((current!=last.getNext() || isStart))
        {
            isStart = false;
            System.out.print(current.getData());
            current = current.getNext();
            if(current != last.getNext())
            {
                System.out.print("==>");
            }
        }
        System.out.println();
    }
}
