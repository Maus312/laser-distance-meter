package com.maus312.polygon.core.executable.commands.laser;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.Constants;

public class StartLaserCI implements CommandInterface {
    private static final String name = Constants.START_LASER_TEXT;

    @Override
    public String getCommandName() {
        return name;
    }
    @Override
    public void onAction() {
    }
}