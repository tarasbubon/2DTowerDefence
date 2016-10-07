package data;

import java.util.concurrent.CopyOnWriteArrayList;

public class TowerCannonBlue extends Tower
{
    public TowerCannonBlue(Tile startTile, CopyOnWriteArrayList<Enemy> enemies)
    {
        super(TowerType.CannonBlue, startTile, enemies);
    }

    @Override
    public void shoot(Enemy e)
    {
        super.projectiles.add(new ProjectileCannonball(super.type.projectileType, super.getTarget(), super.getX(), super.getY(), 16, 16));
        super.target.reduceHiddenHealth(super.type.projectileType.damage);
    }
}
