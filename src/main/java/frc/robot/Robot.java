// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//example comment

package frc.robot;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import dev.doglog.DogLog;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  TalonFX intakeMotor = new TalonFX(20,"rio");
  TalonFX shooterOneMotor = new TalonFX(15,"rio");
  TalonFX shooterTwoMotor = new TalonFX(16,"rio");
  
  XboxController xbox = new XboxController(0);
  RobotState state = RobotState.STOWED;
  DigitalInput intakeSensor = new DigitalInput(2);

  VoltageOut motorVoltageRequest = new VoltageOut(0.0);


  @Override
  public void robotInit() {


  }

  @Override
  public void robotPeriodic() {
    // robot periodic is always running, whether you are enabled or disabled, or in teleop or auto
    // because of that it is a good place to log things, because we want to see the logs even if the robot is disabled
    double motor_temp = intakeMotor.getDeviceTemp().getValue(); //remember that we need to always use .getValue() when using a sensor on a TalonFX
    DogLog.log("Intake/MotorTemperature",motor_temp);
    
    DogLog.log("Robot/RobotState",state); // log the state here so we know what state the robot is in


  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {

  }


    /* TASKS:
     *  1. fill in the code for the missing states
     *  2. add a mode for outtaking (spitting the note out from the intake)
     *  3. add some more logging to the program (motor current, speed, temperature)
     *  4. automatically switch from SHOOTING to STOWED when the intake sensor no longer sees the note
     *  5. Use a VelocityVoltage control request, instead of the VoltageOut to control the flywheel to spin up to an accurate rpm
     *  6. Figure out how to use the intake sensor to ensure that the note doesnt go past the wheel
     *  7. Write some code to control the top and bottom flywheels with different speeds
     * 
     * 
     * 
     * you dont need to do all of these, but please make sure to finish the first four in class
     */


  @Override
  public void teleopPeriodic() {

    // events/transitions, this part reads joysticks and sensors and decides what state we should be in
    if (xbox.getAButton()){
      state = RobotState.INTAKING;
    } else if (xbox.getRightBumper()){
      state = RobotState.SHOOTING;
    } else {
      state = RobotState.STOWED;
    }

    if (state== RobotState.INTAKING && intakeSensor.get()){ // if we are currently in intaking mode right now AND the sensor is true, go to stowed instead
      state = RobotState.STOWED;
    }
    
    // states, this part sets the motors to the right setting based on what state we are in
    if (state == RobotState.STOWED){
      intakeMotor.setControl(motorVoltageRequest.withOutput(0.0));
      shooterOneMotor.setControl(motorVoltageRequest.withOutput(0.0));
      shooterTwoMotor.setControl(motorVoltageRequest.withOutput(0.0));
    } else if (state == RobotState.INTAKING){
      // FILL THIS IN
    } else if (state == RobotState.SHOOTING){
      // FILL THIS IN
    } 

    
    
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
