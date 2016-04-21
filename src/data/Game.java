package data;

import static helpers.Artist.quickLoad;

public class Game
{
    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;

    public Game(int[][] map)
    {
        grid = new TileGrid(map);
        waveManager = new WaveManager(new Enemy(quickLoad("td2dEnmUFO64"), grid.getTile(10, 8), grid, 32, 32, 40), 2, 2);
        player = new Player(grid, waveManager);
    }

    public void update()
    {
        grid.draw();
        waveManager.update();
        player.update();
    }
}
