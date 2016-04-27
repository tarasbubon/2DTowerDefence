package UI;

import org.lwjgl.input.Mouse;

import java.util.ArrayList;

import static helpers.Artist.*;

public class UI
{
    private ArrayList<Button> buttonList;
    private ArrayList<Menu> menuList;

    public UI()
    {
        buttonList = new ArrayList<>();
        menuList = new ArrayList<>();
    }

    public void addButton(String name, String texture, int x, int y)
    {
        buttonList.add(new Button(name, QuickLoad(texture), x, y));
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

    public void createMenu(String name, int x, int y, int optionsWidth, int optionsHeight)
    {
        menuList.add(new Menu(name, x, y, optionsWidth, optionsHeight));
    }

    public Menu getMenuByName(String name)
    {
        for(Menu m : menuList)
        {
            if(name.equals(m.getName()))
            {
                return m;
            }
        }
        return null;
    }

    public void draw()
    {
        for(Button b : buttonList)
        {
            DrawQuadTex(b.getTexture(), b. getX(), b.getY(), b.getWidth(), b.getHeight());
        }
        for(Menu m : menuList)
        {
            m.draw();
        }
    }

    public class Menu
    {
        String name;
        private ArrayList<Button> menuButtons;
        private int x, y, buttonAmount, optionsWidth, optionsHeight;

        public Menu(String name, int x, int y, int optionsWidth, int optionsHeight)
        {
            this.name = name;
            this.x = x;
            this.y = y;
            this.buttonAmount = 0;
            this.optionsWidth = optionsWidth;
            this.optionsHeight = optionsHeight;
            this.menuButtons = new ArrayList<>();
        }

        public void addButton(Button b)
        {
            if(optionsWidth != 0)
            {
                b.setY(y + (buttonAmount / optionsWidth) * TILE_SIZE);
            }
            b.setX(x + (buttonAmount % optionsWidth) * TILE_SIZE);
            buttonAmount++;
            menuButtons.add(b);
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
            for(Button b : menuButtons)
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
            for(Button b :menuButtons)
            {
                DrawQuadTex(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
            }
        }

        public String getName()
        {
            return name;
        }
    }
}
