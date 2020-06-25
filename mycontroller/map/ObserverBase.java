package mycontroller.map;

import tiles.MapTile;
import utilities.Coordinate;

import java.util.Map;
/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: the Base abstract class for all the observers
 **/

public abstract class ObserverBase {
    /**make corresponding change according to new carView
     *
     * @param carView the Map of carView
     */
    public abstract void onReceiveCarView(Map<Coordinate, MapTile> carView);
}
