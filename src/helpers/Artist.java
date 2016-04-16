package helpers;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

import static org.lwjgl.opengl.GL11.*;

public class Artist
{
    public static final int WIDTH = 1024, HEIGHT = 768;

    public static void beginSession()
    {
        Display.setTitle("2D Tower Defense");
        try
        {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create();
        }
        catch (LWJGLException e)
        {
            e.printStackTrace();
        }

        //Applies subsequent matrix operations to the projection matrix stack.
        glMatrixMode(GL_PROJECTION);
        //glLoadIdentity replaces the current matrix with the identity matrix. It is semantically equivalent to calling glLoadMatrix with the identity matrix
        //in some cases it is more efficient.
        glLoadIdentity();
        //Multiply the current matrix with an orthographic matrix
        //Setup our camera
        glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
        //Applies subsequent matrix operations to the model view matrix stack.
        glMatrixMode(GL_MODELVIEW);
        //If enabled and no fragment shader is active, two-dimensional texturing is performed
        glEnable(GL_TEXTURE_2D);
    }

    public static void drawQuad(float x, float y, float width, float height)
    {
        glBegin(GL_QUADS);
        glVertex2f(x, y); //Top left corner
        glVertex2f(x + width, y); //Top right corner
        glVertex2f(x + width, y + height); //Bottom right corner
        glVertex2f(x, y + height); //Bottom left corner
        glEnd();
    }

    public static void drawQuadTex(Texture texture, float x, float y, float width, float height)
    {
        texture.bind();
        //Multiply the current matrix by a translation matrix
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(0, 0);
        glTexCoord2f(1, 0);
        glVertex2f(width, 0);
        glTexCoord2f(1, 1);
        glVertex2f(width, height);
        glTexCoord2f(0, 1);
        glVertex2f(0, height);
        glEnd();
        glLoadIdentity();
    }

    public static Texture loadTexture(String path, String fileType)
    {
        Texture texture = null;
        InputStream in = ResourceLoader.getResourceAsStream(path);
        try
        {
            texture = TextureLoader.getTexture(fileType, in);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return texture;
    }

    public static Texture quickLoad(String name)
    {
        Texture texture = null;
        texture = loadTexture("res/" + name + ".png", "PNG");
        return texture;
    }
}
