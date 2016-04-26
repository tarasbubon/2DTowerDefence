package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.QuickLoad;

public enum ProjectileType
{
    CannonBall(QuickLoad("projectileOne"), 10, 500),
    Iceball(QuickLoad("projectileIceball"), 6, 450);

    Texture texture;
    int damage;
    float speed;

    ProjectileType(Texture texture, int damage, float speed)
    {
        this.texture = texture;
        this.damage = damage;
        this.speed = speed;
    }
}
