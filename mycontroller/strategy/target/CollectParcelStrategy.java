package mycontroller.strategy.target;

import javafx.util.Pair;
import utilities.Coordinate;

import java.util.List;
/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: strategy for car to collect parecl, implements TargetStrategy
 **/

public class CollectParcelStrategy implements TargetStrategy {
    TargetStrategy parcelStrategy;
    TargetStrategy exploreStrategy;

    /**construction of CollectParcelStrategy()
     *
     */
    public CollectParcelStrategy() {
        parcelStrategy = new ParcelStrategy();
        exploreStrategy = new ExploreStrategy();
    }

    @Override
    /**get the target list which contains parcels and exploreStrategy's targets acooording to car coordinate
     *
     */
    public List<Pair<Float, Coordinate>> getTargetList(Coordinate carPos) {
        List<Pair<Float, Coordinate>> result = parcelStrategy.getTargetList(carPos);
        result.addAll(exploreStrategy.getTargetList(carPos));
        return result;
    }
}
