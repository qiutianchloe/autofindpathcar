package mycontroller.strategy.target;

import javafx.util.Pair;
import utilities.Coordinate;

import java.util.List;


/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: Interface of target strategy. declare the functions to find next tile to go 
 **/

public interface TargetStrategy {
    /**
     * @param carPos the current car position
     * @return the targets and their priority to visit
     */
    List<Pair<Float, Coordinate>> getTargetList(Coordinate carPos);
}
