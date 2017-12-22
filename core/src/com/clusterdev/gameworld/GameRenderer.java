package com.clusterdev.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.clusterdev.Constants.RECT_SIZE;

/**
 * Created by jazeem on 22/12/17.
 */

public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    public GameRenderer(GameWorld world) {
        myWorld = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 250, 250);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);


        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                switch (myWorld.getRectangles()[i][j].getState()){
                    case NOT_FIRED:
                        shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);
                        break;
                    case MISSED:
                        shapeRenderer.setColor(0, 0, 1, 1);
                        break;
                    case HIT:
                        shapeRenderer.setColor(1, 0, 0, 1);
                        break;
                    case WRECKED:
                        shapeRenderer.setColor(0.5f, 0, 0, 1);
                        break;
                    case MARKED:
                        shapeRenderer.setColor(0, 1, 0, 1);
                        break;
                }
                shapeRenderer.rect(myWorld.getRectangles()[i][j].getX() * RECT_SIZE, myWorld.getRectangles()[i][j].getY() * RECT_SIZE,
                        RECT_SIZE, RECT_SIZE);
            }
        }


        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        shapeRenderer.setColor(255 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);

        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++)
                shapeRenderer.rect(myWorld.getRectangles()[i][j].getX() * RECT_SIZE, myWorld.getRectangles()[i][j].getY() * RECT_SIZE,
                        RECT_SIZE, RECT_SIZE);
        }

        shapeRenderer.end();
    }
}
