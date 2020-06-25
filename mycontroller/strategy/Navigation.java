package mycontroller.strategy;

import javafx.util.Pair;
import mycontroller.algorithm.QuickFind;
import mycontroller.MyAutoController;
import mycontroller.map.MapRecorder;
import mycontroller.strategy.exceptions.CarOutOfRouteException;
import mycontroller.strategy.route.NoTrapStrategy;
import mycontroller.strategy.route.RouteStrategy;
import mycontroller.strategy.target.TargetStrategy;
import utilities.Coordinate;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: class decide the target and  movement route
 **/

public class Navigation {
    private List<Coordinate> route;
    private int curPos;

    MyAutoController carController;
    TargetStrategyFactory targetFactory;
    RouteStrategy routeStrategy;

    /**construction of Navigation
     * initialize targetFactory
     * initialize routeStrategy
     * @param carController carController of Navigation
     */
    public Navigation(MyAutoController carController) {
        this.carController = carController;
        targetFactory = new TargetStrategyFactory();
        routeStrategy = new NoTrapStrategy();
    }

    /**update the route
     * Firstly,use targetStrategy to get the target list based on car coordinate
     * Secondly,use filterNotConnected to get the reachable Target list based on car coordinate
     * Thirdly, use routeStrategy to get the new movement Route
     * Last,update the route and change the curPos to 0
     */
    public void navigate() {

        List<Coordinate> newRoute;

        Coordinate carPos = carController.getCoordinate();

        TargetStrategy targetStrategy =
                targetFactory.getStrategy(carController.numParcels() == carController.numParcelsFound());
        List<Pair<Float, Coordinate>> targets = targetStrategy.getTargetList(carPos);

        List<Pair<Float, Coordinate>> filteredTarget = filterNotConnected(carPos, targets);

        newRoute = routeStrategy.getRoute(carPos, filteredTarget);

        curPos = 0;
        route = newRoute;
    }

    /**check whether there is a next coordinate
     *
     * @return true if there is a next coordinate in the route , false if there isn't
     */
    public boolean hasNext() {
        return route != null && curPos < route.size() - 1;
    }

    /** get the next movement's coordinate
     *
      * @return the coordinate for mext movement
     * @throws CarOutOfRouteException turn up when car is not in the current route list
     */
    public Coordinate next(){
        Coordinate carPos = carController.getCoordinate();
        if(!route.contains(carPos)) try {
            throw new CarOutOfRouteException();
        } catch (CarOutOfRouteException e) {
            e.printStackTrace();
        }

        if(carPos.equals(route.get(curPos))) {
            curPos++;
        }
        return route.get(curPos);
    }

    /*delete all unreachable target in the target list
     *
     * @param carPos car coordinate
     * @param targets list of target
     * @return the list of all reachable target
     */
    private  List<Pair<Float, Coordinate>> filterNotConnected(Coordinate carPos, List<Pair<Float, Coordinate>> targets) {
        MapRecorder recorder =  MapRecorder.getInstance();

        QuickFind quickFind = new QuickFind(MapRecorder.getInstance().getScope(), x ->  recorder.hasKnown(x) && recorder.getTile(x).isRoad());
        QuickFind.Result result = quickFind.run();

        int connected = result.getComponentMark(carPos);

        return targets.stream().filter(x -> result.getComponentMark(x.getValue()) == connected).collect(Collectors.toList());
    }
}
