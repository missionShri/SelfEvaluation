package com.selfevaluation.ops;

import com.selfevaluation.base.Matrix;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatrixOps {

    private Matrix matrix;

    public MatrixOps(){
        this.matrix = new Matrix();
    }

    public MatrixOps(Matrix matrix){
        this.matrix = matrix;
    }

    public MatrixOps(int length, int breadth){
        this.matrix = new Matrix(length, breadth);
    }

    public void setValueInMatrix(int value, int xPosition, int yPosition){
        if( (xPosition<0 || xPosition>(this.matrix.getLength())) || (yPosition<0 || yPosition>(this.matrix.getBreadth()))){
            throw new IllegalArgumentException("Invalid values for value positions");
        }
        this.matrix.getMatrixArray()[xPosition][yPosition] = value;
    }

    public void prettyPrintMatrix()
    {
        for (int i = 0; i <= matrix.getLength(); i++) {
            for (int j = 0; j <= matrix.getBreadth(); j++) {
                System.out.print(matrix.getMatrixArray()[i][j]+"\t");
                if(j==matrix.getBreadth())
                {
                    System.out.println();
                }
            }
        }
    }

    //TODO: Spiral move
    public void rotateMatrix(int timesOfRotation)
    {
        int temp = -1;
        if(timesOfRotation<1) {
            throw new IllegalArgumentException("Invalid input for times parameter");
        }

        for (int i = 0; i < timesOfRotation; i++) {

            //doing it for (matrix.length)/2 will cover
            for (int a = 0; a <= matrix.getLength()/2; a++) {

                //start with simple case --> no spiral rotation ...just the outermost layer
                //you would have logic made of 0 (for start-index) to length and breadth as end-indexes.

                //now thinking of ***spiral move***, after we are done with 1 round --> the start index would move in diagonal-fashion moving from (0,0) to (1,1) to (2,2)
                //and end-index would decrease in linear-fashion corresponding to start-index
                //if start(1,1) --> end(1,breadth-1) on the right & end(length-1,1) on the bottom side

                //start element location is used as anchor for holding the temp element
                //we keep swapping the temp element with the start-element as we push before-elements one-step ahead
                //so the prev value of the location is held in the start-element location
                //we keep doing this for every spiral ... reducing the start and end-bounds (row and column)-wise every time

                temp = -1;
                for (int j = (0+a); j < (matrix.getBreadth()-a); j++) {
                    temp = matrix.getMatrixArray()[a][j+1];
                    matrix.getMatrixArray()[a][j+1] = matrix.getMatrixArray()[a][a];
                    matrix.getMatrixArray()[a][a]= temp;
                }

                for (int k = (0+a); k < (matrix.getLength()-a); k++) {
                    temp = matrix.getMatrixArray()[k+1][matrix.getBreadth()-a];
                    matrix.getMatrixArray()[k+1][matrix.getBreadth()-a] = matrix.getMatrixArray()[a][a];
                    matrix.getMatrixArray()[a][a]= temp;
                }

                for (int l = (matrix.getBreadth()-a); l > (0+a); l--) {
                    temp = matrix.getMatrixArray()[matrix.getLength()-a][l-1];
                    matrix.getMatrixArray()[matrix.getLength()-a][l-1] = matrix.getMatrixArray()[a][a];
                    matrix.getMatrixArray()[a][a]= temp;
                }

                for (int m = (matrix.getLength()-a); m > (0+a) ; m--) {
                    temp = matrix.getMatrixArray()[m-1][a];
                    matrix.getMatrixArray()[m-1][a]= matrix.getMatrixArray()[a][a];
                    matrix.getMatrixArray()[a][a]= temp;
                }

           }

        }
    }
}
