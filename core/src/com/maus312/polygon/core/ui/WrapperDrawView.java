package com.maus312.polygon.core.ui;

import com.maus312.polygon.core.UICallback;
import com.maus312.polygon.core.graphics.basic.*;

public interface WrapperDrawView {
    Point getViewSize();
    void drawLine(GLine line);
    void drawText(GText text);
    void drawArc(GArc arc);
    void drawCircle(GCircle circle);
    void drawRectangle(GRectangle rectangle);
    void invalidate();
    void repaint();
    void setCallback(UICallback callback);

}
