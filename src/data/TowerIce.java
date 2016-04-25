package data;

import java.util.ArrayList;

public class TowerIce extends Tower
{
    public TowerIce(Tile startTile, ArrayList<Enemy> enemies)
    {
        super(TowerType.CannonIce, startTile, enemies);
    }
}
