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
 * description:strategy for car to go to Exit tiles, implements TargetStrategy
 **/

public class ExitStrategy implements TargetStrategy {

    @Override
    /**get target list which contains exits
     *
     */
    public List<Pair<Float, Coordinate>> getTargetList(Coordinate carPos) {
        List<Pair<Float, Coordinate>> result = new ArrayList<>();
        for(Coordinate coordinate: MapRecorder.getInstance().getExits()) {
            float value =  0;
            result.add(new Pair<>(value, coordinate));
        }
        return result;
    }
}
