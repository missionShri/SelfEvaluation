package com.selfevaluation.LL;

import com.selfevaluation.base.DoubleLinkedList;
import com.selfevaluation.base.DoubleLinkedList.Node;
import com.selfevaluation.base.SingleCircularLinkedList;
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

        //zero-element (null or empty)
        if(doubleLinkedList == null || doubleLinkedList.getHead()==null)
        {
            if(doubleLinkedList==null)
            {
                doubleLinkedList = new DoubleLinkedList();
            }
            doubleLinkedList.setHead(newNode);
            //no need to modify next & prev since they are already marked point to null in the DS definition
            incrementListSize();
            return;
        }

        //no-looping needed since we have access for the head pointer

        //first perform manipulation on the free-node, to let it take shape of the list pointer
        newNode.setNext(doubleLinkedList.getHead());
        newNode.setPrevious(doubleLinkedList.getHead().getPrevious());

        //then transform next/prev of the current list node to start associating to the new node
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

            //zero-element (null or empty)
        if(doubleLinkedList == null || doubleLinkedList.getHead()==null)
        {
            if(doubleLinkedList==null)
            {
                doubleLinkedList = new DoubleLinkedList();
            }
            Node newNode = new Node(nodeValueToBeAdded);
            doubleLinkedList.setHead(newNode);
            incrementListSize();
            return;
        }

        //first = head
        Node current = doubleLinkedList.getHead();
        Node prev = null;

        //(current != last->next)
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

        //zero-element (null or empty)
        if(doubleLinkedList == null || doubleLinkedList.getHead()==null)
        {
            if(doubleLinkedList==null)
            {
                doubleLinkedList = new DoubleLinkedList();
            }
            Node newNode = new Node(nodeValueToBeAdded);
            newNode.setPrevious(null);
            newNode.setNext(null);
            doubleLinkedList.setHead(newNode);
            incrementListSize();
            return;
        }

        //First node
        Node current = doubleLinkedList.getHead();
        Node prev = null;

        //(current != last->next)
        while (current != null && nodeValueToBeAddedBefore!=current.getData())
        {
            prev = current;
            current = current.getNext();
        }

        //First-condition-check
        if(current == null)
        {
            throw new RuntimeException("Reached the end of list");
        }

        //Second-condition-check
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

        //DS pointer manipulation
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

        //zero-element (null or empty)
        if(doubleLinkedList == null || doubleLinkedList.getHead()==null)
        {
            if(doubleLinkedList==null)
            {
                doubleLinkedList = new DoubleLinkedList();
            }
            newNode.setNext(null);
            newNode.setPrevious(null);
            doubleLinkedList.setHead(newNode);
            incrementListSize();
            return;
        }

        //current = first node
        Node current = doubleLinkedList.getHead();
        Node prev = null;

        //(current != last->next)
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

        //DS pointer manipulation
        if(doubleLinkedList.getHead() == current)
        {
            doubleLinkedList.setHead(newNode);
        }
        return;
    }


    public void deleteFirstNode() {
        //null or empty
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

        //no-looping needed since we have access for the head pointer
        //current = 1st element
        doubleLinkedList.setHead(doubleLinkedList.getHead().getNext());
        doubleLinkedList.getHead().setPrevious(null);
        decrementListSize();
        return;
    }

    public void deleteLastNode() {
        //null or empty
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

        //(current != last->next)
        while (current != null)
        {
            prev = current;
            current = current.getNext();
        }

        //First-condition-check
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
        //null or empty
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

        //(current != last->next)
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
        //null or empty
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

        //(current != last->next)
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
        //null or empty
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

        //(current != last->next)
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
        //null or empty
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

        //(current != last->next)
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
        //null or empty
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

        //(current != last->next)
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

    //for recursive calls , what is a good parameter signature & return type =>
    // *** looks like first-last & return the head of result list ****

    // for recursion, do we actually split the lists ? =>
    // Atleast in this case, ***YES***

    //how do we pass in references so that list in JAVA or do we only rely on return types :
        //https://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value?page=1&tab=votes#tab-top

    public Node mergeSort(Node head, Node last)
    {
        //null or 'single-node already' sorted case
        if(head==null || last==null || head==last)
        {
            return head;
        }

        //divide in to two halves
        Node half = findHalf(head, last);

        /*if(head!=null && last!=null && half!=null)
        {
            System.out.println("\nfor head" + head.getData() + " & last "+ last.getData() + " half is: "+half.getData());
        }*/

        Node halfNext = half.getNext();
        //*** break the list in to two halves ***
        half.setNext(null);

        //Divide & conquer by recursion
        Node first = mergeSort(head, half);
        Node second = mergeSort(halfNext,last/*,listDetails.get("last")*/);

        //merge sorted arrays back
        Node mergedListHead =   mergeSortedLists(first, second);

        return mergedListHead;
    }

    //private Map<String, Node> findHalf(Node head, Node last) {
    private Node findHalf(Node head, Node last) {

        //since taken care of in the step merge itself
        if(head==null || last==null /*|| head==last*/)
        {
            return null;
        }

        //current = first element
        Node current = head;
        Node half = head;
        Node prev = null;
        int  times = 0;

        while (current!=null && current!=last)
        {
            //make half node reference, half as fast as current
            if(times%2==0)
            {
                half = half.getNext();
            }
            prev = current;
            current = current.getNext();
            times++;
        }
        //adjust half for odd-length list ...for even number it is ok
        if(times%2==1 && half!=null && half.getPrevious()!=null)
        {
            half = half.getPrevious();
        }

        return half;
    }


    //
    public Node mergeSortedLists(Node first, Node second) {
        if(first == null && second==null)
        {
            return null;
        }
        if(first==null)
        {
            return second;
        }
        if(second==null)
        {
            return first;
        }

        //lets not disturb the head of the returning list. so better chose the smaller of the heads as the final list
        // that means insertAfter is preferable ...but only insertAfter when the foreign node is > prev and more than current
        // on the other hand insertBefore would be when current is > foreign node ...which implicitly, prev < foreign node

        //lets go with ***insertBefore*** , but handling single element cases separately
        //single-element case
        if(first.getNext()==null && second.getNext()==null)
        {
            if(first.getData()>=second.getData())
            {
                return insertBeforeForSingleElement(second,first);
            }
            else
            {
                return insertBeforeForSingleElement(first, second);
            }
        }

        Node prevFirst,prevSecond;
        //prev = prevToCurrent
        prevFirst = prevSecond = null;

        //Rare case where currentFirst and currentSecond are not determined at the start
        Node currentFirst, currentSecond;
        currentFirst = currentSecond =null;

        //first is the one to which insertBefore is going to happen
        //Choosing first solely on values of first nodes of either list. The list with greater value, is the one to be insertedBefore , going forward
        if(first.getData()<=second.getData())
        {
            //current = 1st  element (head)
            currentFirst = second;
            currentSecond = first;
        }
        else
        {
            //current = 1st  element (head)
            currentFirst = first;
            currentSecond = second;
        }

        Node head = currentFirst;
        Node secondHead = currentSecond;

        //the different thing about this call is that the node addition/deletion is happening **while looping** & not after the looping is complete as in other cases

        //(current != last->next)
        while (currentFirst!=null && currentSecond!=null)
        {
            //if candidate for addition, do insertBefore
            if(currentFirst.getData()>=currentSecond.getData())
            {
                //adjust lose node
                currentSecond.setPrevious(prevFirst);
                Node next = currentSecond.getNext();
                currentSecond.setNext(currentFirst);

                //then transform next/prev of the current list node to start associating to the new node & chopped node
                if(prevFirst!=null)
                {
                    prevFirst.setNext(currentSecond);
                }

                currentFirst.setPrevious(currentSecond);

                //prev second will always be null...since we are chopping off currentSecond to the first list
                if(prevSecond!=null)
                {
                    prevSecond.setNext(next);
                }
                if(next!=null)
                {
                    next.setPrevious(prevSecond);
                }

                //advance pointers & also the list pointers
                if(prevFirst==null)
                {
                    prevFirst = currentSecond;
                    head = prevFirst;
                }
                else if(prevFirst!=null)
                {
                    prevFirst = prevFirst.getNext();
                }
                //no need to currentFirst since prevFirst just changed from currentSecond & we want to compare it with new currentSecond
                //currentFirst = currentFirst;

                if(prevSecond==null)
                {
                    currentSecond = next;
                    secondHead = currentSecond;
                }
                else if (prevSecond!=null)
                {
                    //no need to move prevSecond, since currentSecond has just moved
                    //prevSecond = prevSecond;
                    currentSecond = prevSecond.getNext();
                }
            }
            else
            {
                //advance source pointers, not other list. Since the aim is to delete the other list
                prevFirst = currentFirst;
                //prevSecond = currentSecond;
                currentFirst = currentFirst.getNext();
                //currentSecond = currentSecond.getNext();
            }
        }

        //see if one list is exhausted

        //First-condition-check
        if(currentFirst==null)
        {
            Node tmp =currentSecond;
            while (tmp!=null)
            {
                prevFirst.setNext(tmp);
                tmp.setPrevious(prevFirst);
                prevFirst = prevFirst.getNext();
                tmp = tmp.getNext();
            }
            //immediately return rather than holding on to the last
            return head;
        }

        //Second-condition-check
        if(currentSecond==null)
        {
            //take prevSecond and finds it right place ...not needed , since prevSecond shall always be null.
            //immidiately return rather than holding on to the last
            return head;
        }
        return  head;
    }

    private Node insertBeforeForSingleElement(Node nodeToBeInserted, Node insertBefore) {
        insertBefore.setPrevious(nodeToBeInserted);
        nodeToBeInserted.setNext(insertBefore);
        return nodeToBeInserted;
    }

    public void incrementListSize() {
        if(doubleLinkedList!=null)
        {
            doubleLinkedList.setSize(doubleLinkedList.getSize()+1);
        }
    }

    public void decrementListSize() {
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
        System.out.println("Priting the DLL list");

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

// ************* ************* ************* *************
//Options ...how about not changing the pointers/nodes , but just the elemtent
//           keeping track of head, pivot, last for limiting conditions
//           how to pass list by reference, so that any mutations are visible in calling class
//    public void quickSort(Node head, Node pivot) {
//
//        if(head==null || pivot==null)
//        {
//            //how to distinguish between invalid input and recursion limiting condition
//            return;
//        }
//
//        //single element case or pivot is the first element
//        if(head.getNext()==null /*|| head.getNext()==pivot*/)
//        {
//            return;
//        }
//
//        //current = first
//        /*Node current = head;
//        Node prev = null;
//        Node pivot = null;
//
//        //current != last->next
//        while (current!=null)
//        {
//            prev = current;
//            current = current.getNext();
//        }
//
//        //first-if-condition-check
//        if(current==null)
//        {
//            pivot = prev;
//        }*/
//
//        System.out.print("\n\nInputs to partition are: "+head.getData()+" "+pivot.getData());
//        //partition considering the last element as a pivot
//        Node firstListHead = partition(head, pivot);
//
//        Node first = firstListHead;
//        System.out.print("\nAfter partition, first part\n");
//        while (first!=null && first!=pivot)
//        {
//            System.out.print(first.getData()+"-->");
//            first = first.getNext();
//        }
//
//        System.out.print("\n pivot"+ pivot.getData());
//
//        Node second = pivot.getNext();
//        System.out.print("\nAfter partition, second part\n");
//        while (second!=null)
//        {
//            System.out.print(second.getData()+"-->");
//            second = second.getNext();
//        }
//
//        //System.out.print("\n firstListHead" +firstListHead.getData() + "\t" + "pivot" + pivot.getData()+ "\n");
//        //System.out.print("pivot.next" +pivot.getNext().getData() + "\t" + "last" + last.getData()+ "\n");
//
//        if(pivot!=null && pivot.getPrevious()!=null && firstListHead!=null /*&& firstListHead.getData()<pivot.getPrevious().getData()*/)
//        {
//            System.out.print("\n\nQS first part with "+firstListHead.getData() +" "+pivot.getPrevious().getData());
//            quickSort(firstListHead, pivot.getPrevious());
//        }
//
//        Node last  = getLast(doubleLinkedList.getHead());
//        if(last!=null)
//        {
//            System.out.println("\nlast"+last.getData());
//        }
//
//        if(pivot!=null && pivot.getNext()!=null && last!=null && pivot.getNext().getData()<last.getData())
//        {
//            System.out.print("\n\nQS second part "+pivot.getNext().getData() +" "+last.getData());
//            quickSort(pivot.getNext(),last);
//        }
//
//        //any limiting condition to do this , since it was intended for one time use
//        System.out.println("\nhead setting to "+firstListHead.getData());
//        doubleLinkedList.setHead(firstListHead);
//    }
//
//
//    private Node getLast(Node head) {
//        if(head==null || head.getNext()==null)
//        {
//            return null;
//        }
//        Node current = head;
//        Node prev = null;
//        while(current!=null)
//        {
//            prev = current;
//            current = current.getNext();
//        }
//
//        return prev;
//    }
//
//    private Node partition(Node head, Node pivot) {
//        if(pivot==null || head==null)
//        {
//            return null;
//        }
//
//        //single element case or pivot is the first element
//        //not sure if this is helpful since list are now splitted in many sbu-lists
//        /*if(doubleLinkedList.getHead().getNext()==null || pivot == doubleLinkedList.getHead())
//        {
//            return null;
//        }*/
//
//        Node current = head;
//        Node prev = null;
//        Node next = null;
//        Node currentLast = pivot;
//
//        //current != currentLast->next
//        while (current!=null && current!=pivot)
//        {
//            //important to note if current moved around. since if it did, (next!=null)
//            next = null;
//            if(current.getData()>pivot.getData())
//            {
//                //dis-engage current
//                next = current.getNext();
//                //no need to set it null like next, since its modified per loop-iteration outside of this if
//                if(prev!=null)
//                {
//                    prev.setNext(next);
//                }
//
//                if(next!=null)
//                {
//                    next.setPrevious(prev);
//                }
//                if(current==head)
//                {
//                    head = (prev!=null)?prev:next;
//                }
//
//                //make current as currentLast pointer. currentLast= pivot is only to begin with
//                currentLast.setNext(current);
//                current.setPrevious(currentLast);
//                current.setNext(null);
//                currentLast = current;
//
//                //not sure if this is helpful since list are now splitted in many sbu-lists
//                /*if(current==head)
//                {
//                    //1-element case
//                    if(next!=null)
//                    {
//                        //head = next;
//                        doubleLinkedList.setHead(next);
//                    }
//                }*/
//            }
//
//            if(next!=null)
//            {
//                prev = next.getPrevious();
//                current = next;
//            }
//            else //not sure what would be the case with a single element
//            {
//                prev = current;
//                current = current.getNext();
//            }
//        }
//
//        /*Node firstList = head;
//        System.out.print("\nfirstList constrained");
//        while (firstList!=null && firstList!=pivot)
//        {
//            System.out.print(firstList.getData() + "\t");
//            firstList = firstList.getNext();
//        }
//
//        //so we are not really dont have 2 lists until we split the list
//        firstList = head;
//        System.out.print("\nfirstList");
//        while (firstList!=null)
//        {
//            System.out.print(firstList.getData() + "\t");
//            firstList = firstList.getNext();
//        }
//
//        System.out.print("\npivot "+pivot.getData());
//        System.out.print("\nsecondList");
//        Node secondList = pivot.getNext();
//        while (secondList!=null)
//        {
//            System.out.print(secondList.getData() + "\t");
//            secondList = secondList.getNext();
//        }*/
//
//        return head;
//    }

// ************* ************* ************* *************