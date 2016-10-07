package data;

import java.util.concurrent.CopyOnWriteArrayList;

public class TowerCannonRed extends Tower
{
    public TowerCannonRed(Tile startTile, CopyOnWriteArrayList<Enemy> enemies)
    {
        super(TowerType.CannonRed, startTile, enemies);
    }

    @Override
    public void shoot(Enemy e)
    {
        super.projectiles.add(new ProjectileCannonball(super.type.projectileType, super.getTarget(), super.getX(), super.getY(), 16, 16));
        super.target.reduceHiddenHealth(super.type.projectileType.damage);
    }
}
