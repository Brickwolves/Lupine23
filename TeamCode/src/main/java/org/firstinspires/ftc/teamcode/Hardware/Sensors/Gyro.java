package org.firstinspires.ftc.teamcode.Hardware.Sensors;

/*
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Utilities.RingBuffer;

public class Gyro {

    public IMU imu;
    private double datum;
    private final RingBuffer<Double> angleRing = new RingBuffer<>(4,0.0);
    private final RingBuffer<Long> angleTimeRing = new RingBuffer<>(4, (long)0);


    private double imuAngle = 0;
    private double rawAngle = 0;
    private double modAngle = 0;
    private double rateOfChange = 0;
    private double rateOfChangeShort = 0;




    public Gyro() {
        imu = new IMU("imu");
    }

    public void update(){
        imuAngle = imu.getAngle();
        rawAngle = imu.getAngle() - datum;
        modAngle = MathUtils.mod(rawAngle, 360);


        long currentTime = System.currentTimeMillis();
        long deltaMili = currentTime - angleTimeRing.getValue(currentTime);
        double deltaSeconds = deltaMili / 1000.0;

        double currentAngle = imu.getAngle();
        double deltaAngle = currentAngle - angleRing.getValue(currentAngle);


        rateOfChange = deltaAngle/deltaSeconds;

    }

    public void setImu(IMU imu) {
        this.imu = imu;
    }

    public void setDatum(double datum) {
        this.datum = datum;
    }

    public void reset() { datum = imu.getAngle(); }

    public double angle() {
        return 360 - rawAngle;
    }

    public double IMUAngle() {
        return imuAngle;
    }

    public double modAngle() {
        return modAngle;
    }

    public boolean angleRange(double minAngle, double maxAngle) {
        minAngle = MathUtils.mod(minAngle, 360);
        maxAngle = MathUtils.mod(maxAngle, 360);

        if (maxAngle < minAngle) return modAngle > minAngle || modAngle < maxAngle;
        else return modAngle > minAngle && modAngle < maxAngle;
    }

    public double absAngularDist(double compareAngle){
        return Math.abs(compareAngle - angle());
    }


    public double rateOfChange(){
        return rateOfChange;
    }

    public double rateOfChangeShort(){
        return rateOfChangeShort;
    }
}
 */
