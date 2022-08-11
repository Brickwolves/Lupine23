package org.firstinspires.ftc.teamcode.Hardware.Sensors;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.wolfpackmachina.bettersensors.Drivers.ColorSensorV3;
import com.wolfpackmachina.bettersensors.HardwareMapProvider;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Utilities.OpModeUtils;

public class Color_Sensor {
    public ColorSensor colorSensor;
    public int redCacheValue, blueCacheValue, greenCacheValue = 0;

    public void init(String mapName) {
        HardwareMapProvider.setMap(hardwareMap);

        colorSensor = hardwareMap.get(ColorSensor.class, mapName);
    }

    /**
     * ONCE PER LOOP AND ONCE PER LOOP ONLY !!!!!!
     * @return
     */
    public double updateRed(){
        redCacheValue = colorSensor.red();
        return redCacheValue;
    }

    /**
     *
     * ONCE PER LOOP AND ONCE PER LOOP ONLY !!!!!!
     * @return
     */
    public double updateBlue(){
        blueCacheValue = colorSensor.blue();
        return blueCacheValue;
    }

    /**
     * ONCE PER LOOP AND ONCE PER LOOP ONLY !!!!!!
     * @return
     */
    public double updateGreen(){
        greenCacheValue = colorSensor.green();
        return greenCacheValue;
    }

//    public double getDistCM(){
//        colorSensorV3.update();
//        return colorSensorV3.getDistanceCM();
//    }

    public int getRedCacheValue() {
        return redCacheValue;
    }

    public int getBlueCacheValue() {
        return blueCacheValue;
    }

    public int getGreenCacheValue() {
        return greenCacheValue;
    }
}

