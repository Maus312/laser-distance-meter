package com.maus312.polygon.core.executable.commands.service;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.MainSingle;
import com.maus312.polygon.core.ui.UITable;

public class PServiceDisconnected implements CommandInterface {
    private static final String name = "CommandServiceDisconnected";

    @Override
    public String getCommandName() {
        return name;
    }
    @Override
    public void onAction() {
        MainSingle single = MainSingle.getInstance();

        UITable table = single.getUiTable();
        table.getScanButton().setEnabled(false);
        table.getLaserButton().setEnabled(false);
        table.getModeButton().setEnabled(false);
        table.getPrecisionButton().setEnabled(false);
        table.getAngleSetButton().setEnabled(false);
        table.getResetPositionButton().setEnabled(false);
        table.getStartRecordButton().setEnabled(false);
        table.getAngleETVInValue().setEnabled(false);

        single.getMessageSource().onTransmit(name + " OK");
    }

    @Override
    public boolean needConfirmation() {
        return false;
    }
}