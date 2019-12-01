package com.selfevaluation.ops;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MatrixOpsTest {

    private MatrixOps matrixOps;

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenSetValueInNullMatrixThenThrowException()
    {
        matrixOps = new MatrixOps();
        matrixOps.setValueInMatrix(1,2,0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenSetValueInEmptyMatrixThenThrowException()
    {
        matrixOps = new MatrixOps(0,0);
        matrixOps.setValueInMatrix(1,2,0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenSetValueInMatrixInNegativePositionThenThenThrowException()
    {
        matrixOps = new MatrixOps(2,3);
        matrixOps.setValueInMatrix(1,-1,0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenSetValueInMatrixInPositionGreaterThanBreadthThenThenThrowException()
    {
        matrixOps = new MatrixOps(2,3);
        matrixOps.setValueInMatrix(1,0,3);
    }

    @Test
    public void whenSetValueInMatrixThenThenInsertValue()
    {
        matrixOps = new MatrixOps(2,3);
        matrixOps.setValueInMatrix(1,1,0);
        Assert.assertEquals(matrixOps.getMatrix().getMatrixArray()[1][0],1);
    }

    @Test
    public void whenRotateThenTakePerceptionPassbyPrinting()
    {
        matrixOps = new MatrixOps(4,4);

        matrixOps.setValueInMatrix(1,0,0);
        matrixOps.setValueInMatrix(2,0,1);
        matrixOps.setValueInMatrix(3,0,2);
        matrixOps.setValueInMatrix(4,0,3);

        matrixOps.setValueInMatrix(5,1,0);
        matrixOps.setValueInMatrix(6,1,1);
        matrixOps.setValueInMatrix(7,1,2);
        matrixOps.setValueInMatrix(8,1,3);

        matrixOps.setValueInMatrix(9,2,0);
        matrixOps.setValueInMatrix(10,2,1);
        matrixOps.setValueInMatrix(11,2,2);
        matrixOps.setValueInMatrix(12,2,3);

        matrixOps.setValueInMatrix(13,3,0);
        matrixOps.setValueInMatrix(14,3,1);
        matrixOps.setValueInMatrix(15,3,2);
        matrixOps.setValueInMatrix(16,3,3);


        matrixOps.prettyPrintMatrix();

        System.out.println("********");
        matrixOps.rotateMatrix(2);
        matrixOps.prettyPrintMatrix();

        //you could assert for updated elements in the before-after locations
    }
}
