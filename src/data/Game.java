package data;

import static helpers.Artist.TILE_SIZE;
import static helpers.Artist.QuickLoad;
import static helpers.Artist.DrawQuadTex;
import UI.UI;
import UI.UI.Menu;
import org.lwjgl.input.Mouse;

public class Game
{
    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;
    private UI gameUI;
    private Menu towerPickerMenu;

    public Game(TileGrid grid)
    {
        this.grid = grid;
        this.waveManager = new WaveManager(new Enemy(QuickLoad("td2dEnmStar64"), grid.getTile(0, 1), grid, TILE_SIZE, TILE_SIZE, 40, 25), 2, 2);
        this.player = new Player(grid, waveManager);
        player.setup();
        setupUI();
    }

    private void setupUI()
    {
        gameUI = new UI();
        gameUI.createMenu("TowerPicker", 640, 50, 96, 480, 2, 0);
        towerPickerMenu = gameUI.getMenuByName("TowerPicker");
        towerPickerMenu.quickAdd("BlueCannon", "cannonBaseBlue");
        towerPickerMenu.quickAdd("IceCannon", "cannonBaseIce");
    }

    private void updateUI()
    {
        gameUI.draw();
        gameUI.drawString(655, 200, "Lives: " + Player.Lives);
        gameUI.drawString(655, 230, "Cash: " + Player.Cash);

        if(Mouse.next())
        {
            boolean mouseClicked = Mouse.isButtonDown(0);
            if(mouseClicked)
            {
                if (towerPickerMenu.isButtonClicked("BlueCannon"))
                {
                    player.pickTower(new TowerCannonBlue(grid.getTile(0, 0), waveManager.getCurrentWave().getEnemyList()));
                }
                if (towerPickerMenu.isButtonClicked("IceCannon"))
                {
                    player.pickTower(new TowerCannonIce(grid.getTile(0, 0), waveManager.getCurrentWave().getEnemyList()));
                }
            }
        }
    }

    public void update()
    {
        DrawQuadTex(QuickLoad("menuBackground2"), 640, 0, 96, 480);
        grid.draw();
        waveManager.update();
        player.update();
        updateUI();
    }
}
