package mycontroller;

import controller.CarController;

import mycontroller.map.MapRecorder;
import mycontroller.map.SubjectBase;
import mycontroller.strategy.Navigation;
import mycontroller.strategy.exceptions.NoRouteFoundException;
import utilities.Coordinate;
import world.Car;
import world.WorldSpatial;
import world.WorldSpatial.Direction;


/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: control the car's auto movement, extends CarController
 **/

public class MyAutoController extends CarController{

	private static final int CAR_MAX_SPEED = 1;

	private Coordinate coordinate;
	private Navigation navigation;
	private CarViewPublisher publisher;

	private boolean start;

	/**construct a MyAutoController object
	 * @param car the car of MyAutoController
	 */
	public MyAutoController(Car car) {
		super(car);

		coordinate = new Coordinate(getPosition());
		navigation = new Navigation(this);

		publisher = new CarViewPublisher();
		publisher.addObserver(MapRecorder.getInstance());
	}

	// Coordinate initialGuess;
	// boolean notSouth = true;
	
	/** function to update the car's position as the game continue
	 */
	@Override
	public void update() {

		publisher.publish();
		coordinate = new Coordinate(getPosition());
		//let the car move at the begining
		if(!start) {
			applyForwardAcceleration();
			start = true;
			return;
		}
		//if car has finished the current navigation's route, update navigation
		if(!navigation.hasNext())
			navigation.navigate();
		moveTowards(navigation.next());

	}

	/**get coordinate of the car
	 *
	 * @return this.coordinate
	 */
	public Coordinate getCoordinate() {
			return this.coordinate;
		}

	/*make the single movement of the car
	 *
	 * @param target the target coordinate
	 */
	private void moveTowards(Coordinate target) {
		//if car arrive target, stop movement
		if(target.equals(coordinate)) {
			applyBrake();
		}
		else {
			//get move direction
			Direction moveDirection=getMoveDirection(target);
			//get car orientation direction
			Direction carDirection=getOrientation();
			//decide how car make next movement according to direction
			if(moveDirection!=null) {
				if(moveDirection.equals(carDirection))
					applyForwardAcceleration();
				else if(moveDirection.equals(WorldSpatial.reverseDirection(carDirection)))
					applyReverseAcceleration();
				else if(moveDirection.equals(WorldSpatial.changeDirection(carDirection, WorldSpatial.RelativeDirection.RIGHT)))
					turnRight();
				else if(moveDirection.equals(WorldSpatial.changeDirection(carDirection, WorldSpatial.RelativeDirection.LEFT)))
					turnLeft();
			}
		}
	}

	/*get the move direction of the car
	 *
	 * @param target the target coordinate
	 * @return the move direction for next movement
	 * @throws NoRouteFoundException turn up if the car cannot arive the target in one step
	 */
	private Direction getMoveDirection(Coordinate target){
		int currentX= coordinate.x;
		int currentY= coordinate.y;
		int targetX=target.x;
		int targetY=target.y;
		if(targetX==currentX&&targetY==currentY) {
			return getOrientation();
		}
		if(targetX==currentX) {
			if(targetY==currentY+1) {
				return Direction.NORTH;
			}
			else if(targetY==currentY-1) {
				return Direction.SOUTH;
			}
		}
		else if(targetY==currentY) {
			if(targetX==currentX+1) {
				return Direction.EAST;
			}
			else if(targetX==currentX-1) {
				return Direction.WEST;
			}
		}
		else {
			try {
				throw new NoRouteFoundException();
			} catch (NoRouteFoundException e) {
				e.printStackTrace();
			}
		}
		return null;

	}
	/*the class to publish car view*/
	class CarViewPublisher extends SubjectBase {
		public void publish() {
				publishCarView(getView());
			}
		}

	}
