package com.maus312.polygon.core.communication;

import com.maus312.polygon.core.CommandInterface;

/**
 * Describes an interface of entity that can translate commands
 * from string value to CommandInterface and back.
 */

public interface TranslatorController {
    void sendCommand(CommandInterface cI);
    void onSuccessfulTransmit(CommandInterface cI);
    void recogniseCommand (String commandString);
    String stringToCommand(CommandInterface cI);
    CommandInterface stringToCommand(String commandString);
}
