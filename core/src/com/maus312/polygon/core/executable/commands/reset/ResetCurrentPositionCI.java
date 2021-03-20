package com.maus312.polygon.core.executable.commands.reset;

import com.maus312.polygon.core.CommandInterface;

public class ResetCurrentPositionCI implements CommandInterface {
    private static final String name = "RES CPS";

    @Override
    public String getCommandName() {
        return name;
    }
    @Override
    public void onAction() {
    }
}