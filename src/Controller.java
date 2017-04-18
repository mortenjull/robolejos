import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Controller {
private final EV3 brick;
	
	private final RegulatedMotor leftMotor;
	private final RegulatedMotor rightMotor;
	private final RegulatedMotor smallMotor;
	
	private final EV3UltrasonicSensor ultraSonic;
	private final EV3TouchSensor backTouch;
	private final EV3TouchSensor frontTouch;
	
	private final MovePilot pilot;
	private final Chassis chassis;
	
	public Controller() {
		brick = (EV3) BrickFinder.getDefault();
		
		leftMotor = new EV3LargeRegulatedMotor(brick.getPort(("D")));
		rightMotor = new EV3LargeRegulatedMotor(brick.getPort(("B")));
		smallMotor = new EV3MediumRegulatedMotor(brick.getPort(("C")));
		
		ultraSonic = new EV3UltrasonicSensor(brick.getPort(("S1")));
		backTouch = new EV3TouchSensor(brick.getPort(("S3")));
		frontTouch = new EV3TouchSensor(brick.getPort(("S2")));
		
		double wheelDiameter = 53;
		double robotWidth = 120;
		
		Wheel wheelLeft = WheeledChassis.modelWheel(leftMotor, wheelDiameter).offset((-robotWidth) / 2);
		Wheel wheelRight = WheeledChassis.modelWheel(rightMotor, wheelDiameter).offset(robotWidth / 2);
		
		chassis = new WheeledChassis(new Wheel[] {wheelLeft, wheelRight}, WheeledChassis.TYPE_DIFFERENTIAL);
		
		pilot = new MovePilot(chassis);
	}
	
	public void run() {
		Behavior[] behaviorList = new Behavior[] {};
		Arbitrator arb = new Arbitrator(behaviorList);
		
		arb.go();
	}
}