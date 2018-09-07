package selfevaluation.LL;

import com.selfevaluation.LL.LLOps;
import com.selfevaluation.base.LinkedList;
import com.selfevaluation.base.LinkedList.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LLOpsTest {

    LinkedList linkedList;
    LLOps llOps;
    Node node0;

    // TODO: ***** https://medium.freecodecamp.org/coding-interviews-for-dummies-5e048933b82b

    // Null input
    // null/empty list
    // single element list
    // duplicate values list
    // happy case :
       /* - first
        - middle
        - last*/

    @Before
    public void setup()
    {
        linkedList = new LinkedList();
        llOps = new LLOps(linkedList);
        node0 = new Node(0);
    }

    @Test
    public void whenInsertAtFrontThenAddNode()
    {
        llOps.insertAtFront(node0);
        Assert.assertEquals(node0,llOps.getLinkedList().getHead());
        Assert.assertEquals(1,llOps.getLinkedList().getSize());
    }

    @Test()
    public void whenInsertAtFrontOnEmptyListThenThrowException()
    {
        llOps.setLinkedList(null);
        llOps.insertAtFront(node0);
        Assert.assertEquals(1,llOps.getLinkedList().getSize());
    }

    //################
    @Test()
    public void whenInsertNodeToNullListThenThrowException()
    {
        llOps.setLinkedList(null);
        llOps.insertAfterGivenNode(new Node(3), 8);
    }

    @Test()
    public void whenInsertNullNodeThenThrowException()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));

        LLOps.printList(llOps.getLinkedList());
        System.out.println();

        try {
            llOps.insertAfterGivenNode(null, 8);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), RuntimeException.class);
            Assert.assertEquals(e.getMessage(),"Input is empty");
        }
    }

    @Test()
    public void whenInsertAfterNotPresentNodeThenThrowException()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));

        LLOps.printList(llOps.getLinkedList());
        System.out.println();

        try {
            llOps.insertAfterGivenNode(new Node(3), 8);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(),RuntimeException.class);
            Assert.assertEquals(e.getMessage(),"Reached end of the list but element not found");
        }
    }

    @Test
    public void whenInsertAfterGivenNodeThenAddNode()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));

        LLOps.printList(llOps.getLinkedList());
        System.out.println();

        llOps.insertAfterGivenNode(new Node(3), 1);
        Assert.assertEquals(4,llOps.getLinkedList().getSize());
    }

    //1-node value edge case...was failing.
    //Now will handle single element cases separately, BE it ANY DS
    @Test
    public void whenInsertAfterFirstNodeInSingleElementThenAddNode()
    {
        llOps.insertAtFront(node0);

        LLOps.printList(llOps.getLinkedList());
        System.out.println();

        llOps.insertAfterGivenNode(new Node(3), 0);
        Assert.assertEquals(2,llOps.getLinkedList().getSize());
    }

    //################
    //null list
    //Adding to it as a feature to insert
    @Test
    public void whenInsertAtEndOfNullListThenThrowException()
    {
        llOps.setLinkedList(null);
        llOps.insertAtEnd(new Node(3));
        Assert.assertEquals(1,llOps.getLinkedList().getSize());

    }
    //null addition
    @Test
    public void whenInsertNullElementThenThrowException()
    {
        try {
            llOps.insertAtEnd(null);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), RuntimeException.class);
            Assert.assertEquals(e.getMessage(),"Input is empty");
        }
    }

    //adding to a single element list
    @Test
    public void whenInsertAtEndOfSingleElementlList()
    {
        llOps.insertAtFront(node0);

        LLOps.printList(llOps.getLinkedList());
        System.out.println();

        llOps.insertAtEnd(new Node(3));
        Assert.assertEquals(2,llOps.getLinkedList().getSize());
    }

    //happy case
    @Test
    public void whenInsertAtEndOflListThenAddNode()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));

        LLOps.printList(llOps.getLinkedList());
        System.out.println();

        llOps.insertAtEnd(new Node(3));
        Assert.assertEquals(4,llOps.getLinkedList().getSize());
    }

    //################################################
    // null linked-list
    @Test()
    public void whenDeleteFromNullListThenThrowException()
    {
        llOps.setLinkedList(null);
        try {
            llOps.delete(3);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), RuntimeException.class);
            Assert.assertEquals(e.getMessage(),"Input is empty");
        }
    }

    // null input
    @Test
    public void whenDeleteNullInputThenThrowException()
    {
        llOps.insertAtFront(node0);
        try {
            llOps.delete(-1);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), RuntimeException.class);
            Assert.assertEquals(e.getMessage(),"Input is empty");
        }
    }
    // item not in the list
    @Test
    public void whenDeleteInputNotInListThenThrowException()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));

        try {
            llOps.delete(3);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), RuntimeException.class);
            Assert.assertEquals(e.getMessage(),"Reached end of the list but element not found");
        }
    }
    // single-element list
    @Test
    public void whenDeleteSingleElementThenDelete()
    {
        llOps.insertAtFront(node0);
        LLOps.printList(llOps.getLinkedList());
        System.out.println();

        llOps.delete(0);
        Assert.assertEquals(0, llOps.getLinkedList().getSize());
    }

    //last element
    @Test
    public void whenDeleteLastElementInListThenDelete()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));

        LLOps.printList(llOps.getLinkedList());
        System.out.println();

        llOps.delete(0);
        Assert.assertEquals(2, llOps.getLinkedList().getSize());
    }

    // happy case
    @Test
    public void whenDeleteElementInListThenDelete()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));
        llOps.insertAtFront(new Node(3));

        LLOps.printList(llOps.getLinkedList());
        System.out.println();

        llOps.delete(2);
        Assert.assertEquals(3, llOps.getLinkedList().getSize());
    }

    //################################################

    //bad input : null list,
    @Test
    public void whenDeleteElementInNullListThenThrowException()
    {
        llOps.setLinkedList(null);
        try {
            llOps.deleteAtPosition(1);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), RuntimeException.class);
            Assert.assertEquals(e.getMessage(),"Input is invalid");
        }
    }

    //negative index
    @Test
    public void whenDeleteElementWithBadPositionThenThrowException()
    {
        llOps.insertAtFront(node0);
        try {
            llOps.deleteAtPosition(-1);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), RuntimeException.class);
            Assert.assertEquals(e.getMessage(),"Input is invalid");
        }
    }

    // index bigger than list
    @Test
    public void whenDeleteElementWithPositionGreaterThanListSizeThenThrowException()
    {
        llOps.insertAtFront(node0);
        try {
            llOps.deleteAtPosition(2);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), RuntimeException.class);
            Assert.assertEquals(e.getMessage(),"Reached end of the list but element not found");
        }
    }

    // happy case
    @Test
    public void whenDeleteElementAtFirstPositionInSingleElementListThenDelete()
    {
        llOps.insertAtFront(node0);

        LLOps.printList(llOps.getLinkedList());
        System.out.println();

        llOps.deleteAtPosition(0);
        Assert.assertEquals(0, llOps.getLinkedList().getSize());
    }

    @Test
    public void whenDeleteElementAtLastPositionInListThenDelete()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));
        llOps.insertAtFront(new Node(3));

        LLOps.printList(llOps.getLinkedList());
        System.out.println();

        llOps.deleteAtPosition(3);
        Assert.assertEquals(3, llOps.getLinkedList().getSize());
    }

    @Test
    public void whenDeleteElementAtGivenPositionInListThenDelete()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));
        llOps.insertAtFront(new Node(3));

        LLOps.printList(llOps.getLinkedList());
        System.out.println();

        llOps.deleteAtPosition(2);
        Assert.assertEquals(3, llOps.getLinkedList().getSize());
    }

    //################################################
    //null input

    @Test
    public void whenListSizeInNullListThenThrowException()
    {
        llOps.setLinkedList(null);
        try {
            llOps.findListSize();
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), RuntimeException.class);
            Assert.assertEquals(e.getMessage(),"Input is invalid");
        }
    }

    //single element
    @Test
    public void whenListSizeInSingleElementListThenFindSize()
    {
        llOps.insertAtFront(node0);
        Assert.assertEquals(1,llOps.findListSize());
    }

    //average case
    @Test
    public void whenListSizeInListThenFindSize()
    {
        llOps.insertAtFront(node0);
        llOps.insertAtFront(new Node(1));
        llOps.insertAtFront(new Node(2));
        llOps.insertAtFront(new Node(3));

        Assert.assertEquals(4,llOps.findListSize());
    }
}
