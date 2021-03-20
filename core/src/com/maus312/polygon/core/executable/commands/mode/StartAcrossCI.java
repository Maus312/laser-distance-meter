package com.maus312.polygon.core.executable.commands.mode;

import com.maus312.polygon.core.CommandInterface;

public class StartAcrossCI implements CommandInterface {
    private static final String name = "STR AST";

    @Override
    public String getCommandName() {
        return name;
    }
    @Override
    public void onAction() {

    }
}