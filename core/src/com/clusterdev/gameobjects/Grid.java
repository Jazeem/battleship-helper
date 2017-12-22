package com.clusterdev.gameobjects;

import com.clusterdev.Constants;

/**
 * Created by jazeem on 22/12/17.
 */

public class Grid {
    int x, y;

    public void setState() {
        switch (state){
            case NOT_FIRED:
                state = Constants.GRID_STATE.MARKED;
                break;
            case MARKED:
                state = Constants.GRID_STATE.MISSED;
                break;
            case MISSED:
                state = Constants.GRID_STATE.HIT;
                break;
            case HIT:
                state = Constants.GRID_STATE.WRECKED;
                break;
            case WRECKED:
                state = Constants.GRID_STATE.NOT_FIRED;
                break;

        }
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
