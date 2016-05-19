package pl.calcexe.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Rubin on 2016-02-21.
 */
public abstract class State {

    protected GameStatesManager gsm;
    protected State(GameStatesManager gsm)
    {
        this.gsm = gsm;
    }
    protected OrthographicCamera camera;

    public abstract void handleInput();
    public abstract void render(SpriteBatch sb);
    public abstract void update(float dt);
    public abstract void dispose();

}
