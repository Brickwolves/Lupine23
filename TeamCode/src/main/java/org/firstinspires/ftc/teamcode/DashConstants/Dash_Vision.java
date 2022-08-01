package org.firstinspires.ftc.teamcode.DashConstants;
import com.acmerobotics.dashboard.config.Config;

import org.opencv.core.Scalar;

@Config
public class Dash_Vision {

    public static double MAX_Y = 255; //hardcoded YCrCb values from dashboard
    public static double MAX_CR = 170;
    public static double MAX_CB = 65;

    public static double MIN_Y = 150;
    public static double MIN_CR = 135;
    public static double MIN_CB = 0.0;

    public static boolean DEBUG_MODE = false;

    public static boolean duckLeftBarcode = true; //if the duck is on the left barcode

    public static DuckPosition currentDuckPos = DuckPosition.LEFT_BARCODE;

    public enum DuckPosition{
        LEFT_BARCODE, RIGHT_BARCODE
    }



}