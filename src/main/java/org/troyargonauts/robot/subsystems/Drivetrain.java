package org.troyargonauts.robot.subsystems;

import com.ctre.phoenix6.controls.StaticBrake;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    private CANSparkMax m_FL_Motor, m_FR_Motor, m_BL_Motor, m_BR_Motor;
    private double rightEncoderValue, leftEncoderValue;
    public Drivetrain(){
        m_FL_Motor = new CANSparkMax(1, CANSparkMax.MotorType.kBrushless);
        m_FR_Motor = new CANSparkMax(2, CANSparkMax.MotorType.kBrushless);
        m_BL_Motor = new CANSparkMax(3, CANSparkMax.MotorType.kBrushless);
        m_BR_Motor = new CANSparkMax(4, CANSparkMax.MotorType.kBrushless);

        m_FL_Motor.setIdleMode(CANSparkBase.IdleMode.kBrake);
        m_FR_Motor.setIdleMode(CANSparkBase.IdleMode.kBrake);
        m_BL_Motor.setIdleMode(CANSparkBase.IdleMode.kBrake);
        m_BR_Motor.setIdleMode(CANSparkBase.IdleMode.kBrake);
    }

    public void resetEndcoders(){
        m_FL_Motor.getEncoder().setPosition(0);
        m_BL_Motor.getEncoder().setPosition(0);
        m_FR_Motor.getEncoder().setPosition(0);
        m_BL_Motor.getEncoder().setPosition(0);
    }

    @Override
    public void periodic(){
        rightEncoderValue = (m_FL_Motor.getEncoder().getPosition() + m_BL_Motor.getEncoder().getPosition())/2;
        leftEncoderValue = (m_FR_Motor.getEncoder().getPosition() + m_BR_Motor.getEncoder().getPosition())/2;
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
