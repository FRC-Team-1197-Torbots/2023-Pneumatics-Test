package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;

public class Drive {

    private XboxController player1;
    private CANSparkMax RightTop;	
	private CANSparkMax RightBottom1;
	private CANSparkMax RightBottom2;
	
	private CANSparkMax LeftTop;
	private CANSparkMax LeftBottom1;
	private CANSparkMax LeftBottom2;

    private double throttle;
    private double steer;

    private double leftSpeed;
    private double rightspeed;

    public Drive(){
        player1 = new XboxController(0);

        LeftTop = new CANSparkMax(2, MotorType.kBrushless);
        //LeftBottom1 = new CANSparkMax(1, MotorType.kBrushless);
        LeftBottom2 = new CANSparkMax(3, MotorType.kBrushless);

        RightTop = new CANSparkMax(5, MotorType.kBrushless);
        RightBottom1 = new CANSparkMax(4, MotorType.kBrushless);
        RightBottom2 = new CANSparkMax(6, MotorType.kBrushless);
    }

    public void Run(){
        throttle = -player1.getRawAxis(1);
        steer = player1.getRawAxis(0);
        if(Math.abs(throttle) < 0.025f) {
            throttle = 0;
       }

       if(Math.abs(steer) < 0.025f) {
           steer = 0;
       }
       if(throttle > 1) {
        throttle = 1;
        }

        if(throttle < -1) {
            throttle = -1;
        }

        if(throttle > 0) {
            if(steer > 0) {
                leftSpeed = throttle - steer;
                rightspeed = Math.max(throttle, steer);
            } else {
                leftSpeed = Math.max(throttle, -steer);
                rightspeed = throttle + steer;
            }
        } else {
            if(steer > 0) {
                leftSpeed = -Math.max(-throttle, steer);
                rightspeed = throttle + steer;
            } else {
                leftSpeed = throttle - steer;
                rightspeed = -Math.max(-throttle, -steer);
            }
        }

        setMotorSpeeds(leftSpeed, rightspeed);
    }

    public void setMotorSpeeds(double leftSpeed, double rightSpeed) {
		SetLeft(leftSpeed); //- for auto
		SetRight(rightSpeed); //+ for teleop
	}

	// Setting the left master Talon's speed to the given parameter
	public void SetLeft(double speed) {
		LeftTop.set(speed);
		//LeftBottom1.set(-speed);
		LeftBottom2.set(-speed);
	}

	// Setting the right master Talon's speed to the given parameter
	public void SetRight(double speed) {
		RightTop.set(speed); //in correct setting, but "software fix"		
		RightBottom1.set(-speed);
		RightBottom2.set(-speed);
	}
    
    
}
