package com.maus312.polygon.core.communication;

import com.maus312.polygon.core.MainSingle;

/**
 * An interface that describes an Entity that could receive and transmit commands
 * in the string format.
 */

public interface MessageSource {
    default void onReceive(String message)
    {
        MainSingle.getInstance().getTranslatorController().recogniseCommand(message);

        if (MainSingle.getInstance().getLogger() != null) {
            MainSingle.getInstance().getLogger().onLog(getClass().getName(),"onReceive: " + message);
        }
    }
    void onTransmit (String message);
}
