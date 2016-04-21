package data;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static helpers.Artist.*;

public class Player
{
    private TileGrid grid;
    private TileType[] types;
    private int index;
    private WaveManager waveManager;
    private ArrayList<TowerCannon> towerList;

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
    }

    public void setTile()
    {
        grid.setTile((int)Math.floor(Mouse.getX() / 32), (int)Math.floor((HEIGHT - Mouse.getY() - 1) / 32), types[index]);
    }

    public void update()
    {
        for(TowerCannon t : towerList)
        {
            t.update();
        }

        //Handle Mouse Input
        if(Mouse.isButtonDown(0))
        {
            setTile();
        }

        //Handle Keyboard Input
        while(Keyboard.next())
        {
            if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState())
            {
                moveIndex();
            }
            if(Keyboard.getEventKey() == Keyboard.KEY_T && Keyboard.getEventKeyState())
            {
                towerList.add(new TowerCannon(quickLoad("cannonBase"), grid.getTile(13, 5), 10, waveManager.getCurrentWave().getEnemyList()));
            }
        }
    }

    private void moveIndex()
    {
        index++;
        if(index > types.length - 1)
        {
            index = 0;
        }
    }
}
