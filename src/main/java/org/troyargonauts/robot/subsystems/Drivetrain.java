package org.troyargonauts.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.VictorSPXConfiguration;
import com.ctre.phoenix6.controls.StaticBrake;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    private VictorSPX m_FL_Motor, m_FR_Motor, m_BL_Motor, m_BR_Motor;
    private double rightEncoderValue, leftEncoderValue;
    public Drivetrain(){
        m_FL_Motor = new VictorSPX(4);
        m_FR_Motor = new VictorSPX(3);
        m_BL_Motor = new VictorSPX(2);
        m_BR_Motor = new VictorSPX(0);

        m_FL_Motor.setNeutralMode(NeutralMode.Brake);
        m_FR_Motor.setNeutralMode(NeutralMode.Brake);
        m_BL_Motor.setNeutralMode(NeutralMode.Brake);
        m_BR_Motor.setNeutralMode(NeutralMode.Brake);

        m_BR_Motor.setInverted(true);
        m_FR_Motor.setInverted(true);
    }

    public void resetEndcoders(){
        m_FL_Motor.set(VictorSPXControlMode.Position, 0);
        m_BL_Motor.set(VictorSPXControlMode.Position, 0);
        m_FR_Motor.set(VictorSPXControlMode.Position, 0);
        m_BR_Motor.set(VictorSPXControlMode.Position, 0);
    }

    @Override
    public void periodic(){
        rightEncoderValue = (m_FL_Motor.getSelectedSensorPosition() + m_BL_Motor.getSelectedSensorPosition())/2;
        leftEncoderValue = (m_FR_Motor.getSelectedSensorPosition() + m_BR_Motor.getSelectedSensorPosition())/2;

        SmartDashboard.putNumber("RightSideEncoder", rightEncoderValue);
        SmartDashboard.putNumber("LeftSideEncoder", leftEncoderValue);
    }

    public void cheesyDrive(double speed, double turn, double nerf){
        m_FL_Motor.set(VictorSPXControlMode.PercentOutput, (speed + turn) * nerf);
        m_BL_Motor.set(VictorSPXControlMode.PercentOutput, (speed + turn) * nerf);
        m_FR_Motor.set(VictorSPXControlMode.PercentOutput, (speed - turn) * nerf);
        m_BR_Motor.set(VictorSPXControlMode.PercentOutput, (speed - turn) * nerf);
    }

    public void tankDrive(double leftJoyValue, double rightJoyValue, double nerf){
        m_FL_Motor.set(VictorSPXControlMode.PercentOutput, (leftJoyValue * nerf));
        m_BL_Motor.set(VictorSPXControlMode.PercentOutput, (leftJoyValue * nerf));
        m_FR_Motor.set(VictorSPXControlMode.PercentOutput, (rightJoyValue * nerf));
        m_BR_Motor.set(VictorSPXControlMode.PercentOutput, (rightJoyValue * nerf));
    }
}
