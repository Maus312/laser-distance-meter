package com.maus312.polygon.core.executable.commands.mode;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.Constants;
import com.maus312.polygon.core.MainSingle;
import com.maus312.polygon.core.UICallback;
import com.maus312.polygon.core.executable.commands.mode.StartAcrossCI;
import com.maus312.polygon.core.executable.commands.precision.StartPrecisionCI;
import com.maus312.polygon.core.locale.LocaleManager;
import com.maus312.polygon.core.locale.ResourceTable;
import com.maus312.polygon.core.ui.UITable;

public class StartAcrossOkCI implements CommandInterface {
    private static final String name = "STR AST OK";

    @Override
    public String getCommandName() {
        return name;
    }
    @Override
    public void onAction() {
        MainSingle.getInstance().getCommandController().deleteCommand(new StartAcrossCI().getRegularCommandName());
        MainSingle.getInstance().getStateManager().setModeAcross();
        UITable table = MainSingle.getInstance().getUiTable();
        LocaleManager local = MainSingle.getInstance().getLocaleManager();

        table.getModeButton().setText(local.getStringFromId(ResourceTable.SERIAL) + " " +
                local.getStringFromId(ResourceTable.MODE));

        table.getModeButton().setCallback(new UICallback() {
            @Override
            public void onAction() {
                MainSingle.getInstance().getMessageSource().onReceive(Constants.START_SERIAL_TEXT);
            }
        });

        table.getModeTVValue().setText(local.getStringFromId(ResourceTable.ACROSS));
        MainSingle.getInstance().getExecutableManager().resetPointsAndLayer();
        MainSingle.getInstance().getStateManager().setModeAcross();
    }

    @Override
    public boolean needConfirmation() {
        return false;
    }
}