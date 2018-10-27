package com.nickpoole.defendtheworld.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nickpoole.defendtheworld.DtWGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Defend the World!";
		config.height = 960;
		config.width = 1280;
		new LwjglApplication(new DtWGame(), config);
	}
}
