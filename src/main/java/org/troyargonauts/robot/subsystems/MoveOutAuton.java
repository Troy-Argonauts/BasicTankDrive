package org.troyargonauts.robot.subsystems;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import org.troyargonauts.robot.Robot;

public class MoveOutAuton extends SequentialCommandGroup {
    public MoveOutAuton(){
        super(
                new RunCommand(() -> Robot.getDrivetrain().tankDrive(0.4,0.4,0), Robot.getDrivetrain()).withTimeout(3)
        );
    }
}
