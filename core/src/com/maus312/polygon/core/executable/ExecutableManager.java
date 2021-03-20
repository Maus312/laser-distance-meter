package com.maus312.polygon.core.executable;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.Constants;
import com.maus312.polygon.core.WrapperComponentTable;
import com.maus312.polygon.core.communication.*;
import com.maus312.polygon.core.MainSingle;
import com.maus312.polygon.core.executable.commands.CommandManager;
import com.maus312.polygon.core.graphics.BasicGraphicCore;
import com.maus312.polygon.core.graphics.BasicGraphicWrapper;
import com.maus312.polygon.core.graphics.GraphicCore;
import com.maus312.polygon.core.graphics.GraphicWrapper;
import com.maus312.polygon.core.graphics.basic.PolarPoint;
import com.maus312.polygon.core.locale.LanguageValue;
import com.maus312.polygon.core.locale.LocaleManager;
import com.maus312.polygon.core.utils.PointStorage;
import com.maus312.polygon.core.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ExecutableManager implements StateManager.LayerChangeListener {
    double currentAngle;
    double currentLength;
    private List<PointStorage> points;


    CommandManager commandManager;
    StateManager stateManager;

    public ExecutableManager() {
        commandManager = new CommandManager();
        MainSingle single = MainSingle.getInstance();
        MessageSource messageSource = new BasicMessageSource();
        single.setMessageSource(messageSource);
        TranslatorController tS = new BasicTranslatorController();
        single.setTranslatorController(tS);
        CommandController cC = new BasicCommandController();
        single.setCommandController(cC);
        LocaleManager localeManager = new LocaleManager(LanguageValue.Eng);
        single.setLocaleManager(localeManager);
        stateManager = new StateManager();
        single.setStateManager(stateManager);
        stateManager.registerLayerChangeListener(this);
        single.setExecutableManager(this);

        points = new ArrayList<>(10);
    }

    public ExecutableManager(WrapperComponentTable table) {
        this();
        setComponentTable(table);
    }

    public void setComponentTable(WrapperComponentTable table) {
        MainSingle.getInstance().setTable(table);
        MainSingle.getInstance().setUiTable(table.getUiTable());
        GraphicWrapper graphicWrapper = new BasicGraphicWrapper();
        MainSingle.getInstance().setGraphicWrapper(graphicWrapper);
        GraphicCore graphicCore = new BasicGraphicCore();
        MainSingle.getInstance().setGraphicCore(graphicCore);
    }

    public void sendCommandToCore(String command) {
        MainSingle.getInstance().getMessageSource().onReceive(command);
    }

    public String getNamesOfCommands() {
        return commandManager.getNamesOfCommands();
    }

    public CommandInterface getCommandInterfaceFromName(String name) {
        return commandManager.getCommandInterfaceFromName(name);
    }

    public String getNameFromCommandInterface(CommandInterface cI) {
        return commandManager.getNameFromCommandInterface(cI);
    }

    public void sendPolarPoint(PolarPoint polar) {
        Comparator comparator;

        if (!points.isEmpty()) {
            if (stateManager.isIncreasingDirection()) {
                comparator = (int a, int b) -> {
                    return (a <= b);
                };
            } else {
                comparator = (int a, int b) -> {
                    return (a > b);
                };
            }

            PointStorage pointStorage = points.get(0);

            if (pointStorage.getLayer() == stateManager.getLayer()) {
                ;
                points.removeAll(getSublistForDeletingFromLayer(pointStorage.getLayer(),polar.getRawAngle(), comparator));
            }
        }

        stateManager.increaseAngle(polar.getRawAngle());
        boolean isNeedToAdd = Utils.checkLengthThresholdFromAngleAndPolar(polar.getAngle(), polar, true);
        if (isNeedToAdd) {
            points.add(new PointStorage(polar.getCustomCartesianPoint(),
                    MainSingle.getInstance().getGraphicCore().convertFromRealToView(polar.getCustomCartesianPoint()),
                    polar,
                    stateManager.getLayer()));
        }

        currentLength = polar.getRawRadius();
        currentAngle = polar.getRawAngle();

        MainSingle.getInstance().getUiTable().getStatusBarTV().setText("Length = " +
                ((int) currentLength) + "; Angle = " + ((int) currentAngle));

        MainSingle.getInstance().getGraphicCore().onUpdate();

    }

    public List<PointStorage> getPoints() {
        return points;
    }

    public List<PointStorage> getSublistForDeletingFromLayer(int layer, int angle, Comparator action) {
        List<PointStorage> pointStorages = new ArrayList<>();
        for (PointStorage pointStorage : points) {
            if (layer != pointStorage.getLayer()) {
                return pointStorages;
            }
            if (action.compare(pointStorage.getOrigin().getRawAngle(), angle) && !pointStorage.isLocked()) {
                pointStorages.add(pointStorage);
            }
        }
        return pointStorages;
    }

    public void resetPointsAndLayer() {
        points.clear();

        currentAngle = 0;
        currentLength = 0;

        stateManager.setLayer(0);
        stateManager.setAngle(Constants.START_ANGLE);

        MainSingle.getInstance().getGraphicCore().onUpdate();

    }

    @Override
    public void onChange(int oldLayer) {
        if (points.isEmpty()) {
            return;
        }
        for (PointStorage pointStorage : points) {
            if (pointStorage.getLayer() == oldLayer){
                pointStorage.setUnlocked();
            }
        }
    }

    private interface Comparator {
        boolean compare(int a, int b);
    }
}
