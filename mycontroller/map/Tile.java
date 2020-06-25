package mycontroller.map;

import tiles.*;
import utilities.Coordinate;
/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: the class for tile,implements Itile Interface 
 **/


public class Tile implements ITile{

    private static final float CAR_DELTA = 0.25f;

    private Coordinate coordinate;
    private MapTile rawTile;

    Tile(Coordinate coordinate, MapTile rawTile) {
        this.coordinate = coordinate;
        this.rawTile = rawTile;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public boolean isParcel() {
        return rawTile instanceof TrapTile;
    }

    @Override
    public boolean isExit() {
        return rawTile.isType(MapTile.Type.FINISH);
    }

    @Override
    public boolean isRoad() {
        return !rawTile.isType(MapTile.Type.WALL);
    }

    @Override
    public boolean isWall() {
        return rawTile.isType(MapTile.Type.WALL);
    }

    @Override
    public float fuelCost() {
        return 1;
    }

    @Override
    public float healthDelta() {
        if(rawTile instanceof TrapTile) {
            switch (((TrapTile)rawTile).getTrap()){
                case "health":
                    return CAR_DELTA * HealthTrap.HealthDelta;
                case "lava":
                    return CAR_DELTA * -LavaTrap.HealthDelta;
                case "water":
                    return WaterTrap.Yield;
                default:
                    return 0;
            }
        }
        return 0;
    }

    @Override
    public boolean canAccelerate() {
        if(rawTile instanceof TrapTile)
            return ((TrapTile)rawTile).canAccelerate();
        return true;
    }

    @Override
    public boolean canTurn() {
        if(rawTile instanceof MudTrap)
            return false;
        if(rawTile instanceof TrapTile)
            return ((TrapTile)rawTile).canTurn();
        return true;
    }

}
