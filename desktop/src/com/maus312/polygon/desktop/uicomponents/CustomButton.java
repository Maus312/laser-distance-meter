package com.maus312.polygon.desktop.uicomponents;

import com.maus312.polygon.core.UICallback;
import com.maus312.polygon.core.ui.ColorWrapper;
import com.maus312.polygon.core.ui.StyleWrapper;
import com.maus312.polygon.core.ui.WrapperButton;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomButton extends JButton implements WrapperButton, ActionListener {
    UICallback callback = new UICallback() {
        @Override
        public void onAction() {

        }
    };

    public CustomButton() {
        initListener();
    }

    public CustomButton(Icon icon) {
        super(icon);
        initListener();
    }

    public CustomButton(String text) {
        super(text);
        initListener();
    }

    public CustomButton(Action a) {
        super(a);
        initListener();
    }

    public CustomButton(String text, Icon icon) {
        super(text, icon);
        initListener();
    }

    @Override
    public void setColor(ColorWrapper color) {

    }

    @Override
    public void setTextSize(StyleWrapper style) {

    }

    @Override
    public void setCallback(UICallback callback) {
        this.callback = callback;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        callback.onAction();
    }

    void initListener() {
        addActionListener(this::actionPerformed);
    }
}
