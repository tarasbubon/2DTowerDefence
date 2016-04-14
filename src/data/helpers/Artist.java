package data.helpers;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class Artist
{
    public static final int WIDTH = 600, HEIGHT = 400;

    public static void BeginSesion()
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
        glOrtho(0, 600, 400, 0, 1, -1);
        //Applies subsequent matrix operations to the model view matrix stack.
        glMatrixMode(GL_MODELVIEW);
    }

    public static void DrawQuad(float x, float y, float width, float height)
    {
        glBegin(GL_QUADS);
        glVertex2f(x, y); //Top left corner
        glVertex2f(x + width, y); //Top right corner
        glVertex2f(x + width, y + height); //Bottom right corner
        glVertex2f(x, y + height); //Bottom left corner
        glEnd();
    }
}
