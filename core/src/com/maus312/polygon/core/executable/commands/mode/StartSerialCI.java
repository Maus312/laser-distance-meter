package com.maus312.polygon.core.executable.commands.mode;

import com.maus312.polygon.core.CommandInterface;

public class StartSerialCI implements CommandInterface {
    private static final String name = "STR SER";

    @Override
    public String getCommandName() {
        return name;
    }

    @Override
    public void onAction() {

    }
}