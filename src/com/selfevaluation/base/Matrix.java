package com.selfevaluation.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Matrix {

    private int length;
    private int breadth;
    private int[][] matrixArray;

    public Matrix(){
        //0 would have been a valid index
        this.length = -1;
        this.breadth = -1;
        this.matrixArray = null;
    }

    public Matrix(int length, int breadth){
        if(length<=0 && breadth<=0){
            throw new IllegalArgumentException("Invalid values for matrix size");
        }
        //0 would have been a valid index...-1 is appropriate
        //that way care needs to be taken that while entering...indexes start from 0 and not 1
        this.length = length-1;
        this.breadth = breadth-1;
        this.matrixArray = new int[length][breadth];
    }

}
