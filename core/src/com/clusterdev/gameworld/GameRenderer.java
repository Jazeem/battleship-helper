package com.clusterdev.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.clusterdev.Constants;
import com.clusterdev.gameobjects.Grid;

import static com.clusterdev.Constants.GAME_HEIGHT;
import static com.clusterdev.Constants.GAME_WIDTH;
import static com.clusterdev.Constants.RECT_SIZE;
import static com.clusterdev.Constants.xOffset;
import static com.clusterdev.Constants.yOffset;

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
        cam.setToOrtho(true, GAME_WIDTH, GAME_HEIGHT);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(myWorld.getGameState() == Constants.GAME_STATE.LOBBY){
            BitmapFont font = new BitmapFont();
            SpriteBatch batch = new SpriteBatch();
            batch.begin();
            font.draw(batch, "Waiting for opponent", GAME_WIDTH/2, GAME_HEIGHT/2);
            batch.end();
        }else if(myWorld.getGameState() == Constants.GAME_STATE.FULL){
            BitmapFont font = new BitmapFont();
            SpriteBatch batch = new SpriteBatch();
            batch.begin();
            font.draw(batch, "Game is full", GAME_WIDTH/2, GAME_HEIGHT/2);
            batch.end();
        }else{
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    switch (myWorld.getRectangles()[i][j].getState()){
                        case NOT_FIRED:
                            shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);
                            break;
                        case MISSED:
                            shapeRenderer.setColor(9 / 255.0f, 106 / 255.0f, 203 / 255.0f, 1);
                            break;
                        case HIT:
                            shapeRenderer.setColor(157 / 255.0f, 18 / 255.0f, 25 / 255.0f, 1);
                            break;
                        case WRECKED:
                            shapeRenderer.setColor(120 / 255.0f, 39 / 255.0f, 49 / 255.0f, 1);
                            break;
                        case MARKED:
                            shapeRenderer.setColor(158 / 255.0f, 176 / 255.0f, 30 / 255.0f, 1);
                            break;
                    }
                    shapeRenderer.rect(xOffset + myWorld.getRectangles()[i][j].getX() * RECT_SIZE, yOffset + myWorld.getRectangles()[i][j].getY() * RECT_SIZE,
                            RECT_SIZE, RECT_SIZE);
                }
            }


            for (int i = 0; i < myWorld.getMyShips().length; i++) {
                if(i == myWorld.getShipSelected())
                    shapeRenderer.setColor(158 / 255.0f, 176 / 255.0f, 30 / 255.0f, 1);
                else
                    switch (i){
                        case 0:
                            shapeRenderer.setColor(62/ 255.0f, 39/ 255.0f, 35   / 255.0f, 1);
                            break;
                        case 1:
                            shapeRenderer.setColor(78 / 255.0f, 52 / 255.0f, 46 / 255.0f, 1);
                            break;
                        case 2:
                            shapeRenderer.setColor(93 / 255.0f, 64 / 255.0f, 55 / 255.0f, 1);
                            break;
                        case 3:
                            shapeRenderer.setColor(109 / 255.0f, 76 / 255.0f, 65 / 255.0f, 1);
                            break;
                        case 4:
                            shapeRenderer.setColor(121 / 255.0f, 85 / 255.0f, 72 / 255.0f, 1);
                            break;
                    }
                for (Grid grid: myWorld.getMyShips()[i])
                    shapeRenderer.rect(xOffset + grid.getX() * RECT_SIZE, yOffset + grid.getY() * RECT_SIZE, RECT_SIZE, RECT_SIZE);
            }
            shapeRenderer.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(255 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);
            for(int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++)
                    shapeRenderer.rect(xOffset + myWorld.getRectangles()[i][j].getX() * RECT_SIZE, yOffset + myWorld.getRectangles()[i][j].getY() * RECT_SIZE,
                            RECT_SIZE, RECT_SIZE);
            }
            shapeRenderer.end();

            BitmapFont font = new BitmapFont();
            SpriteBatch batch = new SpriteBatch();
            batch.begin();
            font.draw(batch, "Arrange your ships", GAME_WIDTH/2, GAME_HEIGHT - 10);
            font.draw(batch, "Flip", 20, 20);
            font.draw(batch, "Done", 200, 20);
            batch.end();

        }
    }
}
