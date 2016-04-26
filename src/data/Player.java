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
    private WaveManager waveManager;
    private ArrayList<Tower> towerList;
    private boolean leftMouseButtonDown, rightMouseButton;
    public static int Cash, Lives;

    public Player(TileGrid grid, WaveManager waveManager)
    {
        this.grid = grid;
        this.types = new TileType[3];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Dirt;
        this.types[2] = TileType.Water;
        this.waveManager = waveManager;
        this.towerList = new ArrayList<>();
        this.leftMouseButtonDown = false;
        this.rightMouseButton = false;
        Cash = 0;
        Lives = 0;
    }

    public void setup()
    {
        Cash = 200;
        Lives = 10;
    }

    public static boolean modifyCash(int amount)
    {
        if(Cash + amount >= 0)
        {
            Cash += amount;
            System.out.println("Player Cash: " + Cash);
            return true;
        }
        System.out.println("low Cash " + Cash);
        return false;
    }

    public static void modifyLives(int amount)
    {
        Lives += amount;
    }

    public void update()
    {
        for(Tower t : towerList)
        {
            t.update();
            t.draw();
            t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
        }

        //Handle Mouse Input
        if(Mouse.isButtonDown(0) && !leftMouseButtonDown)
        {
            if(modifyCash(-20))
            {
                towerList.add(new TowerCannonBlue(grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE),
                        waveManager.getCurrentWave().getEnemyList()));
            }
        }
        leftMouseButtonDown = Mouse.isButtonDown(0);

        if(Mouse.isButtonDown(1) && !rightMouseButton)
        {
            if(modifyCash(-55))
            {
                towerList.add(new TowerIce(grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE),
                        waveManager.getCurrentWave().getEnemyList()));
            }
        }
        rightMouseButton = Mouse.isButtonDown(1);


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
        }
    }
}
