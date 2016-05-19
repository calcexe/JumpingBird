package pl.calcexe.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.calcexe.JumpingBird;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 300;
		config.height = (int)(300 * 1.77f);
		config.samples = 4;
		new LwjglApplication(new JumpingBird(), config);
	}
}
