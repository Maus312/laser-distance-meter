package com.maus312.polygon.core.executable.commands.service;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.MainSingle;
import com.maus312.polygon.core.ui.UITable;

public class PServiceConnected implements CommandInterface {
    private static final String name = "CommandServiceConnected";

    @Override
    public String getCommandName() {
        return name;
    }
    @Override
    public void onAction() {
        MainSingle single = MainSingle.getInstance();
        UITable table = single.getUiTable();
        table.getScanButton().setEnabled(true);
        table.getLaserButton().setEnabled(true);
        table.getModeButton().setEnabled(true);
        table.getPrecisionButton().setEnabled(true);
        table.getAngleSetButton().setEnabled(true);
        table.getResetPositionButton().setEnabled(true);
        table.getStartRecordButton().setEnabled(true);
        table.getAngleETVInValue().setEnabled(true);

        single.getMessageSource().onTransmit(name + " OK");
    }

    @Override
    public boolean needConfirmation() {
        return false;
    }
}