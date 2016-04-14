package data;

import org.lwjgl.opengl.Display;

import static data.helpers.Artist.*;

public class Boot
{
    public Boot()
    {
        BeginSesion();

        while(!Display.isCloseRequested())
        {
            DrawQuad(50, 50, 100, 100);
            DrawQuad(150, 150, 100, 100);

            Display.update();
            Display.sync(600);
        }

        Display.destroy();
    }

    public static void main(String[] args)
    {
        new Boot();
    }
}
