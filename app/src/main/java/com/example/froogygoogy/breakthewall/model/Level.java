package com.example.froogygoogy.breakthewall.model;

import com.example.froogygoogy.breakthewall.Assets;
import com.example.froogygoogy.breakthewall.framework.BallCollider;


import java.util.LinkedList;
import java.util.List;

public class Level {


    private Level level;
    private int nLevel;

    public Level getLevel ( )
    {
        return level;
    }

    public void setLevel (int level)
    {
      nLevel = level;
    }
    public Level(int n){
        n = nLevel;
    }




}
