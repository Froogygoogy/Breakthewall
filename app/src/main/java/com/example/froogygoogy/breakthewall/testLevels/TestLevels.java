package com.example.froogygoogy.breakthewall.testLevels;

import android.util.DisplayMetrics;

import com.example.froogygoogy.breakthewall.Assets;
import com.example.froogygoogy.breakthewall.framework.GameActivity;
import com.example.froogygoogy.breakthewall.framework.IGameController;
import com.example.froogygoogy.breakthewall.testBricks.TestBrickController;

public class TestLevels extends GameActivity {
    private TestBrickController controller;

    public static final int NO_BRICK = -1;
    private static final int BRICKS_IN_ROW = 10;
    private static int[][][] colors;

    static{

        colors = new int[10][][];

        //FIRST LEVEL
        int nrows = 2;
        colors[0] = new int[nrows][];

        for (int row = 0;row < nrows; row++)
        {
            colors[0][row] = new int [BRICKS_IN_ROW];
            for (int col = 0; col< BRICKS_IN_ROW; col ++)
            {
                colors[0][row][col] = (row + col) % Assets.bricks.length;
            }
        }

        //SECOND LEVEL
        int mrows = 4;
        colors[0] = new int[mrows][];

        for (int row = 0;row < mrows; row++)
        {
            colors[0][row] = new int [BRICKS_IN_ROW];
            for (int col = 0; col< BRICKS_IN_ROW; col ++)
            {
                colors[0][row][col] = (row + col) % Assets.bricks.length;
            }
        }

        //THIRD LEVEL
        int lrows = 5;
        colors[0] = new int[lrows][];

        for (int row = 0;row < lrows; row++)
        {
            colors[0][row] = new int [BRICKS_IN_ROW];
            for (int col = 0; col< BRICKS_IN_ROW; col ++)
            {
                colors[0][row][col] = (row + col) % Assets.bricks.length;
            }
        }





    }


    public static int[][] getLevel(int n) {

        if (n < colors.length) return colors[n];
        else{
            return colors[colors.length-1];
        }
    }


    @Override
    protected IGameController buildGameController() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.controller = new TestBrickController(displayMetrics.widthPixels,displayMetrics.heightPixels, this);
        return controller;
    }


}
