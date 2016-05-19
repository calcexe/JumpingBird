package pl.calcexe.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Rubin on 2016-02-21.
 */
public class GameStatesManager {

    Stack<State> states;

    public GameStatesManager()
    {
        states = new Stack<State>();
    }

    public void push(State state)
    {
        states.push(state);
    }

    public void pop()
    {
        states.pop();
    }

    public void set(State state)
    {
        states.pop();
        states.push(state);
    }

    public void update(float dt)
    {
        states.peek().update(dt);
    }

    public void dispose()
    {
        states.peek().dispose();
    }

    public void render(SpriteBatch sb)
    {
        states.peek().render(sb);
    }
}
