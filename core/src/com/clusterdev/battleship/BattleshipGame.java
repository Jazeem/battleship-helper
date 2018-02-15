package com.clusterdev.battleship;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.clusterdev.helpers.SocketHelper;
import com.clusterdev.screens.GameScreen;

public class BattleshipGame extends Game{


	@Override
	public void create() {
		Gdx.app.log("Battleship", "created");
		GameScreen gameScreen = new GameScreen();
		SocketHelper.getInstance(gameScreen.getWorld());
		setScreen(gameScreen);
	}
}
