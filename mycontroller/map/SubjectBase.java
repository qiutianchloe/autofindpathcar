package mycontroller.map;

import tiles.MapTile;
import utilities.Coordinate;

import java.util.ArrayList;
import java.util.Map;
/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: the abstract class that collect all the observers (Subject class in the Observer Pattern)
 **/

public abstract class SubjectBase {
    private ArrayList<ObserverBase> observers = new ArrayList<>();

    /**add observer into obervers
     *
     * @param observer  the observer which is needed to be added
     */
    public void addObserver(ObserverBase observer) {
        observers.add(observer);
    }

    /**reomove observer from observers
     *
     * @param observer  the oberver which is needed to be removed
     */
    public void removeObserver(ObserverBase observer) {
        observers.remove(observer);
    }

    /**publish the Car View by every observer in this.observers
     *
     * @param carView the Map of carView
     */
    public void publishCarView(Map<Coordinate, MapTile> carView) {
        for(ObserverBase observer: observers) {
            observer.onReceiveCarView(carView);
        }
    }
}
