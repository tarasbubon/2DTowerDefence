package data;

import static helpers.Artist.QuickLoad;

public class EnemyUFO extends Enemy
{
    public EnemyUFO(int tileX, int tileY, TileGrid grid)
    {
        super(tileX, tileY, grid);
        this.setTexture(QuickLoad("td2dEnmUFO64"));
        this.setSpeed(40);
    }
}
