package data;

import helpers.StateManager;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import UI.UI;

import static helpers.Artist.*;

public class MainMenu
{
    private Texture background;
    private UI menuUI;

    public MainMenu()
    {
        background = QuickLoad("MainMenu");
        menuUI = new UI();
        menuUI.addButton("Play", "PlayButton", WIDTH / 2 - 64, (int)(HEIGHT * 0.45f));
        menuUI.addButton("Editor", "EditorButton", WIDTH / 2 - 64, (int)(HEIGHT * 0.55f));
        menuUI.addButton("Quit", "QuitButton", WIDTH / 2 - 64, (int)(HEIGHT * 0.65f));
    }

    //Check if button is clicked by the user, and if so do an action
    private void updateButtons()
    {
        if(Mouse.isButtonDown(0))
        {
            if(menuUI.isButtonClicked("Play"))
            {
                StateManager.setState(StateManager.GameState.GAME);
            }
            if(menuUI.isButtonClicked("Editor"))
            {
                StateManager.setState(StateManager.GameState.EDITOR);
            }
            if(menuUI.isButtonClicked("Quit"))
            {
                System.exit(0);
            }
        }
    }

    public void update()
    {
        DrawQuadTex(background, 0, 0, 1024, 512);
        menuUI.draw();
        updateButtons();
    }
}
