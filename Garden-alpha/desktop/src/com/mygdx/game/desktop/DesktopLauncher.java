package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Garden;
import com.mygdx.game.MyConstSettings;

public class DesktopLauncher implements MyConstSettings {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Garden(), config);
		config.fullscreen = false;
		config.height = HEIGHT_WINDOW;
		config.width = WIDTH_WINDOW;
	}
	@Override
	public void run(){

	}
}
