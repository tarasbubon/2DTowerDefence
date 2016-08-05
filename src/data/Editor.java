package data;

import static helpers.Artist.*;
import static helpers.Leveler.*;

import UI.UI;
import UI.UI.Menu;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

public class Editor
{
    private TileGrid grid;
    private int index;
    private TileType[] types;
    private UI editorUI;
    private Menu tilePickerMenu;
    private Texture menuBackGround;

    public Editor()
    {
        grid = LoadMap("newMap1");
        this.index = 0;
        this.types = new TileType[3];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Dirt;
        this.types[2] = TileType.Water;
        this.menuBackGround = QuickLoad("menuBackgroundEditor");
        setupUI();
    }

    private void setupUI()
    {
        editorUI = new UI();
        editorUI.createMenu("TilePicker",  640, 50, 96, 480, 2, 0);
        tilePickerMenu = editorUI.getMenuByName("TilePicker");
        tilePickerMenu.quickAdd("Grass", "td2dTexGrass64");
        tilePickerMenu.quickAdd("Dirt", "td2dTexDirt64");
        tilePickerMenu.quickAdd("Water", "td2dTexWater64");
    }

    public void update()
    {
        draw();

        //Handle Mouse Input
        if(Mouse.next())
        {
            boolean mouseClicked = Mouse.isButtonDown(0);
            if(mouseClicked)
            {
                if (tilePickerMenu.isButtonClicked("Grass"))
                {
                    index = 0;
                }
                else if (tilePickerMenu.isButtonClicked("Dirt"))
                {
                    index = 1;
                }
                else if (tilePickerMenu.isButtonClicked("Water"))
                {
                    index = 2;
                }
                else
                {
                    setTile();
                }
            }
        }

        //Handle Keyboard Input
        while(Keyboard.next())
        {
            if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState())
            {
               moveIndex();
            }
            if(Keyboard.getEventKey() == Keyboard.KEY_S && Keyboard.getEventKeyState())
            {
                SaveMap("newMap1", grid);
            }
        }
    }

    private void draw()
    {
        DrawQuadTex(menuBackGround, 640, 0, 96, 480);
        grid.draw();
        editorUI.draw();
    }

    private void setTile()
    {
        grid.setTile((int) Math.floor(Mouse.getX() / TILE_SIZE), (int) Math.floor((HEIGHT - Mouse.getY() - 1) / TILE_SIZE), types[index]);
    }

    //Allows editor to change which TileType is selected
    private void moveIndex()
    {
        index++;
        if(index > types.length - 1)
        {
            index = 0;
        }
    }
}
