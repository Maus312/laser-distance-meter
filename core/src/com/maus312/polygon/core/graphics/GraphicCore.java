package com.maus312.polygon.core.graphics;

import com.maus312.polygon.core.graphics.basic.Point;
import com.maus312.polygon.core.utils.PointStorage;

import java.util.List;

public interface GraphicCore {
    Point getWindowSize();
    Point convertFromRealToView(Point realPoint);
    void drawRay(double angle, double difAngle);
    void drawGrid();
    void drawTarget(Point position, int layer);
    void drawTargets(List<PointStorage> pointStorageList);
    void onResize(Point newSize);
    void onUpdate();
    void invalidate();
}
