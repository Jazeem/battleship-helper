package com.clusterdev.gameworld;

import com.badlogic.gdx.Gdx;
import com.clusterdev.Constants;
import com.clusterdev.gameobjects.Grid;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.awt.Rectangle;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.clusterdev.Constants.GAME_STATE;

/**
 * Created by jazeem on 22/12/17.
 */

public class GameWorld {

    private Random random;

    public GAME_STATE getGameState() {
        return gameState;
    }

    public Grid[][] getRectangles() {
        return rectangles;
    }

    private Grid [][] rectangles = new Grid[10][10];

    private GAME_STATE gameState;

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.1.5:3000");
        } catch (URISyntaxException e) {}
    }

    public GameWorld(){
        reset();
        random = new Random();
        gameState = GAME_STATE.LOBBY;
        mSocket.connect();
    }

    public void update(float delta) {

    }

    public void predictTargets(int x) {
        int top, right, bottom, left, possibilities, maxPossibility = 1;
        List<Grid> gridsWithHighestChance = new ArrayList<Grid>();
        List<Grid> gridsWithHighestChanceEven = new ArrayList<Grid>();
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
                        possibilities = Math.max(top + bottom - x, 0) + Math.max(right + left - x, 0);
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
                gridsWithHighestChanceEven = new ArrayList<Grid>();
                for (Grid grid: gridsWithHighestChance) {
                    if((grid.getX() + grid.getY()) % 2 == 0 )
                        gridsWithHighestChanceEven.add(grid);
                }
                if(gridsWithHighestChanceEven.size() > 0)
                    gridsWithHighestChanceEven.get(random.nextInt(gridsWithHighestChanceEven.size())).markTarget();
                else
                    gridsWithHighestChance.get(random.nextInt(gridsWithHighestChance.size())).markTarget();
            }
        }
    }

    public void reset() {
        for(int i = 0; i < 10; i++)
            for (int j = 0 ; j < 10; j++){
                rectangles[i][j] = new Grid(i, j, Constants.GRID_STATE.NOT_FIRED);
            }
    }

    public void showHint() {
        reset();
        for (int i = 0; i < 5; i++)
            rectangles[0+i][3].markTarget();
        for (int i = 0; i < 4; i++)
            rectangles[6][6+i].markTarget();
        for (int i = 0; i < 3; i++)
            rectangles[7+i][1].markTarget();
        for (int i = 0; i < 3; i++)
            rectangles[1+i][9].markTarget();
        for (int i = 0; i < 2; i++)
            rectangles[8+i][6].markTarget();
    }
}
