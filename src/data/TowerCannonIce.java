package data;

import java.util.concurrent.CopyOnWriteArrayList;

public class TowerCannonIce extends Tower
{
    public TowerCannonIce(Tile startTile, CopyOnWriteArrayList<Enemy> enemies)
    {
        super(TowerType.CannonIce, startTile, enemies);
    }

    @Override
    public void shoot(Enemy e)
    {
        super.projectiles.add(new ProjectileIceball(super.type.projectileType, super.getTarget(), super.getX(), super.getY(), 16, 16));
        super.target.reduceHiddenHealth(super.type.projectileType.damage);
    }
}
