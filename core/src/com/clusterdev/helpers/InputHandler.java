package com.clusterdev.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.clusterdev.gameobjects.Grid;
import com.clusterdev.gameworld.GameWorld;

import static com.clusterdev.Constants.GAME_SIZE;
import static com.clusterdev.Constants.RECT_SIZE;

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
        if(screenX < GAME_SIZE && screenY < GAME_SIZE){
            int gridSize = RECT_SIZE * 2;
            int x = screenX / gridSize, y = screenY / gridSize;
            if(x >= 0 && x <= 9 && y >= 0 && y <= 9)
                rectangles[screenX / gridSize][screenY / gridSize].setState();
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
