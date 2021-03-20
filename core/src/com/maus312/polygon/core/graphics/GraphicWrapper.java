package com.maus312.polygon.core.graphics;

import com.maus312.polygon.core.graphics.basic.*;

public interface GraphicWrapper {
    Point GetWindowSize();
    void drawArc(GArc arc);
    void drawCircle(GCircle circle);
    void drawLine(GLine line);
    void drawRectangle(GRectangle rectangle);
    void drawText(GText text);
    void repaint();
    void invalidate();
}
