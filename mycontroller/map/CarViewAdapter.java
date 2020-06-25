package mycontroller.map;

import mycontroller.algorithm.Rect;
import mycontroller.map.exceptions.TileOutOfRangeException;
import tiles.MapTile;
import utilities.Coordinate;
import world.World;

import java.util.Iterator;
import java.util.Map;

/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: Adapter for CarView,implements TileChunkAdapter
 **/

public class CarViewAdapter implements TileChunkAdapter {
    private Rect scope;
    private Map<Coordinate, MapTile> carView;

    /**update the carView map and scope
     *
     * @param carView the Map of tiles which can be viewed by car
     */
    public void setCarView(Map<Coordinate, MapTile> carView) {
        int minX =  World.MAP_WIDTH, maxX = 0, minY = World.MAP_HEIGHT, maxY = 0;
        Iterator<Map.Entry<Coordinate, MapTile>> iter = carView.entrySet().iterator();
        //get the new boundary of carview
        while (iter.hasNext()) {
            Map.Entry<Coordinate, MapTile> entry = iter.next();
            if(!entry.getValue().isType(MapTile.Type.EMPTY)) {
                Coordinate coordinate = entry.getKey();
                if(coordinate.x < minX) minX = coordinate.x;
                if(coordinate.x > maxX) maxX = coordinate.x;
                if(coordinate.y < minY) minY = coordinate.y;
                if(coordinate.y > maxY) maxY = coordinate.y;
            } else {
                iter.remove();
            }
        }
        //update the Rect and carView
        this.scope = new Rect(minX, minY, maxX, maxY);
        this.carView = carView;
    }
    
    @Override
    public Rect getScope() {
        return scope;
    }

    @Override
    /**get Tile by coordinate
     * @throws TileOutOfRangeException turn up when the coordinate is out of range
     */
    public Tile getTile(Coordinate coordinate) {
        try{
            if(!getScope().contains(coordinate)) throw new TileOutOfRangeException();
        } catch (TileOutOfRangeException e) {
            e.printStackTrace();
        }
        return  new Tile(coordinate, carView.get(coordinate));
    }

    /*class for tile Iterator*/
    class TileIterator implements Iterator<Tile> {
        Iterator<Map.Entry<Coordinate, MapTile>> iter;

        TileIterator() {
            iter = carView.entrySet().iterator();
        }

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public Tile next() {
            Map.Entry<Coordinate, MapTile> entry = iter.next();
            return new Tile(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Iterator<Tile> iterator() {
        return new TileIterator();
    }
}
