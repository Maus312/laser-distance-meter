package com.maus312.polygon.core.executable.commands.service;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.MainSingle;
import com.maus312.polygon.core.communication.BasicCommandController;

public class PServicePrintStack implements CommandInterface {
    private static final String name = "CommandPrintStack";

    @Override
    public String getCommandName() {
        return name;
    }
    @Override
    public void onAction() {
        MainSingle single = MainSingle.getInstance();

        String stackOfCommands = ((BasicCommandController)single.getCommandController()).printStack();
        single.getMessageSource().onTransmit(stackOfCommands);

        single.getMessageSource().onTransmit(name + " OK");
    }

    @Override
    public boolean needConfirmation() {
        return false;
    }
}
