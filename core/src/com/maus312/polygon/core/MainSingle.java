package com.maus312.polygon.core;

import com.maus312.polygon.core.communication.CommandController;
import com.maus312.polygon.core.communication.MessageSource;
import com.maus312.polygon.core.communication.TranslatorController;
import com.maus312.polygon.core.executable.ExecutableManager;
import com.maus312.polygon.core.executable.StateManager;
import com.maus312.polygon.core.graphics.GraphicCore;
import com.maus312.polygon.core.graphics.GraphicWrapper;
import com.maus312.polygon.core.locale.LocaleManager;
import com.maus312.polygon.core.ui.UITable;

public class MainSingle {
    private static MainSingle instance;
    private MessageSource messageSource;
    private TranslatorController translatorController;
    private CommandController commandController;
    private CoreLogger logger;
    private ExecutableManager executableManager;
    private WrapperComponentTable table;
    private UITable uiTable;
    private LocaleManager localeManager;
    private StateManager stateManager;
    private GraphicCore graphicCore;
    private GraphicWrapper graphicWrapper;

    private  MainSingle () {
    }

    public static MainSingle getInstance() {
        if (instance == null) {
            //synchronized block to remove overhead
            synchronized (MainSingle.class) {
                if (instance == null) {
                    // if instance is null, initialize
                    instance = new MainSingle();
                }

            }
        }
        return instance;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public TranslatorController getTranslatorController() {
        return translatorController;
    }

    public void setTranslatorController(TranslatorController translatorController) {
        this.translatorController = translatorController;
    }

    public CommandController getCommandController() {
        return commandController;
    }

    public void setCommandController(CommandController commandController) {
        this.commandController = commandController;
    }

    public CoreLogger getLogger() {
        return logger;
    }

    public void setLogger(CoreLogger logger) {
        this.logger = logger;
    }

    public ExecutableManager getExecutableManager() {
        return executableManager;
    }

    public void setExecutableManager(ExecutableManager executableManager) {
        this.executableManager = executableManager;
    }

    public WrapperComponentTable getTable() {
        return table;
    }

    public void setTable(WrapperComponentTable table) {
        this.table = table;
    }

    public UITable getUiTable() {
        return uiTable;
    }

    public void setUiTable(UITable uiTable) {
        this.uiTable = uiTable;
    }

    public LocaleManager getLocaleManager() {
        return localeManager;
    }

    public void setLocaleManager(LocaleManager localeManager) {
        this.localeManager = localeManager;
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    public void setStateManager(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public GraphicCore getGraphicCore() {
        return graphicCore;
    }

    public void setGraphicCore(GraphicCore graphicCore) {
        this.graphicCore = graphicCore;
    }

    public GraphicWrapper getGraphicWrapper() {
        return graphicWrapper;
    }

    public void setGraphicWrapper(GraphicWrapper graphicWrapper) {
        this.graphicWrapper = graphicWrapper;
    }

    //TODO set "Template of creation, or something"
//TODO To exec add coreFactory();


// getMessageSource();
   // getTranslatorController();
   // getCommandController ();
   // getGraphicCore();
   // getUICore();
   // getExecCore();
}
