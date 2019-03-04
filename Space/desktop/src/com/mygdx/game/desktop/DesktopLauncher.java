package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	config.fullscreen = false;
//	    config.vSyncEnabled =true;
		config.height = 768;
		config.width = 1366;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
