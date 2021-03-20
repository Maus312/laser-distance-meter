package com.maus312.polygon.core.executable.commands.scan;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.Constants;
import com.maus312.polygon.core.MainSingle;
import com.maus312.polygon.core.UICallback;
import com.maus312.polygon.core.locale.LocaleManager;
import com.maus312.polygon.core.locale.ResourceTable;
import com.maus312.polygon.core.ui.UITable;


public class StopScanOkCI implements CommandInterface {
    private static final String name = "STP SCV OK";

    @Override
    public String getCommandName() {
        return name;
    }

    @Override
    public void onAction() {
        MainSingle.getInstance().getCommandController().deleteCommand(new StartScanCI().getRegularCommandName());
        MainSingle.getInstance().getStateManager().setScanningDisabled();
        UITable table = MainSingle.getInstance().getUiTable();
        LocaleManager local = MainSingle.getInstance().getLocaleManager();

        table.getScanButton().setText(local.getStringFromId(ResourceTable.START) + " " +
                local.getStringFromId(ResourceTable.SCAN_BUTTON_STRING));
        table.getScanButton().setCallback(new UICallback() {
            @Override
            public void onAction() {
                MainSingle.getInstance().getMessageSource().onReceive(Constants.START_SCAN_TEXT);
            }
        });

        table.getScanTVValue().setText(local.getStringFromId(ResourceTable.DISABLED));

        MainSingle.getInstance().getCommandController().deleteCommand(new StopScanCI().getRegularCommandName());
    }

    @Override
    public boolean needConfirmation() {
        return false;
    }
}