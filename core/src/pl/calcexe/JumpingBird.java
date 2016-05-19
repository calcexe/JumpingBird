package pl.calcexe;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pl.calcexe.states.GameStatesManager;
import pl.calcexe.states.MenuState;

public class JumpingBird extends ApplicationAdapter {
	SpriteBatch batch;
	GameStatesManager gsm;

	@Override
	public void create () {
		batch = new SpriteBatch();

		gsm = new GameStatesManager();

		gsm.push(new MenuState(gsm));

		Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

	}
}
