package com.maus312.polygon.desktop.uicomponents;

import com.maus312.polygon.core.ui.ColorWrapper;
import com.maus312.polygon.core.ui.StyleWrapper;
import com.maus312.polygon.core.ui.WrapperTView;

import javax.swing.*;

public class CustomTV extends JLabel implements WrapperTView {
    public CustomTV(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
    }

    public CustomTV(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }

    public CustomTV(String text) {
        super(text);
    }

    public CustomTV(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
    }

    public CustomTV(Icon image) {
        super(image);
    }

    public CustomTV() {
    }

    @Override
    public void setTextColor(ColorWrapper color) {

    }

    @Override
    public void setTextSize(StyleWrapper style) {

    }
}
