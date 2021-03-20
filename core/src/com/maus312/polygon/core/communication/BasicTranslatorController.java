package com.maus312.polygon.core.communication;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.MainSingle;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasicTranslatorController implements TranslatorController {
    String previousCommand = "";


    @Override
    public void sendCommand(CommandInterface cI) {
        String commandName = MainSingle.getInstance().getExecutableManager().getNameFromCommandInterface(cI);
        MainSingle.getInstance().getMessageSource().onTransmit(commandName);
    }

    @Override
    public void onSuccessfulTransmit(CommandInterface cI) {

    }

    @Override
    public void recogniseCommand(String commandString) {
        String temporaryCommandString = previousCommand.concat(commandString);
        String commands = MainSingle.getInstance().getExecutableManager().getNamesOfCommands();
        Pattern pattern = Pattern.compile(commands);
        Matcher matcher = pattern.matcher(temporaryCommandString);

        List<String> listMatches = new ArrayList<>();

        while(matcher.find())
        {
            listMatches.add(matcher.group());
        }

        if (!listMatches.isEmpty()) {
            for(String s : listMatches)
            {
                CommandInterface cI = MainSingle.getInstance().getExecutableManager().getCommandInterfaceFromName(s);
                MainSingle.getInstance().getCommandController().executeCommand(cI);
            }
            int endPosition = temporaryCommandString.lastIndexOf(listMatches.get(listMatches.size() - 1)) +
                   + listMatches.get(listMatches.size() - 1).length()-1;
            if (temporaryCommandString.length() >= endPosition) {
                previousCommand = "";
            } else {
                previousCommand = temporaryCommandString.substring(endPosition);
            }

            return;

        } else {
            previousCommand = commandString;
        }
        // if recognised, delete recognised from string, create cI
    }

    @Override
    public String stringToCommand(CommandInterface cI) {
        return null;
    }

    @Override
    public CommandInterface stringToCommand(String commandString) {
        return null;
    }
}
