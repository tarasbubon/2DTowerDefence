package data;

import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static helpers.Artist.*;
import static helpers.Clock.*;


public class TowerCannon
{
    private float x, y, timeSinceLastShot, firingSpeed;
    private int width, height, damage;
    private Texture baseTexture, cannonTexture;
    private Tile startTile;
    private ArrayList<Projectile> projectiles;

    public TowerCannon(Texture baseTexture, Tile startTile, int damage)
    {
        this.baseTexture = baseTexture;
        this.cannonTexture = quickLoad("cannonGun");
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = (int)startTile.getWidth();
        this.height = (int)startTile.getHeight();
        this.damage = damage;
        this.firingSpeed = 30;
        this.timeSinceLastShot = 0;
        this.projectiles = new ArrayList<>();
    }

    private void shoot()
    {
        timeSinceLastShot = 0;
        projectiles.add(new Projectile(quickLoad("projectileOne"), x + 16, y + 16, 5, 10));
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

        draw();
    }

    public void draw()
    {
        drawQuadTex(baseTexture, x, y, width, height);
        drawQuadTexRot(cannonTexture, x, y, width, height, 70);
    }
}
