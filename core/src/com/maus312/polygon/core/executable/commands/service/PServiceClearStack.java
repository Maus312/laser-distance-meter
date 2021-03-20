package com.maus312.polygon.core.executable.commands.service;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.MainSingle;
import com.maus312.polygon.core.communication.BasicCommandController;

public class PServiceClearStack implements CommandInterface {
    private static final String name = "CommandClearStack";

    @Override
    public String getCommandName() {
        return name;
    }
    @Override
    public void onAction() {
        MainSingle single = MainSingle.getInstance();

        ((BasicCommandController)single.getCommandController()).clearStack();

        single.getMessageSource().onTransmit(name + " OK");
    }

    @Override
    public boolean needConfirmation() {
        return false;
    }
}
