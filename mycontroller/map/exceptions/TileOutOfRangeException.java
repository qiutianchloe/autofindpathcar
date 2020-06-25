package mycontroller.map.exceptions;

public class TileOutOfRangeException extends Exception {
    public TileOutOfRangeException() {
        System.out.println("Tile is out of range!");
    }
}
