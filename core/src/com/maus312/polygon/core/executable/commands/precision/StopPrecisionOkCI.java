package com.maus312.polygon.core.executable.commands.precision;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.Constants;
import com.maus312.polygon.core.MainSingle;
import com.maus312.polygon.core.UICallback;
import com.maus312.polygon.core.locale.LocaleManager;
import com.maus312.polygon.core.locale.ResourceTable;
import com.maus312.polygon.core.ui.UITable;

public class StopPrecisionOkCI implements CommandInterface {
    private static final String name = "STP PRC OK";

    @Override
    public String getCommandName() {
        return name;
    }

    @Override
    public void onAction() {
        MainSingle.getInstance().getCommandController().deleteCommand(new StopPrecisionCI().getRegularCommandName());
        MainSingle.getInstance().getStateManager().setPrecisionLow();
        UITable table = MainSingle.getInstance().getUiTable();
        LocaleManager local = MainSingle.getInstance().getLocaleManager();

        table.getPrecisionButton().setText(local.getStringFromId(ResourceTable.ENABLE) + " " +
                local.getStringFromId(ResourceTable.PRECISION_BUTTON_STRING));
        table.getPrecisionButton().setCallback(new UICallback() {
            @Override
            public void onAction() {
                MainSingle.getInstance().getMessageSource().onReceive(Constants.START_PRECISION_TEXT);
            }
        });

        table.getPrecisionTVValue().setText(local.getStringFromId(ResourceTable.DISABLED));
    }

    @Override
    public boolean needConfirmation() {
        return false;
    }
}