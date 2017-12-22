package com.clusterdev.gameworld;

import com.badlogic.gdx.Gdx;
import com.clusterdev.Constants;
import com.clusterdev.gameobjects.Grid;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jazeem on 22/12/17.
 */

public class GameWorld {

    private Random random;

    public Grid[][] getRectangles() {
        return rectangles;
    }

    private Grid [][] rectangles = new Grid[10][10];

    public GameWorld(){
        for(int i = 0; i < 10; i++)
            for (int j = 0 ; j < 10; j++){
                rectangles[i][j] = new Grid(i, j, Constants.GRID_STATE.NOT_FIRED);
            }
        random = new Random();
    }

    public void update(float delta) {

    }

    public void predictTargets(int x) {
        int top, right, bottom, left, possibilities, maxPossibility = 0;
        List<Grid> gridsWithHighestChance = new ArrayList<Grid>();
        if(x > 1 && x < 6){
            for(int i = 0; i < 10; i++)
                for (int j = 0 ; j < 10; j++){
                    if(rectangles[i][j].getState() == Constants.GRID_STATE.NOT_FIRED){
                        top = right = bottom = left = 0;
                        for(int k = j; k > (j - x) && k >= 0 && rectangles[i][k].getState() == Constants.GRID_STATE.NOT_FIRED; k--)
                            top++;
                        for(int k = i; k < (i + x) && k <= 9 && rectangles[k][j].getState() == Constants.GRID_STATE.NOT_FIRED; k++)
                            right++;
                        for(int k = j; k < (j + x) && k <= 9 && rectangles[i][k].getState() == Constants.GRID_STATE.NOT_FIRED; k++)
                            bottom++;
                        for(int k = i; k > (i - x) && k >= 0 && rectangles[k][j].getState() == Constants.GRID_STATE.NOT_FIRED; k--)
                            left++;
                        possibilities = Math.min(top, bottom) + Math.min(right, left);
                        if(possibilities > maxPossibility){
                            maxPossibility = possibilities;
                            gridsWithHighestChance = new ArrayList<Grid>();
                            gridsWithHighestChance.add(rectangles[i][j]);
                        } else if(possibilities == maxPossibility){
                            gridsWithHighestChance.add(rectangles[i][j]);
                        }

                    }
                }
            if(gridsWithHighestChance.size() > 0){
                 gridsWithHighestChance.get(random.nextInt(gridsWithHighestChance.size())).markTarget();
            }
        }
        //rectangles[1][2].markTarget();
    }
}
