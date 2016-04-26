package data;

import java.util.concurrent.CopyOnWriteArrayList;

public class TowerIce extends Tower
{
    public TowerIce(Tile startTile, CopyOnWriteArrayList<Enemy> enemies)
    {
        super(TowerType.CannonIce, startTile, enemies);
    }

    @Override
    public void shoot(Enemy e)
    {
        super.projectiles.add(new ProjectileIceball(super.type.projectileType, super.getTarget(), super.getX(), super.getY(), 16, 16));
    }
}
