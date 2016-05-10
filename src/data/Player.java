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
    private boolean leftMouseButtonDown, rightMouseButton, holdingTower;
    private Tower tempTower;
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
        this.holdingTower = false;
        this.tempTower = null;
        Cash = 0;
        Lives = 0;
    }

    //Initialize Stats values for Player
    public void setup()
    {
        Cash = 200;
        Lives = 10;
    }

    //Check if player can afford tower, if so: charge player tower cost
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
        //Update holding tower
        if(holdingTower)
        {
            tempTower.setX(getMouseTile().getX());
            tempTower.setY(getMouseTile().getY());
            tempTower.draw();
        }

        //Update all towers in the game
        for(Tower t : towerList)
        {
            t.update();
            t.draw();
            t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
        }

        //Handle Mouse Input
        if(Mouse.isButtonDown(0) && !leftMouseButtonDown)
        {
            placeTower();
        }

        if(Mouse.isButtonDown(1) && !rightMouseButton)
        {
            System.out.println("Right clicked");
        }
        leftMouseButtonDown = Mouse.isButtonDown(0);
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

    private void placeTower()
    {
        Tile currentTile = getMouseTile();
        if(holdingTower)
        {
            if(!currentTile.isOccupied() && modifyCash(-tempTower.getCost()))
            {
                towerList.add(tempTower);
                currentTile.setOccupied(true);
                holdingTower = false;
                tempTower = null;
            }
        }
    }

    public void pickTower(Tower t)
    {
        tempTower = t;
        holdingTower = true;
    }

    private Tile getMouseTile()
    {
        return grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE);
    }
}
