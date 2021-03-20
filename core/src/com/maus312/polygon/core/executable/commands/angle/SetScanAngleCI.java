package com.maus312.polygon.core.executable.commands.angle;

import com.maus312.polygon.core.CommandInterface;

public class SetScanAngleCI implements CommandInterface {
    private static final String name = "(SCA {1}\\d{3})|(SCA {2}\\d{2})|(SCA {3}\\d{1})";
    private String actualName = name;
    private String SCA_PATTERN = "SCA";
    private int angle;

    public SetScanAngleCI() {
    }

    public SetScanAngleCI(int angle) {
        setAngle(angle);
    }

    public void setAngle(int angle) {
        String angleString = Integer.toString(angle);
        this.angle = angle;
        if (angle < 360 && angle >= 100) {
            actualName = SCA_PATTERN + " " + angleString;
        } else if (angle < 99 && angle >= 10) {
            actualName = SCA_PATTERN + "  " + angleString;
        } else if (angle < 9 && angle >= 1) {
            actualName = SCA_PATTERN + "   " + angleString;
        } else {
            actualName = SCA_PATTERN + "  30";
            this.angle = 30;
        }
    }

    public int getAngle() {
        return angle;
    }

    @Override
    public String getCommandName() {
        return actualName;
    }

    @Override
    public String getRegularCommandName() {
        return actualName;
    }

    @Override
    public void onAction() {

    }
}
