/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Roy
 */
public class TileFactory {
    
    public static Tile createTile(int mapIcon) {
        Tile tile;
        switch(mapIcon) {
            case 1:
                tile = new Tile("cell.jpg", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT);
                break;
            default:
                tile = new Tile("cell.jpg", TileEngine.TILE_WIDTH, TileEngine.TILE_HEIGHT);
                break;
        }
        return tile;
    }
}
