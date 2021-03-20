package com.maus312.polygon.core.executable.commands.length;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.MainSingle;
import com.maus312.polygon.core.graphics.basic.PolarPoint;

public class SetLengthAngle implements CommandInterface {
    private static final String name = "L\\d{1,4}A\\d{1,3}#";
    private int length;
    private int angle;


    @Override
    public String getCommandName() {
        return name;
    }
    @Override
    public void onAction() {
        MainSingle.getInstance().getExecutableManager().sendPolarPoint(new PolarPoint(length, angle));
    }

    @Override
    public boolean needConfirmation() {
        return false;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}