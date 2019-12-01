package com.selfevaluation.ops;

import com.selfevaluation.base.BinaryTree;
import com.selfevaluation.base.BinaryTree.Node;
import com.selfevaluation.base.Queue;
import com.selfevaluation.ops.queue.QueueOps;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BinaryTreeOps {

    BinaryTree binaryTree;

    public BinaryTreeOps(/*BinaryTree binaryTree*/)
    {
        this.binaryTree = new BinaryTree();
    }

    public <T> boolean insertInLevelOrderBinaryTree(QueueOps queueOps, BinaryTree tree, T data)
    {
        convertBinaryTreeToQueue(queueOps, tree.getRoot());

        return false;

    }

    public void convertBinaryTreeToQueue(QueueOps queueOps, BinaryTree.Node root) {

        //TODO: the problem is now, no good reason to believe that this can only be int...can be any subtype of Number
        //zero-element case.
        if (root == null || isInputLessThanOrEqualValueToCompareAgainst(root.getData(),0)) {
            return;
        }

        //first-if-condition-check
        if(root!=null)
        {
            if(queueOps == null)
            {
                queueOps = new QueueOps(new Queue());
            }

            //only add the root, the first time. Later times its some nodes left or right child. So we do not want to add it again
            //BFS and Pre-order DFS are similar since you worry about the node first and then the children
            if(queueOps.getQueue().getSize()==0)
            {
                queueOps.enqueue(new Queue.Node(root.getData()));
            }

            //if we postpone it as part of recursive call, then it would be DFS
            // we enque both children before recurssion to avoid DFS
            //(root.getLeft()==null)?queueOps.enqueue(new Queue.Node(-1));
            if(root.getLeft()!= null)
            {
                queueOps.enqueue(new Queue.Node(root.getLeft().getData()));
            }
            else {
                queueOps.enqueue(new Queue.Node(-1));
            }
            if(root.getRight()!= null)
            {
                queueOps.enqueue(new Queue.Node(root.getRight().getData()));
            }
            else
            {
                queueOps.enqueue(new Queue.Node(-1));
            }

            //System.out.print(queueOps.getQueue().getRear().getData()+"\t");

            //things only get appended, and not de-qued adding to confusion
            convertBinaryTreeToQueue(queueOps,root.getLeft());
            convertBinaryTreeToQueue(queueOps,root.getRight());
        }
    }

    //Need to pass in root , since that is the basis on which we run recursion
    //O(n) for insertion
    public <T extends Number> void insertInBST(Node root , T data/*, int depth*/)
    {
        if(isInputLessThanOrEqualValueToCompareAgainst(data, 0) /*|| root==null*/)
        {
            throw new IllegalArgumentException("Invalid input");
        }

        //zero-element case . root parameter does not matter
        // null or empty
        if(binaryTree==null || binaryTree.getRoot()==null)
        {
            binaryTree = new BinaryTree();
            binaryTree.setRoot(new Node(data));
            incrementSize();
            //incrementDepth();
            return;
        }

        //if node exists
        if(root!=null)
        {
            //find next node
            Node next = (isInputLessThanOrEqualValueToCompareAgainst(data, root.getData()))?root.getLeft():root.getRight();

            //if null
            if(next==null)
            {
                Node newNode = new Node(data);
                if(isInputLessThanOrEqualValueToCompareAgainst(data, root.getData()))

                {
                    root.setLeft(newNode);
                }
                else
                {
                    root.setRight(newNode);
                }
                incrementSize();
                //incrementDepth();
            }
            //if not null
            else if(next!=null)
            {
                insertInBST(next,data/*,++depth*/);
            }
            return;

            /*if(data<=node.getData())
            {
                if(node.getLeft()==null)
                {
                    node.setLeft(new Node(data));
                    incrementSize();
                    incrementDepth();
                    return;
                }
                else
                {
                    insertInBST(node.getLeft(),data, ++depth);
                }
            }
            else
            {
                if(node.getRight()==null)
                {
                    node.setRight(new Node(data));
                    incrementSize();
                    incrementDepth();
                    return;
                }
                else
                {
                    insertInBST(node.getRight(),data,++depth);
                }
            }*/
        }
    }

    //hard to tdd-ed recursive cases
    public void printInorderTraversal(Node root)
    {
        if(root==null)
        {
            return;
        }
        printInorderTraversal(root.getLeft());
        System.out.print(root.getData() + "\t");

        printInorderTraversal(root.getRight());
    }

    public void createListFromTreeWithExtraSpace(Node root, DoubleLinkedListOps  doubleLinkedListOps)
    {
        if(root==null)
        {
            return;
        }
        createListFromTreeWithExtraSpace(root.getLeft(), doubleLinkedListOps);
        //TODO : fix DoubleLL
        doubleLinkedListOps.insertAtEnd(root.getData().intValue());
        createListFromTreeWithExtraSpace(root.getRight(), doubleLinkedListOps);
    }

    private void incrementSize() {
        if(binaryTree!=null)
        {
            binaryTree.setSize(binaryTree.getSize()+1);
        }
    }

    //Pretty-printing tree is tough
    public void printTree(Node root)
    {
        if(root==null)
        {
            return;
        }

        System.out.println(root.getData() + "  " + printChildren(root));

        printTree(root.getLeft());
        printTree(root.getRight());
    }

    private String printChildren(Node root) {
        StringBuilder builder = new StringBuilder();
        if(root.getLeft()!=null)
        {
            builder.append("Left" + root.getLeft().getData()+"\t");

        }
        if(root.getRight()!=null)
        {
            builder.append("Right" + root.getRight().getData());

        }
        return builder.toString();
    }

    private <T extends Number> boolean isInputLessThanOrEqualValueToCompareAgainst(T data, T valueToCompareAgainst) {
        if (data instanceof Integer && valueToCompareAgainst instanceof Integer) {
            return (data.intValue() <= valueToCompareAgainst.intValue());
        }
        else if(data instanceof Float && valueToCompareAgainst instanceof Float){
            return  (data.floatValue()<= valueToCompareAgainst.floatValue());
        }
        return false;
    }
}


//    public void convertBTreeToCDLL(Node root, Node predecessor, DoubleLinkedList doubleLinkedList, Map<Node,Boolean>visitedStatus, Map<Node, Node> nodeToPredecessorMap, Boolean isLeft)
//    {
//        if(root==null)
//        {
//            return ;
//        }
//
//        //post-order makes sense for list node manipulation
//        convertBTreeToCDLL(root.getLeft(), root, doubleLinkedList,visitedStatus, nodeToPredecessorMap,true);
//        convertBTreeToCDLL(root.getRight(), root, doubleLinkedList, visitedStatus, nodeToPredecessorMap, false);
//
//        /*DoubleLinkedList.Node node =  new DoubleLinkedList.Node(root.getData());
//        if(!visitedStatus.containsKey(node) || visitedStatus.get(node)==false)
//        {
//            visitedStatus.put(root,true);
//        }
//
//
//        if(!nodeToPredecessorMap.containsKey(node) && isLeft)
//        {
//            //left child and parent
//            if(predecessor!=null && !visitedStatus.get(predecessor))
//            {
//                nodeToPredecessorMap.put(root,predecessor);
//
//                DoubleLinkedList.Node next = new DoubleLinkedList.Node(predecessor.getData());
//                node.setNext(next);
//                next.setPrevious(node);
//
//
//                visitedStatus.put(predecessor,true);
//            }
//        }
//
//
//        if(predecessor!=null)
//        {
//            //System.out.println( node.getData() +" "+ predecessor.getData());
//
//            else if (visitedStatus.containsKey(predecessor))//predecessor already here, find predecessor's predecessor and make that the next
//            {
//                if(nodeToPredecessorMap.containsKey(predecessor))
//                {
//                    visitedStatus.put(nodeToPredecessorMap.get(predecessor),true);
//                    DoubleLinkedList.Node impliedPredecessor = (nodeToPredecessorMap.get(predecessor)!=null)?new DoubleLinkedList.Node(nodeToPredecessorMap.get(predecessor).getData()):new DoubleLinkedList.Node(predecessor.getData());
//                    node.setNext(impliedPredecessor);
//                    if(impliedPredecessor!=null)
//                    {
//                        impliedPredecessor.setPrevious(node);
//                    }
//                }
//            }
//        }
//
//        if(doubleLinkedList.getHead()==null)
//        {
//            doubleLinkedList.setHead(node);
//        }*/
//
//        return;
//    }
//
//    private void incrementDepth() {
//        if(binaryTree!=null)
//        {
//            binaryTree.setDepth(binaryTree.getDepth()+1);
//        }
//    }