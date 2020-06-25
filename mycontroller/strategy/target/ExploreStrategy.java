package mycontroller.strategy.target;

import javafx.util.Pair;
import mycontroller.map.MapRecorder;
import utilities.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: Strategy for Explore the parcel or exit, implements TargetStrategy
 **/

public class ExploreStrategy implements TargetStrategy {
    private static final float FACTOR = 1.0f;

    @Override
    /**get target list which contains explore targets
     *
     */
    public List<Pair<Float, Coordinate>> getTargetList(Coordinate carPos) {

        List<Pair<Float, Coordinate>> result = new ArrayList<>();
        for(Coordinate coordinate: MapRecorder.getInstance().getToExplore()) {
            float value =  FACTOR * (Math.abs(coordinate.x - carPos.x) + Math.abs(coordinate.y- carPos.y));
            result.add(new Pair<>(value, coordinate));
        }
        return result;
    }
}
