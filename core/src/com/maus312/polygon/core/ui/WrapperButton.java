package com.maus312.polygon.core.ui;

import com.maus312.polygon.core.UICallback;

public interface WrapperButton {
    void setText(String value);
    void setColor(ColorWrapper color);
    void setTextSize(StyleWrapper style);
    void setEnabled(boolean active);
    void setCallback(UICallback callback);
}
