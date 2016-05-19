package pl.calcexe.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import pl.calcexe.states.Direction;

/**
 * Created by Rubin on 2016-03-11.
 */
public class Bird
{
    private TextureRegion region;
    private final int FRAMES_COUNT = 2;
    private ArrayList<TextureRegion> frames;
    private Vector2 position;

    private Direction direction;
    private Vector2 velocity;
    private static final int SPEED = 280;
    private static final int GRAVITY = 30;

    public Bird(BirdsFiles color)
    {
        Texture texture = new Texture(color.getPath());

        frames = new ArrayList<TextureRegion>();

        for (int i = 0; i < FRAMES_COUNT; i++)
            frames.add(new TextureRegion(texture,
                    texture.getWidth() / FRAMES_COUNT * i, 0,
                    texture.getWidth() / FRAMES_COUNT, texture.getHeight()
            ));

        position = new Vector2(200, 200);
        velocity = new Vector2();

        direction = Direction.LEFT;
    }

    public void update(float dt)
    {
        velocity.add(0, -GRAVITY);
        velocity.scl(dt);

        position.add(SPEED * dt * direction.getValue() * -1, velocity.y);

        velocity.scl(1 / dt);

        if (position.x < 0)
            position.x = 0;
    }

    public void setPosition(float x, float y)
    {
        position.x = x;
        position.y = y;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public void flip()
    {
        for (TextureRegion reg : frames)
        {
                reg.flip(true, false);
        }
        if (direction == Direction.LEFT)
            direction = Direction.RIGHT;
        else
            direction = Direction.LEFT;

    }

    public void jump()
    {
        velocity.y = 350;
    }

    public TextureRegion getFrame()
    {
        return frames.get(0);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void dispose()
    {
        for (int i = 0; i < FRAMES_COUNT; i++)
            frames.get(i).getTexture().dispose();
    }
}
