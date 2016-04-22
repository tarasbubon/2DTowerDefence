package data;

import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static helpers.Artist.*;
import static helpers.Clock.*;


public class TowerCannon
{
    private float x, y, timeSinceLastShot, firingSpeed, angle;
    private int width, height, damage;
    private Texture baseTexture, cannonTexture;
    private Tile startTile;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Enemy> enemies;
    private Enemy target;

    public TowerCannon(Texture baseTexture, Tile startTile, int damage, ArrayList<Enemy> enemies)
    {
        this.baseTexture = baseTexture;
        this.cannonTexture = quickLoad("cannonGun");
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = (int)startTile.getWidth();
        this.height = (int)startTile.getHeight();
        this.damage = damage;
        this.firingSpeed = 3;
        this.timeSinceLastShot = 0;
        this.projectiles = new ArrayList<>();
        this.enemies = enemies;
        this.target = acquireTarget();
        this.angle = calculateAngle();
    }

    private Enemy acquireTarget()
    {
        return enemies.get(0);
    }

    private float calculateAngle()
    {
        double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
        return (float)Math.toDegrees(angleTemp) - 90;
    }

    private void shoot()
    {
        timeSinceLastShot = 0;
        projectiles.add(new Projectile(quickLoad("projectileOne"),
                                       target,
                                       x + Game.TILE_SIZE / 2 - Game.TILE_SIZE / 4,
                                       y + Game.TILE_SIZE / 2 - Game.TILE_SIZE / 4,
                                       900,
                                       10));
    }

    public void update()
    {
        timeSinceLastShot += delta();
        if(timeSinceLastShot > firingSpeed)
        {
            shoot();
        }

        for(Projectile p : projectiles)
        {
            p.update();
        }

        angle = calculateAngle();
        draw();
    }

    public void draw()
    {
        drawQuadTex(baseTexture, x, y, width, height);
        drawQuadTexRot(cannonTexture, x, y, width, height, angle);
    }
}
