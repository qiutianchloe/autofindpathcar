package mycontroller.map;

import mycontroller.algorithm.Rect;
import utilities.Coordinate;
/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: the adapter for Tile Chunk (the Adapter class of adapter pattern)
 **/

public interface TileChunkAdapter extends Iterable<Tile>{
    /**get scope
     *
     * @return this.scope
     */
    Rect getScope();
    /**get Tile by coordinate
     *
     */
    Tile getTile(Coordinate coordinate);
}
