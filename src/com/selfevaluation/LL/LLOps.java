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
        this.linkedList.setHead(null);
    }

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
        linkedList.setHead(nodeToBeAdded);
        incrementListSize();
    }

    //Time : O(n) since involves traversing the list
    public void insertAfterGivenNode(Node nodeToBeAdded, int value) {

        if(nodeToBeAdded == null)
        {
            throw new RuntimeException("Input is empty");
        }

        if (linkedList == null)
        {
            linkedList =  new LinkedList();
            linkedList.setHead(nodeToBeAdded);
            incrementListSize();
            return;
        }

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
            //dont want to make use of size here, since its not standard
            while(current.getNext()!= null && current.getData() != value)
            {
                current = current.getNext();
            }

            if(current.getData()==value)
            {
                //This looks like a standard thing for single linked list
                Node tmp = current.getNext();
                current.setNext(nodeToBeAdded);
                nodeToBeAdded.setNext(tmp);
                incrementListSize();
            }
            else if(current.getNext()==null)
            {
                throw new RuntimeException("Reached end of the list but element not found");
            }
        //}

        printList(linkedList);
        System.out.println("\n########");
    }

    public void insertAtEnd(Node nodeToBeAdded) {
        if(nodeToBeAdded == null)
        {
            throw new RuntimeException("Input is empty");
        }

        if(linkedList == null)
        {
            linkedList =  new LinkedList();
            linkedList.setHead(nodeToBeAdded);
            incrementListSize();
            return;
        }

        Node current = linkedList.getHead();

        //handle 1 element case separately. NOT ANYMORE due to above guidance
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
            while (current.getNext()!=null)
            {
                current = current.getNext();
            }
            //This looks like a standard thing for single linked list
            Node tmp = current.getNext();
            current.setNext(nodeToBeAdded);
            nodeToBeAdded.setNext(tmp);
            incrementListSize();
        //}

        printList(linkedList);
        System.out.println("\n########");
    }

    public void delete(int value) {
        if (linkedList == null || value < 0) {
            throw new RuntimeException("Input is empty");
        }

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
        //                throw new RuntimeException("Reached end of the list but element not found");
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
        //first node, single node
        if(current!=null && current.getData() == value)
        {
            linkedList.setHead(current.getNext());
            decrementListSize();
            return;
        }

        while (current!=null && current.getData()!=value)
        {
            prev = current;
            current = current.getNext();
        }

        if(current==null)
        {
            throw new RuntimeException("Reached end of the list but element not found");
        }

        //Implicit that the value is found
        //Last node, middle node all treated here
        prev.setNext(current.getNext());
        decrementListSize();

        printList(linkedList);
        System.out.println("\n########");
    }

    //TODO: Got simpler due to logic as prescribed at https://www.geeksforgeeks.org/linked-list-set-3-deleting-node/
    public void deleteAtPosition(int position) {
        if (linkedList == null || position < 0) {
            throw new RuntimeException("Input is invalid");
        }

        Node current = linkedList.getHead();
        Node prev = null;

        int i = 0;
        if(current!=null && i==position)
        {
            linkedList.setHead(current.getNext());
            decrementListSize();
            return;
        }

        while(current!=null && i<position)
        {
            prev = current;
            current = current.getNext();
            i++;
        }

        if(current==null)
        {
            throw new RuntimeException("Reached end of the list but element not found");
        }

        //de-link
        //implicit
        if(i==position)
        {
            prev.setNext(current.getNext());
            decrementListSize();
        }
        printList(linkedList);
        System.out.println("\n########");
    }


    public int findListSize() {
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
        return i;

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

    public static void printList(LinkedList linkedList)
    {
        if(linkedList != null)
        {
            Node n = linkedList.getHead();
            System.out.println();
            while (n != null)
            {
                System.out.print(n.getData());
                if(n.getNext()!=null)
                {
                    System.out.print("-->");
                }
                n = n.getNext();
            }
        }
    }
}
