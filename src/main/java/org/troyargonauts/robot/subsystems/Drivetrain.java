package org.troyargonauts.robot.subsystems;

import com.ctre.phoenix6.controls.StaticBrake;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    private TalonFX m_FL_Motor, m_FR_Motor, m_BL_Motor, m_BR_Motor;
    private double rightEncoderValue, leftEncoderValue;
    public Drivetrain(){
        m_FL_Motor = new TalonFX(4);
        m_FR_Motor = new TalonFX(3);
        m_BL_Motor = new TalonFX(2);
        m_BR_Motor = new TalonFX(0);

        m_FL_Motor.setNeutralMode(NeutralModeValue.Brake);
        m_FR_Motor.setNeutralMode(NeutralModeValue.Brake);
        m_BL_Motor.setNeutralMode(NeutralModeValue.Brake);
        m_BL_Motor.setNeutralMode(NeutralModeValue.Brake);
    }

    public void resetEndcoders(){
        m_FL_Motor.setPosition(0);
        m_BL_Motor.setPosition(0);
        m_FR_Motor.setPosition(0);
        m_BL_Motor.setPosition(0);
    }

    @Override
    public void periodic(){
        rightEncoderValue = (m_FL_Motor.getPosition().getValueAsDouble() + m_BL_Motor.getPosition().getValueAsDouble())/2;
        leftEncoderValue = (m_FR_Motor.getPosition().getValueAsDouble() + m_BR_Motor.getPosition().getValueAsDouble())/2;
    }

    public void cheesyDrive(double speed, double turn, double nerf){
        m_FL_Motor.set((speed + turn) * nerf);
        m_BL_Motor.set((speed + turn) * nerf);
        m_FR_Motor.set((speed - turn) * nerf);
        m_BR_Motor.set((speed - turn) * nerf);
    }

    public void tankDrive(double leftJoyValue, double rightJoyValue, double nerf){
        m_FL_Motor.set(leftJoyValue * nerf);
        m_BL_Motor.set(leftJoyValue * nerf);
        m_FR_Motor.set(rightJoyValue * nerf);
        m_BR_Motor.set(rightJoyValue * nerf);
    }
}
