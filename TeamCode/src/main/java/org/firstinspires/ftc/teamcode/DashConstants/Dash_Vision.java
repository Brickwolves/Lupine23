package org.firstinspires.ftc.teamcode.DashConstants;
import com.acmerobotics.dashboard.config.Config;

@Config
public class Dash_Vision {
    //hardcoded from dashboard

    public static boolean DEBUG_MODE = true;

    //for GREEN on signal sleeve
    public static double GREEN_MAX_Y = 95;
    public static double GREEN_MAX_CR = 125;
    public static double GREEN_MAX_CB = 255;
    public static double GREEN_MIN_Y = 0;
    public static double GREEN_MIN_CR = 0;
    public static double GREEN_MIN_CB = 0;

    //for ORANGE on signal sleeve
    public static double ORANGE_MAX_Y = 95;
    public static double ORANGE_MAX_CR = 200;
    public static double ORANGE_MAX_CB = 100;
    public static double ORANGE_MIN_Y = 0;
    public static double ORANGE_MIN_CR = 0;
    public static double ORANGE_MIN_CB = 0;

    //for PURPLE on signal sleeve
    public static double PINK_MAX_Y = 95;
    public static double PINK_MAX_CR = 200;
    public static double PINK_MAX_CB = 150;
    public static double PINK_MIN_Y = 0;
    public static double PINK_MIN_CR = 150;
    public static double PINK_MIN_CB = 120;



    //public static CAMERA_OFFSET = 0;

    public static double DEGREE_RANGE = 100;

    public enum CamBeingUsed{
        SIGCAM, JUNKCAM
    }



}