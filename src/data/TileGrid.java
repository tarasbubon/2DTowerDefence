package data;

public class TileGrid
{
    public Tile[][] map;
    private int tileWide, tileHigh;

    public TileGrid()
    {
        this.tileWide = 20;
        this.tileHigh = 15;
        map = new Tile[20][15];
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map[i].length; j++)
            {
                map[i][j] = new Tile(i * 32, j * 32, 32, 32, TileType.Grass);
            }
        }
    }

    public TileGrid(int[][] newMap)
    {
        this.tileWide = newMap[0].length;
        this.tileHigh = newMap.length;
        map = new Tile[tileWide][tileHigh];
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map[i].length; j++)
            {
                switch(newMap[j][i])
                {
                    case 0:
                        map[i][j] = new Tile(i * 32, j * 32, 32, 32, TileType.Grass);
                        break;
                    case 1:
                        map[i][j] = new Tile(i * 32, j * 32, 32, 32, TileType.Dirt);
                        break;
                    case 2:
                        map[i][j] = new Tile(i * 32, j * 32, 32, 32, TileType.Water);
                        break;
                }
            }
        }
    }

    public void setTile(int xCoord, int yCoord,TileType type)
    {
        map[xCoord][yCoord] = new Tile(xCoord * 32, yCoord * 32, 32, 32, type);
    }

    public Tile getTile(int xPlace, int yPlace)
    {
        if(xPlace < tileWide && yPlace < tileHigh && xPlace > -1 && yPlace > -1)
        {
            return map[xPlace][yPlace];
        }
        else
        {
            return new Tile(0, 0, 0, 0, TileType.NULL);
        }
    }

    public void draw()
    {
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map[i].length; j++)
            {
                map[i][j].draw();
            }
        }
    }

    //GETTERS AND SETTERS
    public int getTileWide() {
        return tileWide;
    }

    public void setTileWide(int tileWide) {
        this.tileWide = tileWide;
    }

    public int getTileHigh() {
        return tileHigh;
    }

    public void setTileHigh(int tileHigh) {
        this.tileHigh = tileHigh;
    }
}
