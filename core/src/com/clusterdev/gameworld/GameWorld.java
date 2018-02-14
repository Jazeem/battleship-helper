package com.clusterdev.gameworld;

import com.badlogic.gdx.Gdx;
import com.clusterdev.Constants;
import com.clusterdev.gameobjects.Grid;


import java.awt.Rectangle;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.clusterdev.Constants.GAME_STATE;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

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
    private Grid [][] myRectangles = new Grid[10][10];


    private GAME_STATE gameState;

    public Grid[][] getMyShips() {
        return myShips;
    }

    Grid[][] myShips = new Grid[][]{
            new Grid[]{

                    new Grid(0, 0, Constants.GRID_STATE.NOT_FIRED),
                    new Grid(0, 1, Constants.GRID_STATE.NOT_FIRED),
                    new Grid(0, 2, Constants.GRID_STATE.NOT_FIRED),
                    new Grid(0, 3, Constants.GRID_STATE.NOT_FIRED),
                    new Grid(0, 4, Constants.GRID_STATE.NOT_FIRED)
            },
            new Grid[]{
                    new Grid(1, 0, Constants.GRID_STATE.NOT_FIRED),
                    new Grid(1, 1, Constants.GRID_STATE.NOT_FIRED),
                    new Grid(1, 2, Constants.GRID_STATE.NOT_FIRED),
                    new Grid(1, 3, Constants.GRID_STATE.NOT_FIRED)
            },
            new Grid[]{
                    new Grid(2, 0, Constants.GRID_STATE.NOT_FIRED),
                    new Grid(2, 1, Constants.GRID_STATE.NOT_FIRED),
                    new Grid(2, 2, Constants.GRID_STATE.NOT_FIRED)
            },
            new Grid[]{
                    new Grid(3, 0, Constants.GRID_STATE.NOT_FIRED),
                    new Grid(3, 1, Constants.GRID_STATE.NOT_FIRED),
                    new Grid(3, 2, Constants.GRID_STATE.NOT_FIRED)
            },
            new Grid[]{
                    new Grid(4, 0, Constants.GRID_STATE.NOT_FIRED),
                    new Grid(4, 1, Constants.GRID_STATE.NOT_FIRED)
            }
    };

    private Socket mSocket;


    private Emitter.Listener onGameStarted = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            gameState = GAME_STATE.ARRANGE;
        }
    };

    private Emitter.Listener onGameOver = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            if(((String)args[0]).equals("roomfull"))
                gameState = GAME_STATE.FULL;
        }
    };

    int shipSelected = -1;

    public GameWorld(){
        reset();
        random = new Random();
        gameState = GAME_STATE.LOBBY;
        try {
            mSocket = IO.socket("http://192.168.1.5:3000");
        } catch (URISyntaxException e) {}
        mSocket.connect();
        mSocket.on("gamestarted", onGameStarted);
        mSocket.on("gameover", onGameOver);
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

    private int findShip(int x, int y){
        for(int i = 0; i < myShips.length; i++){
            for (Grid grid: myShips[i]) {
                if(grid.getX() == x && grid.getY() == y)
                    return i;
            }
        }
        return -1;
    }

    private boolean isShipHorizontal(int ship){
        return myShips[ship][0].getY() == myShips[ship][1].getY();
    }

    public int getShipSelected() {
        return shipSelected;
    }

    private void moveShip(int ship, int x, int y, boolean flip){
        boolean horizontal = isShipHorizontal(ship);
        if(flip)
            horizontal = !horizontal;
        if(horizontal){
            int i = 0;
            if(flip)
                i++; //else first position of the ship will always conflict
            for(; i < myShips[ship].length; i++)
                if(x + i > 9 || findShip(x + i, y) != -1)
                    break;
            if(i == myShips[ship].length)
                for (int j = 0; j < myShips[ship].length; j++) {
                    myShips[ship][j].setX(x + j);
                    myShips[ship][j].setY(y);
                }
        } else {
            int i = 0;
            if(flip)
                i++;
            for(; i < myShips[ship].length; i++)
                if(y + i > 9 || findShip(x, y + i) != -1)
                    break;
            if(i == myShips[ship].length)
                for (int j = 0; j < myShips[ship].length; j++) {
                    myShips[ship][j].setX(x);
                    myShips[ship][j].setY(y + j);
                }
        }
    }

    public void gridClicked(int x, int y) {
        if(gameState == GAME_STATE.ARRANGE){
            if(shipSelected == -1){
                int shipClicked = findShip(x, y);
                if(shipClicked != -1)
                    shipSelected = shipClicked;
            } else {
                int shipClicked = findShip(x, y);
                if(shipClicked == -1){
                    moveShip(shipSelected, x, y, false);
                    shipSelected = -1;
                } else
                    shipSelected = shipClicked;
            }
        }
    }

    public void flipClicked() {
        if(shipSelected != -1){
            moveShip(shipSelected, myShips[shipSelected][0].getX(), myShips[shipSelected][0].getY(), true);
            shipSelected = -1;
        }
    }

    private String serialize(Grid[][] ships){
        String retVal = "";
        for (Grid[] ship: myShips) {
            for(Grid grid: ship){
                retVal += grid.getX();
                retVal += grid.getY();
            }
        }
        return retVal;
    }

    public void arrangeComplete() {
        mSocket.emit("shiparranged", serialize(myShips));
    }
}
