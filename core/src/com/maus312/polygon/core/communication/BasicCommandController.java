package com.maus312.polygon.core.communication;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.MainSingle;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BasicCommandController implements CommandController {
    private List<CommandInterface> commandInterfaces;
    private Timer timer;
    private ControllerTimerTask timerTask;

    public BasicCommandController() {
        commandInterfaces = new ArrayList<>();
    }

    @Override
    public void executeCommand(CommandInterface cI) {
        cI.onAction();
        if (cI.needConfirmation()) {
            addNewCommand(cI);
        }
    }

    @Override
    public void sendCommand(CommandInterface cI) {
        MainSingle.getInstance().getTranslatorController().sendCommand(cI);
    }

    private synchronized void addNewCommand(CommandInterface cI) {
        commandInterfaces.add(cI);
        if (timer == null || timerTask == null) {
            timer = new Timer();
            timerTask = new ControllerTimerTask();
            timer.schedule(timerTask, 0, 1500);
        }
    }

    public void deleteFirst() {
        if (timer != null || timerTask != null) {
            timerTask.deleteFirst();
        }
    }

    public void clearStack() {
        if (timer != null || timerTask != null) {
            timerTask.clearAll();
        }
    }

    public String printStack() {
        String commandString = "Command Stack \n";
        if(commandInterfaces.isEmpty()) {
            return (commandString+="Empty \n");
        }
        List<CommandInterface> commands = new ArrayList<>(commandInterfaces);
        for (CommandInterface command : commands) {
            commandString = commandString + command.getCommandName() + "\n";
        }
        return  commandString + "\n";
    }



    @Override
    public synchronized void deleteCommand(String name) {
        if (timer != null || timerTask != null) {
            timerTask.deleteFromName(name);
        }
    }

    private class ControllerTimerTask extends TimerTask {
        private String nameForDeleting = "";
        private boolean clearStack;
        private boolean deleteFirst;

        @Override
        public void run() {
            if (commandInterfaces.isEmpty()) {
                timer = null;
                timerTask = null;
                return;
            }

            if(deleteFirst && !commandInterfaces.isEmpty()) {
                commandInterfaces.remove(0);
                deleteFirst = false;
                return;
            }

            if(clearStack && !commandInterfaces.isEmpty()) {
                commandInterfaces.clear();
                clearStack = false;
                return;
            }

            if (!nameForDeleting.isEmpty() && !commandInterfaces.isEmpty()) {
                for (CommandInterface ci : commandInterfaces) {
                    if (ci.getCommandName().matches(nameForDeleting)) {
                        commandInterfaces.remove(ci);
                        nameForDeleting = "";
                        return;
                    } else {
                        nameForDeleting = "";
                    }
                }
            }

            commandInterfaces.get(0).lastRepeatAction();
        }

        public void deleteFromName(String name) {
            nameForDeleting = name;
        }

        public void deleteFirst() {
            deleteFirst = true;
        }

        public void clearAll() {
            clearStack = true;
        }
    }
}
