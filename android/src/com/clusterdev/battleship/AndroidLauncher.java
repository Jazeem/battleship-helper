package com.clusterdev.battleship;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.clusterdev.battleship.BattleshipGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new BattleshipGame(), config);
	}

	@Override
	protected void createWakeLock(boolean use) {
		use = true;
		super.createWakeLock(use);
	}
}
