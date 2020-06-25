package mycontroller.strategy.route;

import javafx.util.Pair;
import mycontroller.algorithm.AstarAlgorithm;
import mycontroller.algorithm.AstarStrategy;
import mycontroller.map.MapRecorder;
import mycontroller.strategy.exceptions.NoRouteFoundException;
import utilities.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: Strategy for No Trap, implements interface class RouteStrategy
 **/

public class NoTrapStrategy implements RouteStrategy{
    /*class for No Trap, implements AstarStrategy
     *
     */
    class NoTrap implements AstarStrategy {
        @Override
        /**get the list of adjacent coordinates
         *
         */
        public List<Coordinate> getAdjacentCoord(Coordinate coordinate) {
            return MapRecorder.getInstance().getAdjacentTiles(coordinate, x -> x.isRoad())
                    .stream().map(x -> x.getCoordinate()).collect(Collectors.toList());
        }

        @Override
        public float getWeight(Coordinate coordinate) {
            return 1;
        }
    }

    @Override
    /**get the list of coordinate which shows the movement route
     * @throws NoRouteFoundException turn up when donot find any available route
     */
    public List<Coordinate> getRoute(Coordinate carPos, List<Pair<Float, Coordinate>> targets) {
        targets.sort((o1, o2) -> o1.getKey() - o2.getKey() < 0 ? -1: 1);
        List<Pair<Float, Coordinate>> firstThree = targets.subList(0, targets.size() >= 3 ? 3 : targets.size());

        List<Pair<Float, List<Coordinate>>> firstThreeToValue = new ArrayList<>(3);
        for(Pair<Float, Coordinate> pair: firstThree) {
            AstarAlgorithm astar = new AstarAlgorithm(carPos, pair.getValue(), new NoTrap());
            AstarAlgorithm.Result result = astar.run();
            if(result.isSuccess()) {
                firstThreeToValue.add(new Pair<>(pair.getKey() * result.getTotalWeight(), result.getRoute()));
            }
        }
        firstThreeToValue.sort((o1, o2) -> o1.getKey() - o2.getKey() < 0 ? -1: 1);

        if(!firstThreeToValue.isEmpty()) return firstThreeToValue.get(0).getValue();

        try {
            throw new NoRouteFoundException();
        } catch (NoRouteFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
