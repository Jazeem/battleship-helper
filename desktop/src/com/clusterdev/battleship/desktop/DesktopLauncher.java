package com.clusterdev.battleship.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.clusterdev.battleship.BattleshipGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Battleship";
		config.width = 500;
		config.height = 500;
		new LwjglApplication(new BattleshipGame(), config);
	}
}
