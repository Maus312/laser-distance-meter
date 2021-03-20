package com.maus312.polygon.core.executable.commands;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.executable.commands.angle.SetScanAngleCI;
import com.maus312.polygon.core.executable.commands.angle.SetScanAngleOkCI;
import com.maus312.polygon.core.executable.commands.laser.StartLaserCI;
import com.maus312.polygon.core.executable.commands.laser.StartLaserOkCI;
import com.maus312.polygon.core.executable.commands.laser.StopLaserCI;
import com.maus312.polygon.core.executable.commands.laser.StopLaserOkCI;
import com.maus312.polygon.core.executable.commands.length.SetLengthAngle;
import com.maus312.polygon.core.executable.commands.mode.StartAcrossCI;
import com.maus312.polygon.core.executable.commands.mode.StartAcrossOkCI;
import com.maus312.polygon.core.executable.commands.mode.StartSerialCI;
import com.maus312.polygon.core.executable.commands.mode.StartSerialOkCI;
import com.maus312.polygon.core.executable.commands.precision.StartPrecisionCI;
import com.maus312.polygon.core.executable.commands.precision.StartPrecisionOkCI;
import com.maus312.polygon.core.executable.commands.precision.StopPrecisionCI;
import com.maus312.polygon.core.executable.commands.precision.StopPrecisionOkCI;
import com.maus312.polygon.core.executable.commands.reset.ResetCurrentPositionCI;
import com.maus312.polygon.core.executable.commands.reset.ResetCurrentPositionOkCI;
import com.maus312.polygon.core.executable.commands.scan.StartScanCI;
import com.maus312.polygon.core.executable.commands.scan.StartScanOkCI;
import com.maus312.polygon.core.executable.commands.scan.StopScanCI;
import com.maus312.polygon.core.executable.commands.scan.StopScanOkCI;
import com.maus312.polygon.core.executable.commands.service.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandManager {
    private static final String COMMAND_STR_SCV_OK = "(STR SCV OK)";
    private static final String COMMAND_STP_SCV_OK = "(STP SCV OK)";
    private static final String COMMAND_STR_LSR_OK = "(STR LSR OK)";
    private static final String COMMAND_STP_LSR_OK = "(STP LSR OK)";
    private static final String COMMAND_STR_SER_OK = "(STR SER OK)";
    private static final String COMMAND_STR_AST_OK = "(STR AST OK)";
    private static final String COMMAND_SCA_XXX_OK = "(SCA {1}\\d{3} OK)";
    private static final String COMMAND_SCA__XX_OK = "(SCA {2}\\d{2} OK)";
    private static final String COMMAND_SCA___X_OK = "(SCA {3}\\d{1} OK)";
    private static final String COMMAND_STR_PRC_OK = "(STR PRC OK)";
    private static final String COMMAND_STP_PRC_OK = "(STP PRC OK)";
    private static final String COMMAND_RES_CPS_OK = "(RES CPS OK)";
    private static final String COMMAND_LXXXAXXX = "(L\\d{1,4}A\\d{1,3}#)";
    private static final String COMMAND_RES_CPS = "(RES CPS)";
    private static final String COMMAND_STR_SCV = "(STR SCV)";
    private static final String COMMAND_STP_SCV = "(STP SCV)";
    private static final String COMMAND_STR_LSR = "(STR LSR)";
    private static final String COMMAND_STP_LSR = "(STP LSR)";
    private static final String COMMAND_STR_SER = "(STR SER)";
    private static final String COMMAND_STR_AST = "(STR AST)";
    private static final String COMMAND_SCA_XXX = "(SCA {1}\\d{3})";
    private static final String COMMAND_SCA__XX = "(SCA {2}\\d{2})";
    private static final String COMMAND_SCA___X = "(SCA {3}\\d{1})";
    private static final String COMMAND_STR_PRC = "(STR PRC)";
    private static final String COMMAND_STP_PRC = "(STP PRC)";

    private List<CommandInterface> commands;

    public CommandManager() {
        commands = new ArrayList<>();
        commands.add(new SetLengthAngle());
        commands.add(new ResetCurrentPositionOkCI());
        commands.add(new ResetCurrentPositionCI());
        commands.add(new SetScanAngleOkCI());
        commands.add(new SetScanAngleCI());
        commands.add(new StartAcrossOkCI());
        commands.add(new StartAcrossCI());
        commands.add(new StartLaserOkCI());
        commands.add(new StartLaserCI());
        commands.add(new StartPrecisionOkCI());
        commands.add(new StartPrecisionCI());
        commands.add(new StartScanOkCI());
        commands.add(new StartScanCI());
        commands.add(new StartSerialOkCI());
        commands.add(new StartSerialCI());
        commands.add(new StopLaserOkCI());
        commands.add(new StopLaserCI());
        commands.add(new StopPrecisionOkCI());
        commands.add(new StopPrecisionCI());
        commands.add(new StopScanOkCI());
        commands.add(new StopScanCI());
        commands.add(new PServiceOnInit());
        commands.add(new PServiceConnected());
        commands.add(new PServiceDisconnected());
        commands.add(new PServiceClearStack());
        commands.add(new PServicePopCommand());
        commands.add(new PServicePrintStack());
    }

    public String getNamesOfCommands() {
        if (commands == null || commands.isEmpty() || commands.size() == 1) {
            return "";
        }
        String namesOfCommands = "";
        for (int i = 0; i <= commands.size() - 2; i++) {
            namesOfCommands = namesOfCommands + commands.get(i).getRegularCommandName() + "|";
        }
        namesOfCommands += commands.get(commands.size() - 1).getRegularCommandName();
        return namesOfCommands;
    }

    public String getNameFromCommandInterface(CommandInterface ci) {
        return ci.getCommandName();
    }

    public CommandInterface getCommandInterfaceFromName(String name) {
        for (CommandInterface ci : commands) {
            if (name.matches(ci.getRegularCommandName())) {
                Class<?> clazz = ci.getClass();
                Object object = new Object();
                Constructor<?> ctor = null;
                try {
                    ctor = clazz.getConstructor();
                    object = ctor.newInstance(new Object[]{});
                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

                if (name.matches(new SetLengthAngle().getRegularCommandName())) {
                    int length = Integer.parseInt(name.substring(name.indexOf('L')+1,name.indexOf('A')));
                    int angle = Integer.parseInt(name.substring(name.indexOf('A')+1,name.indexOf('#')));

                    ((SetLengthAngle) object).setAngle(angle);
                    ((SetLengthAngle) object).setLength(length);


                } else if (name.matches(new SetScanAngleOkCI().getRegularCommandName())) {

                    Pattern pattern = Pattern.compile("(\\d{1,2})");
                    Matcher matcher = pattern.matcher(name);

                    while(matcher.find())
                    {
                        String value = matcher.group();
                        int angle = Integer.parseInt(value);
                        ((SetScanAngleOkCI) object).setAngle(angle);
                        break;
                    }
                } else if (name.matches(new SetScanAngleCI().getRegularCommandName())) {

                    Pattern pattern = Pattern.compile("(\\d{1,2})");
                    Matcher matcher = pattern.matcher(name);

                    while(matcher.find())
                    {
                        String value = matcher.group();
                        int angle = Integer.parseInt(value);
                        ((SetScanAngleCI) object).setAngle(angle);
                        break;
                    }
                }
                return (CommandInterface) object;
            }
        }

        return new CommandInterface() {
            @Override
            public String getCommandName() {
                return "";
            }

            @Override
            public void onAction() {

            }
        };
    }
}

