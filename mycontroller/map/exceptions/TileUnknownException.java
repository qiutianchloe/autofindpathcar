package mycontroller.map.exceptions;

public class TileUnknownException extends Exception {
    public TileUnknownException() {
        System.out.println("Tile is unExplored!");
    }
}
