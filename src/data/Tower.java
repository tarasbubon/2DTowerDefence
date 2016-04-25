package data;

import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static helpers.Artist.*;
import static helpers.Clock.delta;

public abstract class Tower implements Entity
{
    private float x, y, timeSinceLastShot, firingSpeed, angle;
    private int width, height, damage, range;
    private Enemy target;
    private Texture[] textures;
    private ArrayList<Enemy> enemies;
    private ArrayList<Projectile> projectiles;
    private boolean targeted;

    public Tower(TowerType type, Tile startTile, ArrayList<Enemy> enemies)
    {
        this.textures = type.textures;
        this.damage = type.damage;
        this.range = type.range;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = startTile.getWidth();
        this.height = startTile.getHeight();
        this.enemies = enemies;
        this.targeted = false;
        this.timeSinceLastShot = 0f;
        this.projectiles = new ArrayList<>();
        this.firingSpeed = type.firingSpeed;
        this.angle = 0f;
    }

    private Enemy acquireTarget()
    {
        Enemy closest = null;
        float closestDistance = 10000;
        for(Enemy e : enemies)
        {
            if(isInRange(e) && findDistance(e) < closestDistance)
            {
                closestDistance = findDistance(e);
                closest = e;
            }
        }
        if(closest != null)
        {
            targeted = true;
        }
        return closest;
    }

    private boolean isInRange(Enemy e)
    {
        float xDistance = Math.abs(e.getX() - x);
        float yDistance = Math.abs(e.getY() - y);
        if(xDistance < range && yDistance < range)
        {
            return true;
        }
        return false;
    }

    private float findDistance(Enemy e)
    {
        float xDistance = Math.abs(e.getX() - x);
        float yDistance = Math.abs(e.getY() - y);
        return xDistance + yDistance;
    }

    private float calculateAngle()
    {
        double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
        return (float)Math.toDegrees(angleTemp) - 90;
    }

    public void shoot()
    {
        timeSinceLastShot = 0;
        projectiles.add(new ProjectileIceball(QuickLoad("projectileIceball"),
                target,
                x + TILE_SIZE / 2 - TILE_SIZE / 4,
                y + TILE_SIZE / 2 - TILE_SIZE / 4,
                16,
                16,
                600,
                10));
    }

    public void updateEnemyList(ArrayList<Enemy> newList)
    {
        enemies = newList;
    }

    public void update()
    {
        if(!targeted)
        {
            target = acquireTarget();
        }

        if(target == null || !target.isAlive() )
        {
            targeted = false;
        }

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
        DrawQuadTex(textures[0], x, y, width, height);
        if(textures.length > 1)
        {
            for (int i = 1; i < textures.length; i++)
            {
                DrawQuadTexRot(textures[i], x, y, width, height, angle);
            }
        }
    }

    //GETTERS AND SETTERS
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Enemy getTarget()
    {
        return target;
    }
}
