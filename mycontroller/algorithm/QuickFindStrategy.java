package mycontroller.algorithm;

import utilities.Coordinate;
/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: Interface declare all the function need to be implemented in the QuickFind Class 
 * so that it can check whether there is a path can connect two point directly
 **/


@FunctionalInterface
public interface QuickFindStrategy {
    /**judge whether the coordinate is connected
     *
     * @param coordinate the specific coordinate
     * @return  true if it is connected, false if it is not
     */
    boolean isConnected(Coordinate coordinate);
}
