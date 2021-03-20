package com.maus312.polygon.core.executable.commands.precision;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.Constants;
import com.maus312.polygon.core.MainSingle;
import com.maus312.polygon.core.UICallback;
import com.maus312.polygon.core.locale.LocaleManager;
import com.maus312.polygon.core.locale.ResourceTable;
import com.maus312.polygon.core.ui.UITable;

public class StartPrecisionOkCI implements CommandInterface {
    private static final String name = "STR PRC OK";

    @Override
    public String getCommandName() {
        return name;
    }

    @Override
    public void onAction() {
        MainSingle.getInstance().getCommandController().deleteCommand(new StartPrecisionCI().getRegularCommandName());
        MainSingle.getInstance().getStateManager().setPrecisionHigh();
        UITable table = MainSingle.getInstance().getUiTable();
        LocaleManager local = MainSingle.getInstance().getLocaleManager();

        table.getPrecisionButton().setText(local.getStringFromId(ResourceTable.DISABLE) + " " +
                local.getStringFromId(ResourceTable.PRECISION_BUTTON_STRING));
        table.getPrecisionButton().setCallback(new UICallback() {
            @Override
            public void onAction() {
                MainSingle.getInstance().getMessageSource().onReceive(Constants.STOP_PRECISION_TEXT);
            }
        });

        table.getPrecisionTVValue().setText(local.getStringFromId(ResourceTable.ENABLED));
    }

    @Override
    public boolean needConfirmation() {
        return false;
    }
}