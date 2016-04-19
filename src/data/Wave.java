package data;

import java.util.ArrayList;

import static helpers.Clock.*;

public class Wave
{
    private float timeSinceLastSpawn, spawnTime;
    private Enemy enemyType;
    private ArrayList<Enemy> enemyList;

    public Wave(float spawnTime, Enemy enemType)
    {
        this.enemyType = enemType;
        this.spawnTime = spawnTime;
        timeSinceLastSpawn = 0;
        enemyList = new ArrayList<>();
    }

    public void update()
    {
        timeSinceLastSpawn += delta();
        if(timeSinceLastSpawn > spawnTime)
        {
            spawn();
            timeSinceLastSpawn = 0;
        }

        for(Enemy e : enemyList)
        {
            e.update();
            e.draw();
        }
    }

    private void spawn()
    {
        enemyList.add(new Enemy(enemyType.getTexture(), enemyType.getStartTile(), enemyType.getGrid(), enemyType.getWidth(), enemyType.getHeight(), enemyType.getSpeed()));

    }
}
