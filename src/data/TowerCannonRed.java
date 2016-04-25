package data;

import java.util.ArrayList;

public class TowerCannonRed extends Tower
{
    public TowerCannonRed(Tile startTile, ArrayList<Enemy> enemies)
    {
        super(TowerType.CannonRed, startTile, enemies);
    }
}
