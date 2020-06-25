package mycontroller.strategy.target;

import javafx.util.Pair;
import utilities.Coordinate;

import java.util.List;
/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: Strategy for the situation when car pick enough parcel and can exit at that point, implements TargetStrategy.
 **/

public class CanExitStrategy implements TargetStrategy {
    TargetStrategy exitStrategy;
    TargetStrategy exploreStrategy;

    /**construction of CanExitStrategy
     *
     */
    public CanExitStrategy() {
        exitStrategy = new ExitStrategy();
        exploreStrategy = new ExploreStrategy();
    }

    @Override
    /**get the target list which contains exits and exploreStrategy's targets acooording to car coordinate
     *
     */
    public List<Pair<Float, Coordinate>> getTargetList(Coordinate carPos) {
        List<Pair<Float, Coordinate>> result = exitStrategy.getTargetList(carPos);
        result.addAll(exploreStrategy.getTargetList(carPos));
        return result;
    }
}
