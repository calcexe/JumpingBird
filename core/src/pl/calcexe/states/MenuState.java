package pl.calcexe.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Menu;
import java.awt.event.KeyListener;

import javafx.scene.Camera;

/**
 * Created by Rubin on 2016-02-21.
 */
public class MenuState extends State {

    public MenuState(GameStatesManager gsm)
    {
        super(gsm);
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
    }

    @Override
    public void handleInput()
    {
        if(Gdx.input.justTouched())
            gsm.set(new PlayState(gsm));

    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.setProjectionMatrix(camera.combined);

        sb.begin();
        sb.end();
    }

    @Override
    public void update(float dt)
    {
        handleInput();

    }

    @Override
    public void dispose() {

    }
}
