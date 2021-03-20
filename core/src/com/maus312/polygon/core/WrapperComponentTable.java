package com.maus312.polygon.core;

import com.maus312.polygon.core.communication.MessageSource;
import com.maus312.polygon.core.ui.UITable;

public abstract class WrapperComponentTable {
    private MessageSource messageSource;
    private UITable uiTable;

    public WrapperComponentTable() {
        uiTable = new UITable();
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public UITable getUiTable() {
        return uiTable;
    }

    public void setUiTable(UITable uiTable) {
        this.uiTable = uiTable;
    }
}
