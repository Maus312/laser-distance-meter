package com.maus312.polygon.core.executable.commands.precision;

import com.maus312.polygon.core.CommandInterface;

public class StopPrecisionCI implements CommandInterface {
    private static final String name = "STP PRC";

    @Override
    public String getCommandName() {
        return name;
    }

    @Override
    public void onAction() {

    }
}