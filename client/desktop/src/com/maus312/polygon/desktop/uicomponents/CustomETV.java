package com.maus312.polygon.desktop.uicomponents;

import com.maus312.polygon.core.UICallback;
import com.maus312.polygon.core.ui.ColorWrapper;
import com.maus312.polygon.core.ui.WrapperETView;


import javax.swing.*;
import javax.swing.text.Document;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CustomETV extends JTextField implements WrapperETView {
    UICallback callback = new UICallback() {
        @Override
        public void onAction() {

        }
    };

    public CustomETV() {
        addListener();
    }

    public CustomETV(String text) {
        super(text);
        addListener();
    }

    public CustomETV(int columns) {
        super(columns);
        addListener();
    }

    public CustomETV(String text, int columns) {
        super(text, columns);
        addListener();
    }

    public CustomETV(Document doc, String text, int columns) {
        super(doc, text, columns);
        addListener();
    }

    @Override
    public void setBackgroundColor(ColorWrapper color) {

    }

    @Override
    public void setTextColor(ColorWrapper color) {

    }

    @Override
    public String getString() {
        return getText();
    }

    @Override
    public void setCallback(UICallback callback) {
        this.callback = callback;
    }

    void addListener() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                callback.onAction();
            }
        });
    }
}
