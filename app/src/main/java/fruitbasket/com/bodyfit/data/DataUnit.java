package fruitbasket.com.bodyfit.data;

import android.hardware.SensorManager;

/**
 * 用于存储一条数据记录
 */
public class DataUnit {
    public static final int SENSER_NUMBER=12;//传感器的数据的数量

    static final int REF = 10;
    static final float COEFFICIENT_A = 13.2447f;
    static final float COEFFICIENT_B = -0.511f;

    private String time=null;

    /*
    这个数组中各个元素的值代表了：ax,ay,az,gx,gy,gz,mx,my,mz,p1,p2,p3
    */
    private double dataUnit[];

    public static int getIntFromByteArray(byte[] data, int start, int len) {
        int i = start + len - 1;
        int val = data[i];
        for (i-- ; i >= start; i--) {
            val <<= 8;
            val += (data[i] & 0xFF) ;
        }
        return val;
    }

    private static double convertPress(int val) {
        double r = (val * REF * 1.0) / (8192 - val);
        return COEFFICIENT_A * Math.pow(r, COEFFICIENT_B);
    }

    public DataUnit(byte[] data) {
        dataUnit = new double[SENSER_NUMBER];
        //getIntFromByteArray(data, 0, 2);
        //getIntFromByteArray(data, 2, 2);
        dataUnit[0] = getIntFromByteArray(data, 10, 2) * SensorManager.GRAVITY_EARTH / 16384.0;
        dataUnit[1] = getIntFromByteArray(data, 12, 2) * SensorManager.GRAVITY_EARTH / 16384.0;
        dataUnit[2] = getIntFromByteArray(data, 14, 2) * SensorManager.GRAVITY_EARTH / 16384.0;
        dataUnit[3] = getIntFromByteArray(data, 18, 2) / 131.0;
        dataUnit[4] = getIntFromByteArray(data, 20, 2) / 131.0;
        dataUnit[5] = getIntFromByteArray(data, 22, 2) / 131.0;
        dataUnit[6] = getIntFromByteArray(data, 24, 2) * 0.6;
        dataUnit[7] = getIntFromByteArray(data, 26, 2) * 0.6;
        dataUnit[8] = getIntFromByteArray(data, 28, 2) * 0.6;
        dataUnit[9] = convertPress(getIntFromByteArray(data, 4, 2));
        dataUnit[10] = convertPress(getIntFromByteArray(data, 6, 2));
        dataUnit[11] = convertPress(getIntFromByteArray(data, 8, 2));
    }

    public DataUnit(double dataUnit[]){

        this.dataUnit =new double[SENSER_NUMBER];

        if(dataUnit!=null&&dataUnit.length==SENSER_NUMBER){
            this.dataUnit[0]=dataUnit[0];
            this.dataUnit[1]=dataUnit[1];
            this.dataUnit[2]=dataUnit[2];
            this.dataUnit[3]=dataUnit[3];
            this.dataUnit[4]=dataUnit[4];
            this.dataUnit[5]=dataUnit[5];
            this.dataUnit[6]=dataUnit[6];
            this.dataUnit[7]=dataUnit[7];
            this.dataUnit[8]=dataUnit[8];
            this.dataUnit[9]=dataUnit[9];
            this.dataUnit[10]=dataUnit[10];
            this.dataUnit[11]=dataUnit[11];
        }
        else{

        }
    }

    public DataUnit(String time, double dataUnit[]){
        this(dataUnit);
        this.time=time;
    }

    public String getTime(){
        return time;
    }

    public double[] getDataUnit(){
        return dataUnit;
    }

    public double getAx(){
        return dataUnit[0];
    }

    public double getAy(){
        return dataUnit[1];
    }

    public double getAz(){
        return dataUnit[2];
    }

    public double getGx(){
        return dataUnit[3];
    }

    public double getGy(){
        return dataUnit[4];
    }

    public double getGz(){
        return dataUnit[5];
    }

    public double getMx(){
        return dataUnit[6];
    }

    public double getMy(){
        return dataUnit[7];
    }

    public double getMz(){
        return dataUnit[8];
    }

    public double getP1(){
        return dataUnit[9];
    }

    public double getP2(){
        return dataUnit[10];
    }

    public double getP3(){
        return dataUnit[11];
    }
}
