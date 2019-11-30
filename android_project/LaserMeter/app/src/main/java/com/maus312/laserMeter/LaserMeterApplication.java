package com.maus312.laserMeter;

import android.app.Application;

public class LaserMeterApplication extends Application {
    boolean workStatus;
    boolean laserStatus;

    @Override
    public void onCreate() {
        super.onCreate();
        workStatus = false;
        laserStatus = false;
    }

    public boolean getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(boolean workStatus) {
        this.workStatus = workStatus;
    }

    public boolean getLaserStatus() {
        return laserStatus;
    }

    public void setLaserStatus(boolean laserStatus) {
        this.laserStatus = laserStatus;
    }
}
