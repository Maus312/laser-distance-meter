package com.maus312.polygon.core.executable.commands.laser;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.Constants;
import com.maus312.polygon.core.MainSingle;
import com.maus312.polygon.core.UICallback;
import com.maus312.polygon.core.locale.LocaleManager;
import com.maus312.polygon.core.locale.ResourceTable;
import com.maus312.polygon.core.ui.UITable;

public class StartLaserOkCI implements CommandInterface {
    private static final String name = Constants.START_LASER_TEXT + " OK";

    @Override
    public String getCommandName() {
        return name;
    }
    @Override
    public void onAction() {
        MainSingle.getInstance().getCommandController().deleteCommand(new StartLaserCI().getRegularCommandName());
        MainSingle.getInstance().getStateManager().setLaserEnabled();
        UITable table = MainSingle.getInstance().getUiTable();
        LocaleManager local = MainSingle.getInstance().getLocaleManager();

        table.getLaserButton().setText(local.getStringFromId(ResourceTable.DISABLE) + " " +
                local.getStringFromId(ResourceTable.LASER_BUTTON_STRING));
        table.getLaserButton().setCallback(new UICallback() {
            @Override
            public void onAction() {
                MainSingle.getInstance().getMessageSource().onReceive(Constants.STOP_LASER_TEXT);
            }
        });

        table.getLaserTVValue().setText(local.getStringFromId(ResourceTable.ENABLED));
    }

    @Override
    public boolean needConfirmation() {
        return false;
    }
}