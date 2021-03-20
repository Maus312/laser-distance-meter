package com.maus312.polygon.core.executable.commands.angle;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.MainSingle;
import com.maus312.polygon.core.executable.commands.laser.StopLaserCI;
import com.maus312.polygon.core.ui.UITable;

public class SetScanAngleOkCI implements CommandInterface {
    private static final String name = "(SCA {1}\\d{3} OK)|(SCA {2}\\d{2} OK)|(SCA {3}\\d{1} OK)";
    private String actualName = name;
    private String SCA_PATTERN = "SCA";
    private int angle = 30;

    @Override
    public String getCommandName() {
        return name;
    }

    @Override
    public String getRegularCommandName() {
        return name;
    }

    @Override
    public void onAction() {
        MainSingle.getInstance().getStateManager().setStepAngle(angle);
        UITable table = MainSingle.getInstance().getUiTable();
        table.getAngleTVValue().setText(Integer.toString(angle));
        MainSingle.getInstance().getCommandController().deleteCommand(new SetScanAngleCI().getRegularCommandName());

    }

    public SetScanAngleOkCI() {
    }

    public SetScanAngleOkCI(int angle) {
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

    @Override
    public boolean needConfirmation() {
        return false;
    }
}
