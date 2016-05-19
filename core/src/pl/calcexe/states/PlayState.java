package pl.calcexe.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.security.Key;

import pl.calcexe.objects.Bird;
import pl.calcexe.objects.BirdsFiles;
import pl.calcexe.objects.Obstacles;

/**
 * Created by Rubin on 2016-02-21.
 */
public class PlayState extends State
{
    private Obstacles obstacles;
    private Obstacles ground;
    private Obstacles roof;

    private Bird bird;

    public PlayState(GameStatesManager gsm)
    {
        super(gsm);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 300, 300 * 1.77f);

        obstacles = new Obstacles(30, camera);

        obstacles.generate(Direction.LEFT);

        bird = new Bird(BirdsFiles.RED);
        ground = new Obstacles(24, camera, Direction.BOTTOM);
        roof = new Obstacles(24, camera, Direction.TOP);

    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched())
            bird.jump();

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(camera.combined);

        sb.begin();

        sb.draw(bird.getFrame(), bird.getPosition().x, bird.getPosition().y);

        sb.end();
        obstacles.draw();
        ground.drawBottom();
        roof.drawBottom();
    }

    @Override
    public void update(float dt) {

        handleInput();

        bird.update(dt);

        if (bird.getPosition().x <= 0)
        {
            bird.setPosition(0, bird.getPosition().y);
            bird.flip();
            obstacles.generate(Direction.RIGHT);
        }
        else if (bird.getPosition().x + bird.getFrame().getRegionWidth() >= camera.viewportWidth)
        {
            bird.setPosition(camera.viewportWidth - bird.getFrame().getRegionWidth(), bird.getPosition().y);
            bird.flip();
            obstacles.generate(Direction.LEFT);
        }

        if (obstacles.checkCollision(new Vector2(bird.getPosition().x,
                bird.getPosition().y)))
            gsm.set(new MenuState(gsm));

        if (obstacles.checkCollision(new Vector2(bird.getPosition().x,
                bird.getPosition().y + bird.getFrame().getRegionHeight())))
            gsm.set(new MenuState(gsm));

        if (obstacles.checkCollision(new Vector2(bird.getPosition().x + bird.getFrame().getRegionWidth(),
                bird.getPosition().y)))
            gsm.set(new MenuState(gsm));

        if (obstacles.checkCollision(new Vector2(bird.getPosition().x + bird.getFrame().getRegionWidth(),
                bird.getPosition().y + bird.getFrame().getRegionHeight())))
            gsm.set(new MenuState(gsm));

        if (bird.getPosition().y + bird.getFrame().getRegionHeight() >= camera.viewportHeight - roof.getHeight())
            gsm.set(new MenuState(gsm));

        if (bird.getPosition().y <= ground.getHeight())
            gsm.set(new MenuState(gsm));

        if (bird.getPosition().y < 0)
            bird.setPosition(bird.getPosition().x, 0);

        if (bird.getPosition().y + bird.getFrame().getRegionHeight() > camera.viewportHeight)
            bird.setPosition(bird.getPosition().x, camera.viewportHeight - bird.getFrame().getRegionHeight());
    }

    @Override
    public void dispose() {
        obstacles.dispose();
    }
}
