package com.maus312.polygon.core.ui;

import com.maus312.polygon.core.UICallback;

public interface WrapperETView {
    void setBackgroundColor(ColorWrapper color);
    void setTextColor(ColorWrapper color);
    String getString();
    void setText(String value);
    void setCallback(UICallback callback);
    void setEnabled(boolean enabled);
}
