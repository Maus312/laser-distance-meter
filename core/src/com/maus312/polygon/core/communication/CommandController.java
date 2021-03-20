package com.maus312.polygon.core.communication;

import com.maus312.polygon.core.CommandInterface;

/**
 * Interface that describes entity that controls command executing process
 */
public interface CommandController {
    void deleteCommand(String name);
    void executeCommand(CommandInterface cI);
    void sendCommand(CommandInterface cI);
}
