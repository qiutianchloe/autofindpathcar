package mycontroller.strategy.route;

import javafx.util.Pair;
import utilities.Coordinate;

import java.util.List;

/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: Interface RouteStrategy, declare the functions that need to find a route
 **/

public interface RouteStrategy {
    /**get the list of coordinate which shows the movement route
     *
     * @param carPos car coordinate
     * @param targets  the targets and their priority to visit
     * @return  the list of coordinates, which shows the movement get the list of coordinate which shows the planing movement route
     */
    List<Coordinate> getRoute(Coordinate carPos, List<Pair<Float, Coordinate>> targets);
}
