package com.maus312.polygon.core.executable.commands.laser;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.Constants;
import com.maus312.polygon.core.MainSingle;
import com.maus312.polygon.core.UICallback;
import com.maus312.polygon.core.locale.LocaleManager;
import com.maus312.polygon.core.locale.ResourceTable;
import com.maus312.polygon.core.ui.UITable;

public class StopLaserOkCI implements CommandInterface {
    private static final String name = "STP LSR OK";

    @Override
    public String getCommandName() {
        return name;
    }
    @Override
    public void onAction() {
        MainSingle.getInstance().getCommandController().deleteCommand(new StopLaserCI().getRegularCommandName());
        MainSingle.getInstance().getStateManager().setLaserDisabled();
        UITable table = MainSingle.getInstance().getUiTable();
        LocaleManager local = MainSingle.getInstance().getLocaleManager();

        table.getLaserButton().setText(local.getStringFromId(ResourceTable.ENABLE) + " " +
                local.getStringFromId(ResourceTable.LASER_BUTTON_STRING));
        table.getLaserButton().setCallback(new UICallback() {
            @Override
            public void onAction() {
                MainSingle.getInstance().getMessageSource().onReceive(Constants.START_LASER_TEXT);
            }
        });

        table.getLaserTVValue().setText(local.getStringFromId(ResourceTable.DISABLED));
    }

    @Override
    public boolean needConfirmation() {
        return false;
    }
}