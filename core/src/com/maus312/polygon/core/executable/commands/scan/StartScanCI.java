package com.maus312.polygon.core.executable.commands.scan;

import com.maus312.polygon.core.CommandInterface;

public class StartScanCI implements CommandInterface {
    private static final String name = "STR SCV";

    @Override
    public String getCommandName() {
        return name;
    }
    @Override
    public void onAction() {
    }
}
