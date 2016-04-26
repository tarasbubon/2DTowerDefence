package data;

import helpers.Clock;
import helpers.StateManager;
import org.lwjgl.opengl.Display;

import static helpers.Artist.*;

public class Boot
{
    public Boot()
    {
        //Call static method in Artist class to initialize OpenGL calls
        BeginSession();

        //Main Game Loop
        while(!Display.isCloseRequested())
        {
            Clock.update();
            StateManager.update();
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
    }

    public static void main(String[] args)
    {
        new Boot();
    }
}
