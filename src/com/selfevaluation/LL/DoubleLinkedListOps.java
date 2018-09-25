package com.selfevaluation.LL;

import com.selfevaluation.base.DoubleLinkedList;
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

}
