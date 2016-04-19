package data;

public enum TileType
{
    Grass("td2dTexGrass64", true), Dirt("td2dTexDirt64", true), Water("td2dTexWater64", false), NULL("td2dTexWater64", false);

    String textureName;
    boolean buildable;

    TileType(String textureName, boolean buildable)
    {
        this.textureName = textureName;
        this.buildable = buildable;
    }
}
