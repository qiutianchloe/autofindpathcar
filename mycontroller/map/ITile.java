package mycontroller.map;

import utilities.Coordinate;


/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: Interface of Tile, declare the functions that being used for the tiles
 **/

public interface ITile {
    /**
     * @return the position of the tile
     */
    Coordinate getCoordinate();
    /**judge whether the tile is parcel
     *
     * @return  true if it is, false if it isn't
     */
    boolean isParcel();
    /**judge whether the tile is Exit
     *
     * @return  true if it is, false if it isn't
     */
    boolean isExit();
    /**judge whether the tile is Road
     *
     * @return  true if it is, false if it isn't
     */
    boolean isRoad();

    /**judge whether the tile is wall
     *
     * @return  true if it is, false if it isn't
     */
    boolean isWall();

    /**get fuel cost according to different tile type
     * @return a float number which is fule cost
     */
    float fuelCost();

    /**get health change according to different tile type
     * @return  a float number which is health change
     */
    float healthDelta();

    /**check whether the car can accelerate when in that tile
     *
     * @return true if it can sccelerate, false if it cannot
     */
    boolean canAccelerate();

    /**check whether the car can turn when in that tile
     *
     * @return true if it can turn, false if it can't
     */
    boolean canTurn();
}
