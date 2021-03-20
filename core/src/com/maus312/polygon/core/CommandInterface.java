package com.maus312.polygon.core;

/**
 * Describes an interface of simple command for interacting with core
 */

public interface CommandInterface {
    String getCommandName();
    default String getRegularCommandName() {
        return "("+ getCommandName() +")";
    }
    void onAction();
    default boolean needConfirmation() {
        return true;
    }
    default void lastRepeatAction() {
        MainSingle.getInstance().getCommandController().sendCommand(this);
    }
    //TODO : make regularName static
}
