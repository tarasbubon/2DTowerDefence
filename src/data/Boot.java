package data;

import org.lwjgl.opengl.Display;

import static helpers.Artist.*;

public class Boot
{
    public Boot()
    {
        beginSession();

        int[][] map = {
                {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 2, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        TileGrid grid = new TileGrid(map);

        //Test code
        grid.setTile(2, 4, grid.getTile(2, 3).getType());
        Enemy e = new Enemy(quickLoad("td2dEnmUFO64"), grid.getTile(10, 10), 64, 64, 2);

        while(!Display.isCloseRequested())
        {
            grid.draw();
            e.draw();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }

    public static void main(String[] args)
    {
        new Boot();
    }
}
