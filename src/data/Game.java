package data;

import static helpers.Artist.TILE_SIZE;
import static helpers.Artist.QuickLoad;
import static helpers.Artist.DrawQuadTex;
import UI.UI;
import UI.Button;
import org.lwjgl.input.Mouse;

public class Game
{
    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;
    private UI towerPickerUI;

    public Game(int[][] map)
    {
        grid = new TileGrid(map);
        waveManager = new WaveManager(new Enemy(QuickLoad("td2dEnmUFO64"), grid.getTile(10, 8), grid, TILE_SIZE, TILE_SIZE, 40, 25), 2, 2);
        player = new Player(grid, waveManager);
        player.setup();
        setupUI();
    }

    private void setupUI()
    {
        towerPickerUI = new UI();
        //towerPickerUI.addButton("CannonBlue", "cannonBaseBlue", 0, 0);
        //towerPickerUI.addButton("CannonIce", "cannonBaseIce", TILE_SIZE, 0);
        towerPickerUI.createMenu("TowerPicker", 656, 0, 2, 0);
        towerPickerUI.getMenuByName("TowerPicker").addButton(new Button("CannonBlue", QuickLoad("cannonBaseBlue"), 0, 0));
        towerPickerUI.getMenuByName("TowerPicker").addButton(new Button("CannonIce", QuickLoad("cannonBaseIce"), 0, 0));
        towerPickerUI.getMenuByName("TowerPicker").addButton(new Button("CannonIce", QuickLoad("cannonBaseIce"), 0, 0));
        towerPickerUI.getMenuByName("TowerPicker").addButton(new Button("CannonIce", QuickLoad("cannonBaseIce"), 0, 0));
        towerPickerUI.getMenuByName("TowerPicker").addButton(new Button("CannonIce", QuickLoad("cannonBaseIce"), 0, 0));
    }

    private void updateUI()
    {
        towerPickerUI.draw();

        if(Mouse.next())
        {
            boolean mouseClicked = Mouse.isButtonDown(0);
            if(mouseClicked)
            {
                if (towerPickerUI.getMenuByName("TowerPicker").isButtonClicked("CannonBlue"))
                {
                    player.pickTower(new TowerCannonBlue(grid.getTile(0, 0), waveManager.getCurrentWave().getEnemyList()));
                }
                if (towerPickerUI.getMenuByName("TowerPicker").isButtonClicked("CannonIce"))
                {
                    player.pickTower(new TowerCannonIce(grid.getTile(0, 0), waveManager.getCurrentWave().getEnemyList()));
                }
            }
        }
    }

    public void update()
    {
        DrawQuadTex(QuickLoad("menuBackground"), 640, 0, 96, 480);
        grid.draw();
        waveManager.update();
        player.update();
        updateUI();
    }
}
