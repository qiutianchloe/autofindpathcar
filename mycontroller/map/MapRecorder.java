package mycontroller.map;

import mycontroller.algorithm.Rect;
import mycontroller.map.exceptions.TileUnknownException;
import tiles.MapTile;
import utilities.Coordinate;
import world.World;

import java.util.*;
import java.util.function.Predicate;
/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: recorder of the map, extends ObserverBase, Using Singleton pattern
 **/

public class MapRecorder extends ObserverBase {

    private Tile[][] map;
    private Rect scope;
    private Set<Coordinate> toExplore;
    private Set<Coordinate> parcels;
    private Set<Coordinate> exits;

    private CarViewAdapter carViewAdapter;

    private static MapRecorder _instance;

    /**static method which gets the instance
     *
     * @return _instance
     */
    public static MapRecorder getInstance() {
        if (_instance == null) {
            _instance = new MapRecorder();
        }
        return _instance;
    }

    private MapRecorder(){
        map = new Tile[World.MAP_WIDTH+1][World.MAP_HEIGHT+1];
        scope = new Rect(0, 0, World.MAP_WIDTH, World.MAP_HEIGHT);
        toExplore = new HashSet<>();
        parcels = new HashSet<>();
        exits = new HashSet<>();

        carViewAdapter = new CarViewAdapter();
    }

    /**make corresponding update in MapRecorder according to new carView
     * set the new CarView to car
     * add the new coorinate to corresponding map
     * remove the parcel which has been collected from parcels
     * remove the coordinate which is at boundary from toExplore
     * @param carView the Map of carView
     */
    public void onReceiveCarView(Map<Coordinate, MapTile> carView){
        //set the new CarView to car
        carViewAdapter.setCarView(carView);
        //add the new coordinate to corresponding map
        for(Tile newTile: carViewAdapter) {
            Coordinate coordinate = newTile.getCoordinate();
            if(!hasKnown(coordinate)) {
                if(newTile.isExit()) exits.add(coordinate);
                if(newTile.isParcel()) parcels.add(coordinate);
                if(carViewAdapter.getScope().atBoundary(coordinate)
                        && newTile.isRoad())
                    toExplore.add(coordinate);
            } else {
                Tile oldTile = getTile(coordinate);
                if(oldTile.isParcel() &&  !newTile.isParcel())
                    parcels.remove(coordinate);
                if(!carViewAdapter.getScope().atBoundary(coordinate))
                    toExplore.remove(coordinate);
            }

            map[coordinate.x][coordinate.y] = newTile;
        }
    }

    /**ckeck whether the coordinate has been recorded
     *
     * @param coordinate the coordinate which is needed to check
     * @return  true if it has been recorded, false if it hasn't
     */
    public boolean hasKnown(Coordinate coordinate) {
        return map[coordinate.x][coordinate.y] != null;
    }

    /**get tile according to coordinate
     *
     * @param coordinate the specific coordinate
     * @return the tile in that coordinate
     * @throws TileUnknownException turn up when the tile doesn't exist(tile==null)
     */
    public Tile getTile(Coordinate coordinate) {
        try{
            Tile tile = map[coordinate.x][coordinate.y];
            if(tile == null) throw new TileUnknownException();
            return tile;
        } catch (TileUnknownException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**get adjacent Tiles of the coordinate
     *
     * @param coordinate 
     * @param predicate
     * @return the list of adjacent tiles
     */
    public List<Tile> getAdjacentTiles(Coordinate coordinate, Predicate<Tile> predicate) {
        List<Tile> adjList = new ArrayList<>(4);
        for (int dx = -1; dx < 2; dx+=2) {
            Coordinate coordinate1 = new Coordinate(coordinate.x + dx, coordinate.y);
            if (scope.contains(coordinate1) && hasKnown(coordinate1)) {
                Tile tile = map[coordinate.x + dx][coordinate.y];
                if(predicate.test(tile)) adjList.add(tile);
            }
        }
        for (int dy = -1; dy < 2; dy+=2) {
            Coordinate coordinate1 = new Coordinate(coordinate.x, coordinate.y + dy);
            if (scope.contains(coordinate1) && hasKnown(coordinate1)) {
                Tile tile = map[coordinate.x][coordinate.y + dy];
                if(predicate.test(tile)) adjList.add(tile);
            }
        }
        return adjList;
    }

    /**get the scope
     *
     * @return this.scope
     */
    public Rect getScope() {return scope;}

    /**get toExplore
     *
     * @return this.toExplore
     */
    public Set<Coordinate> getToExplore() {
        return toExplore;
    }

    /**get parcels
     *
     * @return this.parcels, which is a set of coordinate
     */
    public Set<Coordinate> getParcels() {
        return parcels;
    }

    /**get Exits
     *
     * @return this.exits. which is a set of coordinate
     */
    public Set<Coordinate> getExits() {
        return exits;
    }

}

