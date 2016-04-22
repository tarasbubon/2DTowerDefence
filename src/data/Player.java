package data;

import helpers.Clock;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;

import static helpers.Artist.*;

public class Player
{
    private TileGrid grid;
    private TileType[] types;
    private int index;
    private WaveManager waveManager;
    private ArrayList<TowerCannon> towerList;
    private boolean leftMouseButtonDown;

    public Player(TileGrid grid, WaveManager waveManager)
    {
        this.grid = grid;
        this.types = new TileType[3];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Dirt;
        this.types[2] = TileType.Water;
        this.index = 0;
        this.waveManager = waveManager;
        this.towerList = new ArrayList<>();
        this.leftMouseButtonDown = false;
    }

    public void update()
    {
        for(TowerCannon t : towerList)
        {
            t.update();
        }

        //Handle Mouse Input
        if(Mouse.isButtonDown(0) && !leftMouseButtonDown)
        {
            towerList.add(new TowerCannon(quickLoad("cannonBase"),
                                          grid.getTile(Mouse.getX() / 32, (HEIGHT - Mouse.getY() - 1) / 32),
                                          10,
                                          waveManager.getCurrentWave().getEnemyList()));
        }
        leftMouseButtonDown = Mouse.isButtonDown(0);

        //Handle Keyboard Input
        while(Keyboard.next())
        {
            if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState())
            {
                Clock.changeMultiplier(0.2f);
            }
            if(Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState())
            {
                Clock.changeMultiplier(-0.2f);
            }
            if(Keyboard.getEventKey() == Keyboard.KEY_T && Keyboard.getEventKeyState())
            {
                towerList.add(new TowerCannon(quickLoad("cannonBase"), grid.getTile(13, 5), 10, waveManager.getCurrentWave().getEnemyList()));
            }
        }
    }
}
