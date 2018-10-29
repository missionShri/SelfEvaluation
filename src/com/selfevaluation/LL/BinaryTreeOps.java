package com.selfevaluation.LL;

import com.selfevaluation.base.BinaryTree;
import com.selfevaluation.base.BinaryTree.Node;
import com.selfevaluation.base.DoubleLinkedList;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class BinaryTreeOps {

    BinaryTree binaryTree;

    public BinaryTreeOps(BinaryTree binaryTree)
    {
        this.binaryTree = binaryTree;
        if(this.binaryTree!=null)
        {
            this.binaryTree.setRoot(null);
        }
    }

    //Need to pass in root , since that is the basis on which we run recursion
    //O(n) for insertion
    public void insertInBST(Node root , int data/*, int depth*/)
    {
        if(data<0)
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

        //if tree exists
        if(root!=null)
        {
            //find next node
            Node next = (data<=root.getData())?root.getLeft():root.getRight();

            //if null
            if(next==null)
            {
                Node newNode = new Node(data);
                if(data<=root.getData())
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
        doubleLinkedListOps.insertAtEnd(root.getData());
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