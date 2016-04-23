package helpers;

import data.Tile;
import data.TileGrid;
import data.TileType;

import java.io.*;

public class Leveler
{
    public static void saveMap(String mapName, TileGrid grid)
    {
        String mapData = "";
        for(int i = 0; i < grid.getTileWide(); i++)
        {
            for(int j = 0; j < grid.getTileHigh(); j++)
            {
                mapData += getTileIdByTileType(grid.getTile(i, j));
            }
        }

        try
        {
            File file = new File(mapName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(mapData);
            bw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static TileGrid loadMap(String mapName)
    {
        TileGrid grid = new TileGrid();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(mapName));
            String data = br.readLine();
            for(int i = 0; i < grid.getTileWide(); i++)
            {
                for(int j = 0; j < grid.getTileHigh(); j++)
                {
                    grid.setTile(i, j, getTileTypeById(data.substring(i * grid.getTileHigh() + j, i * grid.getTileHigh() + j + 1)));
                }
            }
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return grid;
    }

    public static TileType getTileTypeById(String id)
    {
        TileType type = TileType.NULL;
        switch(id)
        {
            case "0":
                type = TileType.Grass;
                break;
            case "1":
                type = TileType.Dirt;
                break;
            case "2":
                type = TileType.Water;
                break;
            case "3":
                type = TileType.NULL;
                break;
        }
        return type;
    }

    public static String getTileIdByTileType(Tile t)
    {
        String ID = "E";
        switch(t.getType())
        {
            case Grass:
                ID = "0";
                break;
            case Dirt:
                ID = "1";
                break;
            case Water:
                ID = "2";
                break;
            case NULL:
                ID = "3";
                break;
        }
        return ID;
    }

}
