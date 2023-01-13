package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();

		cfg.width =sSize.width;
		cfg.height = sSize.height;
		//cfg.width = 1280;
		//cfg.height = 720;
		cfg.title = "VC";
		cfg.addIcon("core/assets/icon.png", Files.FileType.Internal);
		cfg.x = 0;
		cfg.y = 0;
		//cfg.fullscreen = true;

		cfg.vSyncEnabled = true;





		//cfg.useGL20 = false;

		new LwjglApplication(new MyGdxGame(), cfg);

		//LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();









	}
}
