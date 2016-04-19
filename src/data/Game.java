package data;

import static helpers.Artist.quickLoad;

public class Game
{
    private TileGrid grid;
    private Player player;
    private Wave wave;

    //Temporary variables
    TowerCannon tower;

    public Game(int[][] map)
    {
        grid = new TileGrid(map);
        player = new Player(grid);
        wave = new Wave(20, new Enemy(quickLoad("td2dEnmUFO64"), grid.getTile(10, 8), grid, 32, 32, 3));

        tower = new TowerCannon(quickLoad("cannonBase"), grid.getTile(13, 7), 10);
    }

    public void update()
    {
        grid.draw();
        wave.update();
        player.update();

        tower.update();
    }
}
