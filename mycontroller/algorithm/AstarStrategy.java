package mycontroller.algorithm;

import utilities.Coordinate;

import java.util.List;

/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: Interface AstarStrategy declare which function should Astar algorithm implement 
 **/

public interface AstarStrategy {
    /**get the adjacent coordinate list of the input coordinate
     *
     * @param coordinate the specific coordinate
     * @return  the list of adjacent coordinate
     */
    List<Coordinate> getAdjacentCoord(Coordinate coordinate);

    /**get the weight of coordinate
     * @param coordinate the specific coordinate
     * @return the weight of coordinate
     */
    float getWeight(Coordinate coordinate);
}
