package org.firstinspires.ftc.teamcode.DashConstants;
import com.acmerobotics.dashboard.config.Config;

@Config
public class Dash_Vision {
    //hardcoded from dashboard

    public static boolean DEBUG_MODE = false;

    //for GREEN on signal sleeve
    public static double GREEN_MAX_H = 0;
    public static double GREEN_MAX_S = 0;
    public static double GREEN_MAX_V = 0;
    public static double GREEN_MIN_H = 0;
    public static double GREEN_MIN_S = 0;
    public static double GREEN_MIN_V = 0;

    //for ORANGE on signal sleeve
    public static double ORANGE_MAX_H = 0;
    public static double ORANGE_MAX_S = 0;
    public static double ORANGE_MAX_V = 0;
    public static double ORANGE_MIN_H = 0;
    public static double ORANGE_MIN_S = 0;
    public static double ORANGE_MIN_V = 0;

    //for PURPLE on signal sleeve
    public static double PURPLE_MAX_H = 0;
    public static double PURPLE_MAX_S = 0;
    public static double PURPLE_MAX_V = 0;
    public static double PURPLE_MIN_H = 0;
    public static double PURPLE_MIN_S = 0;
    public static double PURPLE_MIN_V = 0;

    //public static CAMERA_OFFSET = 0;

    public static double DEGREE_RANGE = 100;

    public enum CamBeingUsed{
        SIGCAM, JUNKCAM
    }



}