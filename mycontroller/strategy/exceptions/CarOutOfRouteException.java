package mycontroller.strategy.exceptions;

public class CarOutOfRouteException extends Exception{
    public CarOutOfRouteException(){
        System.out.println("Car current position is not in the route!");
    }
}
