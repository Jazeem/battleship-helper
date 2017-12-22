package com.clusterdev.gameworld;

import com.clusterdev.Constants;
import com.clusterdev.gameobjects.Grid;

/**
 * Created by jazeem on 22/12/17.
 */

public class GameWorld {



    public Grid[][] getRectangles() {
        return rectangles;
    }

    private Grid [][] rectangles = new Grid[10][10];

    public GameWorld(){
        for(int i = 0; i < 10; i++)
            for (int j = 0 ; j < 10; j++){
                rectangles[i][j] = new Grid(j, i , Constants.GRID_STATE.NOT_FIRED);
            }
    }

    public void update(float delta) {

    }
}
