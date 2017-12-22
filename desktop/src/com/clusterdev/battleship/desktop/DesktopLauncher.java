package com.clusterdev.battleship.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.clusterdev.battleship.BattleshipGame;

import static com.clusterdev.Constants.GAME_SIZE;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Battleship";
		config.width = GAME_SIZE;
		config.height = GAME_SIZE;
		new LwjglApplication(new BattleshipGame(), config);
	}
}
