package pl.calcexe.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Camera;
import pl.calcexe.states.Direction;

/**
 * Created by Rubin on 2016-03-10.
 */
public class Obstacles
{
    private int HEIGHT;
    private int MIN_HEIGHT;
    private int CAPACITY;
    private int MIN_CAPACITY;
    private int SPACE;

    private ShapeRenderer obstacles;
    private ArrayList<Vector2> positions;
    private Random random;
    private OrthographicCamera camera;
    private Direction direction;

    public Obstacles(int height, OrthographicCamera camera)
    {
        obstacles = new ShapeRenderer();
        positions = new ArrayList<Vector2>();
        random = new Random();

        this.camera = camera;

        HEIGHT = height;
        MIN_HEIGHT = HEIGHT;
        SPACE = HEIGHT / 2;
        int MAX_HEIGHT = (int)camera.viewportHeight - HEIGHT;
        CAPACITY = ((MAX_HEIGHT - MIN_HEIGHT + SPACE) / (HEIGHT + SPACE));
        MIN_CAPACITY = 2;

        MIN_HEIGHT = ((int)camera.viewportHeight - (CAPACITY * HEIGHT) - (CAPACITY - 1) * SPACE) / 2;

        obstacles.setAutoShapeType(true);
        obstacles.setColor(Color.BLACK);
    }

    public Obstacles(int height, OrthographicCamera camera, Direction direction)
    {
        obstacles = new ShapeRenderer();
        positions = new ArrayList<Vector2>();

        this.camera = camera;
        this.direction = direction;

        if (direction == Direction.TOP)
        {
            HEIGHT = height;
            MIN_HEIGHT = HEIGHT;
            SPACE = HEIGHT / 4;
            int MAX_HEIGHT = (int)camera.viewportWidth - HEIGHT;
            CAPACITY = ((int)camera.viewportWidth / (HEIGHT));
            MIN_CAPACITY = 2;

            MIN_HEIGHT = ((int)camera.viewportWidth - (CAPACITY * HEIGHT) - (CAPACITY - 1) * SPACE) / 2;

            obstacles.setAutoShapeType(true);
            obstacles.setColor(Color.BLACK);

            for (int i = 0; i < CAPACITY; i++)
            {
                positions.add(new Vector2(i * (HEIGHT + SPACE) + MIN_HEIGHT, camera.viewportHeight));
            }
        }

        if (direction == Direction.BOTTOM)
        {


            HEIGHT = height;
            MIN_HEIGHT = HEIGHT;
            SPACE = HEIGHT / 4;
            int MAX_HEIGHT = (int)camera.viewportWidth - HEIGHT;
            CAPACITY = ((int)camera.viewportWidth / (HEIGHT));
            MIN_CAPACITY = 2;

            MIN_HEIGHT = ((int)camera.viewportWidth - (CAPACITY * HEIGHT) - (CAPACITY - 1) * SPACE) / 2;

            obstacles.setAutoShapeType(true);
            obstacles.setColor(Color.BLACK);

            for (int i = 0; i < CAPACITY; i++)
            {
                positions.add(new Vector2(i * (HEIGHT + SPACE) + MIN_HEIGHT, 0));
            }

        }
    }

    public void generate(Direction dir)
    {
        direction = dir;
        boolean obstacles_pos[] = new boolean[CAPACITY];
        int x;

        if (dir == Direction.LEFT)
            x = 0;
        else
            x = (int)camera.viewportWidth;

        positions.clear();

            for (int i = 0; i < random.nextInt(CAPACITY - MIN_CAPACITY) + MIN_CAPACITY; i++)
            {
                int rand;
                do {
                    rand = random.nextInt(CAPACITY);
                } while(obstacles_pos[rand]);

                obstacles_pos[rand] = true;
                positions.add(new Vector2(x, ((rand * HEIGHT + rand * SPACE + MIN_HEIGHT))));
            }
    }

    private float sign(Vector2 p1, Vector2 p2, Vector2 p3)
    {
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
    }

    public boolean checkCollision(Vector2 pt)
    {
        boolean b1, b2, b3;

        for (Vector2 position : positions)
        {

            Vector2 v1 = new Vector2(position.x, position.y);
            Vector2 v2 = new Vector2(position.x, position.y + HEIGHT);
            Vector2 v3 = new Vector2(position.x + (HEIGHT * 0.75f) * direction.getValue(), position.y + HEIGHT / 2);

            b1 = sign(pt, v1, v2) < 0.0f;
            b2 = sign(pt, v2, v3) < 0.0f;
            b3 = sign(pt, v3, v1) < 0.0f;

            if ((b1 == b2) && (b2 == b3))
                return true;
        }

        return false;
    }

    public void drawBottom()
    {
        obstacles.setProjectionMatrix(camera.combined);
        obstacles.begin(ShapeRenderer.ShapeType.Filled);


        for (int i = 0; i < positions.size(); i++)
            obstacles.triangle(
                    positions.get(i).x, positions.get(i).y,
                    positions.get(i).x + HEIGHT, positions.get(i).y,
                    positions.get(i).x + HEIGHT / 2, positions.get(i).y + (HEIGHT * 0.75f) * direction.getValue()
            );

        obstacles.end();
    }

    public void draw()
    {
        obstacles.setProjectionMatrix(camera.combined);
        obstacles.begin(ShapeRenderer.ShapeType.Filled);

        for (int i = 0; i < positions.size(); i++)
            obstacles.triangle(
                    positions.get(i).x, positions.get(i).y,
                    positions.get(i).x, positions.get(i).y + HEIGHT,
                    positions.get(i).x + (HEIGHT * 0.75f) * direction.getValue(), positions.get(i).y + HEIGHT / 2
            );

        obstacles.end();
    }

    public int getHeight()
    {
        return HEIGHT;
    }

    public void dispose()
    {
        obstacles.dispose();
    }
}
