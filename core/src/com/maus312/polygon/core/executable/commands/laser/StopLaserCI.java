package com.maus312.polygon.core.executable.commands.laser;

import com.maus312.polygon.core.CommandInterface;

public class StopLaserCI implements CommandInterface {
    private static final String name = "STP LSR";

    @Override
    public String getCommandName() {
        return name;
    }
    @Override
    public void onAction() {
    }
}