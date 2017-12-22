package com.clusterdev.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.clusterdev.gameobjects.Grid;

import static com.clusterdev.Constants.GAME_SIZE;
import static com.clusterdev.Constants.RECT_SIZE;

/**
 * Created by jazeem on 22/12/17.
 */

public class InputHandler implements InputProcessor {

    private Grid[][] rectangles;

    public InputHandler(Grid[][] rectangles) {
        this.rectangles = rectangles;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
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
            rectangles[screenY / gridSize][screenX / gridSize].setState();
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
