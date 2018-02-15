package com.clusterdev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.clusterdev.gameworld.GameRenderer;
import com.clusterdev.gameworld.GameWorld;
import com.clusterdev.helpers.InputHandler;

/**
 * Created by jazeem on 22/12/17.
 */

public class GameScreen implements Screen {

    public GameWorld getWorld() {
        return world;
    }

    private GameWorld world;
    private GameRenderer renderer;

    public GameScreen(){
        world = new GameWorld();
        renderer = new GameRenderer(world);
        Gdx.input.setInputProcessor(new InputHandler(world));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
