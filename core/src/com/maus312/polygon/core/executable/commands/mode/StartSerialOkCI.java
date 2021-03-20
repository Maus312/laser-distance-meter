package com.maus312.polygon.core.executable.commands.mode;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.Constants;
import com.maus312.polygon.core.MainSingle;
import com.maus312.polygon.core.UICallback;
import com.maus312.polygon.core.locale.LocaleManager;
import com.maus312.polygon.core.locale.ResourceTable;
import com.maus312.polygon.core.ui.UITable;

public class StartSerialOkCI implements CommandInterface {
    private static final String name = "STR SER OK";

    @Override
    public String getCommandName() {
        return name;
    }
    @Override
    public void onAction() {
        MainSingle.getInstance().getCommandController().deleteCommand(new StartSerialCI().getRegularCommandName());
        MainSingle.getInstance().getStateManager().setModeSerial();
        UITable table = MainSingle.getInstance().getUiTable();
        LocaleManager local = MainSingle.getInstance().getLocaleManager();

        table.getModeButton().setText(local.getStringFromId(ResourceTable.ACROSS) + " " +
                local.getStringFromId(ResourceTable.MODE));
        table.getModeButton().setCallback(new UICallback() {
            @Override
            public void onAction() {
                MainSingle.getInstance().getMessageSource().onReceive(Constants.START_ACROSS_TEXT);
            }
        });

        table.getModeTVValue().setText(local.getStringFromId(ResourceTable.SERIAL));
        MainSingle.getInstance().getExecutableManager().resetPointsAndLayer();
        MainSingle.getInstance().getStateManager().setModeSerial();
    }

    @Override
    public boolean needConfirmation() {
        return false;
    }
}