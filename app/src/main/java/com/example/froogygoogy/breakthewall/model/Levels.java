package com.example.froogygoogy.breakthewall.model;

import android.util.Log;

import com.example.froogygoogy.breakthewall.Assets;

public class Levels {
    public static final int NO_BRICK = -1;
    private static final int BRICKS_IN_ROW = 10;
    private static int[][][] colors;

    static {
        Log.d("SPEED","Static");

        colors = new int[10][][]; // or other number
// First level:
        Log.d("SPEED","LVL1");

        int nrows = 2; // or other number
        colors[0] = new int[nrows][];
        for (int row = 0 ; row < nrows ; row++) {
            colors[0][row] = new int[BRICKS_IN_ROW];
            for (int col = 0 ; col < BRICKS_IN_ROW ; col++)
                colors[0][row][col] = (row + col) % Assets.bricks.length;
        }
// Second level:
        Log.d("SPEED","LVL2");

        nrows = 4; // or other number
        colors[1] = new int[nrows][];
        for (int row = 0 ; row < nrows ; row++) {
            colors[1][row] = new int[BRICKS_IN_ROW];
            for (int col = 0 ; col < BRICKS_IN_ROW ; col++)
            {
                if (row == col) {
                    colors[1][row][col] = NO_BRICK;
                }
                else
                {
                    colors[1][row][col] = (row + col) % Assets.bricks.length;
                }
            }
        }
// Third level:
        Log.d("SPEED","LVL3");

        nrows = 6; // or other number
        colors[2] = new int[nrows][];
        for (int row = 0 ; row < nrows ; row++) {
            colors[2][row] = new int[BRICKS_IN_ROW];
            for (int col = 0 ; col < BRICKS_IN_ROW ; col++)
            {
                if(row%2==0 && col%2==0)
                {
                    colors[2][row][col] = NO_BRICK;
                }
                else
                {
                    colors[2][row][col] = (row + col) % Assets.bricks.length;

                }
            }
        }
        Log.d("SPEED","FIN");
    }

    public static int[][] getLevel(int level) {
        if(level < colors.length)
        {
            Log.d("SPEED","IF GET LEVEL");

            return colors[level];
        }
        else
        {
            Log.d("SPEED","ELSE GET LEVEL");
            return colors[colors.length -1];
        }
    }

}
