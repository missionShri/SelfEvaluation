package com.selfevaluation.LL;

import com.selfevaluation.base.DoubleLinkedList;
import com.selfevaluation.base.DoubleLinkedList.Node;
import lombok.Getter;
import lombok.Setter;

//Feels so wierd designing the new data-structure
@Getter
@Setter
public class DoubleLinkedListOps {

    private DoubleLinkedList doubleLinkedList;

    public DoubleLinkedListOps(DoubleLinkedList doubleLinkedList)
    {
        this.doubleLinkedList = doubleLinkedList;
        if(this.doubleLinkedList!=null)
        {
            this.doubleLinkedList.setHead(null);
        }
    }

    public void insertAtFront(int value) {
        if(value<0)
        {
            throw new IllegalArgumentException("Invalid input");
        }

        Node newNode = new Node(value);

        //zero-element
        if(doubleLinkedList == null || doubleLinkedList.getHead()==null)
        {
            doubleLinkedList = new DoubleLinkedList();
            doubleLinkedList.setHead(newNode);
            //no need to modify next & prev since they are already marked point to null in the DS definition
            incrementListSize();
            return;
        }

        //no-looping needed since we have access for the head pointer
        newNode.setNext(doubleLinkedList.getHead());
        newNode.setPrevious(doubleLinkedList.getHead().getPrevious());
        doubleLinkedList.getHead().setPrevious(newNode);
        doubleLinkedList.setHead(newNode);
        incrementListSize();

        return;
    }

    public void insertAfterGivenNode(int nodeValueToBeAdded, int nodeValueToBeAddedAfter) {
        if(nodeValueToBeAdded<0 || nodeValueToBeAddedAfter <0)
        {
            throw new IllegalArgumentException("Invalid entry");
        }

        //zero element
        if(doubleLinkedList==null || doubleLinkedList.getHead()==null)
        {
            doubleLinkedList = new DoubleLinkedList();
            Node newNode = new Node(nodeValueToBeAdded);
            doubleLinkedList.setHead(newNode);
            incrementListSize();
            return;
        }

        //first = head
        Node current = doubleLinkedList.getHead();
        Node prev = null;


        while(current!=null  && current.getData() != nodeValueToBeAddedAfter)
        {
            prev = current;
            current = current.getNext();
        }

        //First-condition-check
        if(current == null)
        {
            throw new RuntimeException("Reached end of the list.");
        }

        //second-if-condition-check
        if(current.getData() == nodeValueToBeAddedAfter)
        {
            //first perform manipulation on the free-node, to let it take shape of the list pointer
            Node newNode = new Node(nodeValueToBeAdded);
            newNode.setNext(current.getNext());
            newNode.setPrevious(current);
            //then transform next/prev of the current list node to start associating to the new node
            current.setNext(newNode);
            if(newNode.getNext()!=null)
            {
                newNode.getNext().setPrevious(newNode);
            }
            incrementListSize();
        }
        return;
    }

    public void insertBeforeGivenNode(int nodeValueToBeAdded, int nodeValueToBeAddedBefore) {

        if(nodeValueToBeAdded <0 || nodeValueToBeAddedBefore<0)
        {
            throw new IllegalArgumentException("Invalid input");
        }

        //zero element
        if(doubleLinkedList == null || doubleLinkedList.getHead() == null)
        {
            Node newNode = new Node(nodeValueToBeAdded);
            newNode.setPrevious(null);
            newNode.setNext(null);
            doubleLinkedList =  new DoubleLinkedList();
            doubleLinkedList.setHead(newNode);
            incrementListSize();
            return;
        }

        //First node
        Node current = doubleLinkedList.getHead();
        Node prev = null;

        while (current != null && nodeValueToBeAddedBefore!=current.getData())
        {
            prev = current;
            current = current.getNext();
        }

        if(current == null)
        {
            throw new RuntimeException("Reached the end of list");
        }

        Node newNode = null;
        if(current.getData() == nodeValueToBeAddedBefore)
        {
            //first take care of new node
            newNode = new Node(nodeValueToBeAdded);
            newNode.setPrevious(current.getPrevious());
            newNode.setNext(current);

            current.setPrevious(newNode);
            if(newNode.getPrevious()!=null)
            {
                newNode.getPrevious().setNext(newNode);
            }
            incrementListSize();
        }

        if(doubleLinkedList.getHead() == current)
        {
            doubleLinkedList.setHead(newNode);
        }
        return;
    }

    public void insertAtEnd(int nodeValueToBeAdded) {
        if(nodeValueToBeAdded<0)
        {
            throw new IllegalArgumentException("Invalid input");
        }

        Node newNode = new Node(nodeValueToBeAdded);

        //zero-element case
        if(doubleLinkedList==null || doubleLinkedList.getHead()==null)
        {
            doubleLinkedList =  new DoubleLinkedList();
            newNode.setNext(null);
            newNode.setPrevious(null);
            doubleLinkedList.setHead(newNode);
            incrementListSize();
            return;
        }

        //current = first node
        Node current = doubleLinkedList.getHead();
        Node prev = null;

        while (current != null)
        {
            prev  = current;
            current = current.getNext();
        }

        //First-condition-check
        if(current==null)
        {
            if(prev!=null)
            {
                newNode.setPrevious(prev);
                prev.setNext(newNode);
                incrementListSize();
            }
        }

        if(doubleLinkedList.getHead() == current)
        {
            doubleLinkedList.setHead(newNode);
        }
        return;
    }


    public void deleteFirstNode() {
        if(doubleLinkedList == null || doubleLinkedList.getHead()==null)
        {
            throw new RuntimeException("Empty list");
        }

        //single element case
        if(doubleLinkedList.getHead().getNext()==null)
        {
            doubleLinkedList.setHead(null);
            decrementListSize();
            return;
        }

        //current = 1st element
        doubleLinkedList.setHead(doubleLinkedList.getHead().getNext());
        doubleLinkedList.getHead().setPrevious(null);
        decrementListSize();
        return;
    }

    public void deleteLastNode() {
        if (doubleLinkedList == null || doubleLinkedList.getHead() == null) {
            throw new RuntimeException("Empty list");
        }

        //single element
        if (doubleLinkedList.getHead().getNext() == null) {
            doubleLinkedList.setHead(null);
            decrementListSize();
            return;
        }

        //current = first element
        Node current = doubleLinkedList.getHead();
        Node prev = null;

        while (current != null)
        {
            prev = current;
            current = current.getNext();
        }

        if(current==null)
        {
            if(prev!=null && prev.getPrevious()!=null)
            {
                prev.getPrevious().setNext(null);
                decrementListSize();
                return;
            }
        }
    }


    public void deleteNode(int nodeValueToBeDeleted) {
        if(doubleLinkedList == null || doubleLinkedList.getHead()==null || nodeValueToBeDeleted<0)
        {
            throw  new IllegalArgumentException("Invalid input");
        }

        //single
        if(doubleLinkedList.getHead().getNext()==null && doubleLinkedList.getHead().getData()==nodeValueToBeDeleted)
        {
            doubleLinkedList.setHead(null);
            decrementListSize();
            return;
        }

        //current = fist
        Node current = doubleLinkedList.getHead();
        Node prev = null;

        while (current!=null && current.getData()!=nodeValueToBeDeleted)
        {
            prev = current;
            current = current.getNext();
        }

        //first-if-condition-check
        if(current==null)
        {
            throw new RuntimeException("Reached end of list");
        }

        //second-if-condition-check
        if(current.getData()==nodeValueToBeDeleted)
        {
            if(prev!=null)
            {
                prev.setNext(current.getNext());
            }
            if(current.getNext()!=null)
            {
                current.getNext().setPrevious(prev);
            }
            decrementListSize();
        }

        //Modifying the list pointer, since we just deleted the node pointed to by head pointer
        if(current==doubleLinkedList.getHead())
        {
            //???..if head was deleted, how can it have a prev ?
            if(prev!=null)
            {
                doubleLinkedList.setHead(prev);
            }
            else if(current.getNext()!=null)
            {
                doubleLinkedList.setHead(current.getNext());
            }
        }
        return;
    }

    public void deleteNodeAtPositon(int position) {
        if(doubleLinkedList == null || doubleLinkedList.getHead()==null || position<0)
        {
            throw  new IllegalArgumentException("Invalid input");
        }

        //single element case
        if(doubleLinkedList.getHead().getNext()==null && position == 0)
        {
            doubleLinkedList.setHead(null);
            decrementListSize();
            return;
        }

        //current = fist
        Node current = doubleLinkedList.getHead();
        Node prev = null;
        int i =0;

        while (current!=null && i<position)
        {
            prev = current;
            current = current.getNext();
            i++;
        }

        //first-if-condition-check
        if(current==null)
        {
            throw new RuntimeException("Reached end of list");
        }

        //second-if-condition-check
        if(i==position)
        {
            if(prev!=null)
            {
                prev.setNext(current.getNext());
            }
            if(current.getNext()!=null)
            {
                current.getNext().setPrevious(prev);
            }
            decrementListSize();
        }

        //Modifying the list pointer, since we just deleted the node pointed to by head pointer
        if(current==doubleLinkedList.getHead() && position == 0)
        {
            //???..if head was deleted, how can it have a prev ?
            if(prev!=null)
            {
                doubleLinkedList.setHead(prev);
            }
            else if(current.getNext()!=null)
            {
                doubleLinkedList.setHead(current.getNext());
            }
        }
        return;
    }

    //Bit-Wierd-logic than our standard template since have to track prevToPrev
    public void deleteNodeBefore(int nodeValueToDeleteBefore) {
        if (doubleLinkedList == null || doubleLinkedList.getHead() == null || nodeValueToDeleteBefore < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        //single element
        if (doubleLinkedList.getHead().getNext() == null
            && doubleLinkedList.getHead().getData() == nodeValueToDeleteBefore) {
            //if only one element, nothing to delete before
            return;
        }

        //current = first
        Node current = doubleLinkedList.getHead();
        //prev is the target
        Node prev = null;

        while (current != null && current.getData() != nodeValueToDeleteBefore)
        {
            prev = current;
            current = current.getNext();
        }

        //first-if-condition-check
        if(current==null)
        {
            throw new RuntimeException("Reached end of list");
        }

        //second-if-condition-check
        if(current.getData()==nodeValueToDeleteBefore)
        {
            if(prev!=null)
            {
                //non-first prev cases
                if(prev.getPrevious()!=null)
                {
                    prev.getPrevious().setNext(current);
                    current.setPrevious(prev.getPrevious());
                    decrementListSize();
                }
                //prev as first
                else if (prev.getPrevious() == null)
                {
                    current.setPrevious(null);
                    decrementListSize();
                }
            }
        }

        //Modifying the list pointer, since we just deleted the node pointed to by head pointer
        if(prev!=null && prev == doubleLinkedList.getHead())
        {
            doubleLinkedList.setHead(current);
        }
        return;
    }

    //Bit-Wierd-logic than our standard template since have to track nextToNext
    public void deleteNodeAfter(int nodeValueToDeleteAfter) {
        if(doubleLinkedList == null ||  doubleLinkedList.getHead()==null || nodeValueToDeleteAfter<0)
        {
            throw new IllegalArgumentException("Invalid input");
        }

        //single element case
        if(doubleLinkedList.getHead().getNext()==null && doubleLinkedList.getHead().getData()==nodeValueToDeleteAfter)
        {
            //if only one element, nothing to delete after
            return;
        }

        //current as first
        Node current = doubleLinkedList.getHead();
        Node prev = null;

        while (current!=null && current.getData()!=nodeValueToDeleteAfter)
        {
            prev = current;
            current = current.getNext();
        }

        //first-if-condition-check
        if(current==null)
        {
            throw new RuntimeException("Reached end of list");
        }

        //second-if-condition-check
        if(current.getData()==nodeValueToDeleteAfter)
        {
            Node next = current.getNext();
            Node nextToNext;

            //node to delete is next
            if(next!=null)
            {
                nextToNext = next.getNext();
                if(nextToNext!=null)
                {
                    current.setNext(nextToNext);
                    nextToNext.setPrevious(current);
                    decrementListSize();
                }
                else
                {
                    current.setNext(null);
                    decrementListSize();
                }
            }
        }

        //Modifying the list pointer, since we just deleted the node pointed to by head pointer
        if(doubleLinkedList.getHead() == current)
        {
            //no-op ..since deletion after, no action
        }

        return;
    }

    public void reverse() {
        if(doubleLinkedList==null || doubleLinkedList.getHead()==null)
        {
            throw new IllegalArgumentException("Invalid empty");
        }

        //single element case
        if(doubleLinkedList.getHead().getNext()==null)
        {
            return;
        }

        //current = 1st element
        Node current = doubleLinkedList.getHead();
        Node prev = current.getPrevious();
        Node next;

        while (current!=null)
        {
            //Manipulation inside the loop unlike other cases since every node gets some treatment
            next = current.getNext();
            //prev becomes next
            current.setNext(prev);
            //next becomes prev
            current.setPrevious(next);

            prev = current;
            current = next;
        }

        //Modifying the list pointer, since we just deleted the node pointed to by head pointer
        if(prev!=null)
        {
            doubleLinkedList.setHead(prev);
        }

    }

    public void reverseRecursive(Node node) {
        if(node==null)
        {
            throw new IllegalArgumentException("Invalid input");
        }

        //single/breaking case for recursion in element list
        //following our template would have lead us here , but would lead to duplication and we would also missed setNext , setPrev assignments for
//        if(node.getNext()==null)
//        {
//            node.setNext(node.getPrevious());
//            node.setPrevious(node.getNext());
//            doubleLinkedList.setHead(node);
        //               return;
//        }

          // Breaking condition
//        //current = 1st element
//        if(node==null)
//        {
//
//            return;
//        }

                Node next = node.getNext();
                node.setNext(node.getPrevious());
                node.setPrevious(next);
                if(next==null)
                {
                    doubleLinkedList.setHead(node);
                    return;
                }
                reverseRecursive(next);

    }

    private void incrementListSize() {
        if(doubleLinkedList!=null)
        {
            doubleLinkedList.setSize(doubleLinkedList.getSize()+1);
        }
    }

    private void decrementListSize() {
        if(doubleLinkedList!=null)
        {
            doubleLinkedList.setSize(doubleLinkedList.getSize()-1);
        }
    }

    public static void printList(Node head) {
        if(head == null)
        {
            return;
        }

        System.out.println();
        System.out.println("Priting the list");

        Node current = head;
        while (current!=null)
        {
            System.out.print(current.getData());
            current = current.getNext();

            if(current!=null)
            {
                System.out.print(" ==> ");
            }
        }
    }
}
