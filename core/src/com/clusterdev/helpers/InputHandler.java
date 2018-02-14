package com.clusterdev.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.clusterdev.Constants;
import com.clusterdev.gameobjects.Grid;
import com.clusterdev.gameworld.GameWorld;

import static com.clusterdev.Constants.GAME_HEIGHT;
import static com.clusterdev.Constants.GAME_WIDTH;
import static com.clusterdev.Constants.RECT_SIZE;
import static com.clusterdev.Constants.xOffset;
import static com.clusterdev.Constants.yOffset;

/**
 * Created by jazeem on 22/12/17.
 */

public class InputHandler implements InputProcessor {

    private Grid[][] rectangles;
    private GameWorld myWorld;

    public InputHandler(GameWorld world) {
        this.rectangles = world.getRectangles();
        this.myWorld = world;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        Gdx.app.log("Keycode", String.valueOf(keycode));
        if(keycode >= 8 && keycode <= 16)
            myWorld.predictTargets(keycode - 7);
        if(keycode == 31)
            myWorld.reset();
        if(keycode == 36)
            myWorld.showHint();
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log(String.valueOf(screenX), String.valueOf(screenY));
        int posX = screenX - xOffset;
        int posY = screenY - yOffset;
        if(posX < GAME_WIDTH && posY < GAME_HEIGHT){
            int gridSize = RECT_SIZE ;
            int x = posX / gridSize, y = posY / gridSize;
            if(x >= 0 && x <= 9 && y >= 0 && y <= 9)
                myWorld.gridClicked(x, y);
        }
        if(myWorld.getGameState() == Constants.GAME_STATE.ARRANGE){
            if(screenX < 50 && screenX > 20 && screenY > (GAME_HEIGHT - 20) && screenY < (GAME_HEIGHT - 10))
                myWorld.flipClicked();
            if(screenX < 230 && screenX > 200 && screenY > (GAME_HEIGHT - 20) && screenY < (GAME_HEIGHT - 10))
                myWorld.arrangeComplete();
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
