package com.maus312.polygon.core.executable.commands.reset;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.MainSingle;

public class ResetCurrentPositionOkCI implements CommandInterface {
    private static final String name = "RES CPS OK";

    @Override
    public String getCommandName() {
        return name;
    }
    @Override
    public void onAction() {
        MainSingle.getInstance().getCommandController().deleteCommand(new ResetCurrentPositionCI().getRegularCommandName());
        MainSingle.getInstance().getExecutableManager().resetPointsAndLayer();
    }

    @Override
    public boolean needConfirmation() {
        return false;
    }
}