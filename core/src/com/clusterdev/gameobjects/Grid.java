package com.clusterdev.gameobjects;

import com.clusterdev.Constants;

/**
 * Created by jazeem on 22/12/17.
 */

public class Grid {
    int x, y;

    public void setState(Constants.GRID_STATE state) {
        this.state = state;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {

        return x;
    }

    public int getY() {
        return y;
    }

    public Constants.GRID_STATE getState() {
        return state;
    }

    Constants.GRID_STATE state;
    public Grid(int x, int y, Constants.GRID_STATE state){
        this.x = x;
        this.y = y;
        this.state = state;
    }

    public void markTarget() {
        this.state = Constants.GRID_STATE.MARKED;
    }
}
