package mycontroller.strategy;

import mycontroller.strategy.target.*;
/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: Factory class to generate which Target Strategy to use(the factory class in factory pattern)
 **/

/**Factory for Target Strategies
 *
 */
public class TargetStrategyFactory {
    TargetStrategy canExitStrategy;
    TargetStrategy collectParcelStrategy;

    /**construction of TargetStrategyFactory
     * initialize canExitStrategy
     * initialize collectParcelStrategy
     */
    public TargetStrategyFactory() {
        canExitStrategy = new CanExitStrategy();
        collectParcelStrategy = new CollectParcelStrategy();
    }

    /**get corresponding strategy based on whether the the car can exit or not
     *
     * @param canExit whether the the car can exit or not
     * @return  canExitStrategy if the car already can Exit, collectParcelStrategy if the car cannot leave
     */
    public TargetStrategy getStrategy(boolean canExit) {

        if(canExit)
            return canExitStrategy;
        else
            return  collectParcelStrategy;
    }
}
