package data;

import static helpers.Artist.TILE_SIZE;
import static helpers.Artist.QuickLoad;
import static helpers.Artist.DrawQuadTex;
import UI.UI;
import UI.UI.Menu;
import helpers.StateManager;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

public class Game
{
    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;
    private UI gameUI;
    private Menu towerPickerMenu;
    private Texture menuBackground;

    public Game(TileGrid grid)
    {
        this.grid = grid;
        this.waveManager = new WaveManager(new Enemy(QuickLoad("td2dEnmStar64"), grid.getTile(0, 1), grid, TILE_SIZE, TILE_SIZE, 40, 25), 2, 2);
        this.player = new Player(grid, waveManager);
        player.setup();
        this.menuBackground = QuickLoad("menuBackground2");
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
        gameUI.drawString(660, 350, "Lives: " + Player.Lives);
        gameUI.drawString(660, 370, "Cash: " + Player.Cash);
        gameUI.drawString(660, 280, "Wave: " + waveManager.getWaveNumber());
        gameUI.drawString(0, 0, StateManager.framesInLastSecond + " fps");

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
        DrawQuadTex(menuBackground, 640, 0, 96, 480);
        grid.draw();
        waveManager.update();
        player.update();
        updateUI();
    }
}
