package com.clusterdev.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.clusterdev.Constants;
import com.clusterdev.gameobjects.Grid;

import static com.clusterdev.Constants.B1X_OFFSET;
import static com.clusterdev.Constants.B1Y_OFFSET;
import static com.clusterdev.Constants.B2X_OFFSET;
import static com.clusterdev.Constants.B2Y_OFFSET;
import static com.clusterdev.Constants.GAME_HEIGHT;
import static com.clusterdev.Constants.GAME_WIDTH;
import static com.clusterdev.Constants.RECT_SIZE;
import static com.clusterdev.Constants.xOffset;
import static com.clusterdev.Constants.xOffsetEnemy;
import static com.clusterdev.Constants.yOffset;
import static com.clusterdev.Constants.yOffsetEnemy;

/**
 * Created by jazeem on 22/12/17.
 */

public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private SpriteBatch batch;

    public GameRenderer(GameWorld world) {
        myWorld = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, GAME_WIDTH, GAME_HEIGHT);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        font = new BitmapFont();
        font.getData().setScale(3f);
        batch = new SpriteBatch();
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(myWorld.getGameState() == Constants.GAME_STATE.LOBBY){
            batch.begin();
            font.draw(batch, "Waiting for opponent", GAME_WIDTH/2, GAME_HEIGHT/2);
            batch.end();
        }else if(myWorld.getGameState() == Constants.GAME_STATE.FULL){
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
                    if(myWorld.getRectangles()[grid.getX()][grid.getY()].getState() == Constants.GRID_STATE.NOT_FIRED) {
                        shapeRenderer.rect(xOffset + grid.getX() * RECT_SIZE, yOffset + grid.getY() * RECT_SIZE
                                , RECT_SIZE, RECT_SIZE);
                    }
            }
            shapeRenderer.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(255 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);
            for(int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++){
                    shapeRenderer.rect(xOffset + myWorld.getRectangles()[i][j].getX() * RECT_SIZE, yOffset + myWorld.getRectangles()[i][j].getY() * RECT_SIZE,
                            RECT_SIZE, RECT_SIZE);
                }
            }
            shapeRenderer.end();

            if(myWorld.getGameState() == Constants.GAME_STATE.ARRANGE){
                batch.begin();
                if(myWorld.isArrangeWaiting())
                    font.draw(batch, "Waiting for opponent", GAME_WIDTH/2 + 200, GAME_HEIGHT/2);
                else
                    font.draw(batch, "Arrange your ships", GAME_WIDTH/2, GAME_HEIGHT - 10);
                font.draw(batch, "Flip", B1X_OFFSET, B1Y_OFFSET);
                font.draw(batch, "Done", B2X_OFFSET, B2Y_OFFSET);
                batch.end();
            } else {
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                for(int i = 0; i < 10; i++){
                    for(int j = 0; j < 10; j++){
                        switch (myWorld.getEnemyRectangles()[i][j].getState()){
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
                        shapeRenderer.rect(xOffsetEnemy + myWorld.getEnemyRectangles()[i][j].getX() * RECT_SIZE,
                                yOffsetEnemy + myWorld.getEnemyRectangles()[i][j].getY() * RECT_SIZE,
                                RECT_SIZE, RECT_SIZE);
                    }
                }
                shapeRenderer.end();
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.setColor(255 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);
                for(int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++)
                        shapeRenderer.rect(xOffsetEnemy + myWorld.getEnemyRectangles()[i][j].getX() * RECT_SIZE,
                                yOffsetEnemy + myWorld.getEnemyRectangles()[i][j].getY() * RECT_SIZE,
                                RECT_SIZE, RECT_SIZE);
                }
                shapeRenderer.end();
                batch.begin();
                font.draw(batch, "Shots available: " + myWorld.getShotsAvailable(), B1X_OFFSET, B1Y_OFFSET);
                font.draw(batch, "Fire", B2X_OFFSET, B2Y_OFFSET);
                if(myWorld.isPlayWaiting())
                    font.draw(batch, "Waiting for opponent", B1X_OFFSET, 2 * B1Y_OFFSET);
                batch.end();
            }
        }
    }
}
