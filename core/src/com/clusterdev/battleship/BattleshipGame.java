package com.clusterdev.battleship;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.clusterdev.screens.GameScreen;

public class BattleshipGame extends Game{

	@Override
	public void create() {
		Gdx.app.log("Battleship", "created");
		setScreen(new GameScreen());
	}
}
