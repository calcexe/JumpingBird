package pl.calcexe.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Font;

/**
 * Created by Rubin on 2016-02-23.
 */
public class LoseState extends State {

    protected LoseState(GameStatesManager gsm) {
        super(gsm);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched())
            gsm.set(new PlayState(gsm));
    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void update(float dt)
    {
        handleInput();
    }

    @Override
    public void dispose()
    {

    }
}
