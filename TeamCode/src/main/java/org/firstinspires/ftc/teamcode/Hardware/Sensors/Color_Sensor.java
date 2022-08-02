package org.firstinspires.ftc.teamcode.Hardware.Sensors;

import com.qualcomm.robotcore.hardware.ColorSensor;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

public class Color_Sensor {
    public ColorSensor colorSensor;
    public int redCacheValue, blueCacheValue, greenCacheValue = 0;

    public void init(String mapName) {

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

