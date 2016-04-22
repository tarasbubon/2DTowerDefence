package UI;

import org.lwjgl.input.Mouse;

import java.util.ArrayList;

import static helpers.Artist.*;

public class UI
{
    private ArrayList<Button> buttonList;

    public UI()
    {
        buttonList = new ArrayList<>();
    }

    public void addButton(String name, String texture, int x, int y)
    {
        buttonList.add(new Button(name, quickLoad(texture), x, y));
    }

    public boolean isButtonClicked(String buttonName)
    {
        Button b = getButtonByName(buttonName);
        float mouseY = HEIGHT - Mouse.getY() - 1;
        if(Mouse.getX() > b.getX() &&
           Mouse.getX() < b.getX() + b.getWidth() &&
           mouseY > b.getY() &&
           mouseY < b.getY() + b.getHeight())
        {
            return true;
        }
        return false;
    }

    private Button getButtonByName(String buttonName)
    {
        for(Button b : buttonList)
        {
            if(b.getName().equals(buttonName))
            {
                return b;
            }
        }
        return null;
    }

    public void draw()
    {
        for(Button b : buttonList)
        {
            drawQuadTex(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
        }
    }
}
