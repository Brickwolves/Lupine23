package org.firstinspires.ftc.teamcode.Utilities;

import com.acmerobotics.dashboard.config.Config;

@Config
public class PIDWeights {

    public static double proportionalWeight = 0.025;
    public static double integralWeight = 0;
    public static double derivativeWeight = 0.0034;
    public static double intakeP = 0.02;
    public static double intakeI = 0;
    public static double intakeD = 0;
}