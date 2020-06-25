package mycontroller.strategy.exceptions;

public class NoRouteFoundException extends Exception {
    public NoRouteFoundException(){
        System.out.println("Can not find any route, stuck!");
    }
}
