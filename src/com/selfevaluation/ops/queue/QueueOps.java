package com.selfevaluation.ops.queue;

import com.selfevaluation.base.Queue;
import com.selfevaluation.base.Queue.Node;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueueOps {

    Queue queue;
    Node node;

    public QueueOps(Queue queue)
    {
        this.queue = queue;
        this.node = new Node(0);
        if(this.queue!=null)
        {
            this.queue.setFront(null);
            this.queue.setRear(null);
        }

    }

    // Parameter : Queue.Node or data ==>  I prefer data so that node creation happens in the function and we do not hit the case of existing next and prev pointers messing up

    //However tomorrow is the data-type changes, then we are looking at the method signature change
    //Also dequeue is returning a node..so better enqueue takes in node
    public void enqueue(Node node)
    {
        if(node == null)
        {
            throw new IllegalArgumentException("Invalid input");
        }

        //zero or null case
        if(queue==null || isEmpty())
        {
            if(queue==null)
            {
                queue = new Queue();
            }
            queue.setRear(node);
            //so that we do not hit the case of existing next and prev pointers messing up
            node.setNext(null);

            //also seed front (Unit-test rocks)
            queue.setFront(node);
            incrementSize();
            return;
        }

        queue.getRear().setNext(node);
        queue.setRear(node);
        //so that we do not hit the case of existing next and prev pointers messing up
        node.setNext(null);
        incrementSize();
        return;
    }

    public Node dequeue()
    {
        Node node = null;
        if(queue==null || isEmpty())
        {
            throw new RuntimeException("Queue is null/empty");
        }

        //Single element case
        //Check which is similar to the isEmpty check, but checking for a singular element
        if(queue.getFront()!=null && queue.getFront()==queue.getRear())
        {
            node = queue.getFront();
            //(Unit-test rocks)...was not setting front to null for single-element case before
            queue.setFront(null);
            queue.setRear(null);

            //don't mark queue as null, rather have it become empty (Unit-test rocks)
            //queue = null;

            decrementSize();
            return node;
        }

        node = queue.getFront();
        queue.setFront(node.getNext());
        decrementSize();
        return node;
    }

    public boolean isEmpty()
    {
        return queue!=null && (queue.getFront()==null && queue.getFront()== queue.getRear());
    }

    private void incrementSize() {
        if(queue==null)
        {
            throw new RuntimeException("Queue is null");
        }
        queue.setSize(queue.getSize()+1);
    }

    private void decrementSize() {
        if(queue==null)
        {
            throw new RuntimeException("Queue is null");
        }
        queue.setSize(queue.getSize()-1);
    }

    public String[] generateBinaryNumber(int n)
    {
        String[] generatedNumbers =null;
        if(n<0)
        {
            throw new IllegalArgumentException("Invalid input");
        }
        if(n>0)
        {
            Node node = new Node(1);
            enqueue(node);
            int data = -1 , originalLength = n;
            generatedNumbers = new String[originalLength];

            while (n>0)
            {
                data = dequeue().getData();
                generatedNumbers[originalLength-n] = data+"";

//                enqueue(new Node(node.getData()+"0"));
//                enqueue(new Node(node.getData()+"1"));
                n--;
            }
        }
        return generatedNumbers;
    }

   }
