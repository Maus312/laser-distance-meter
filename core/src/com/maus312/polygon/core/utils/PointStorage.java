package com.maus312.polygon.core.utils;

import com.maus312.polygon.core.graphics.basic.Point;
import com.maus312.polygon.core.graphics.basic.PolarPoint;

/**
 * An entity that describes a simple storage that unite polar coordinate,
 * cartesian value and it representation.
 */
public class PointStorage {
    Point realPoint;
    Point repPoint;
    PolarPoint origin;
    int layer;
    boolean isLocked;

    public PointStorage() {
        realPoint = new Point();
        realPoint = new Point();
        origin = new PolarPoint();
        layer = 0;
    }

    public PointStorage(Point realPoint, Point repPoint, PolarPoint origin, int layer) {
        this.realPoint = realPoint;
        this.repPoint = repPoint;
        this.origin = origin;
        this.layer = layer;
        isLocked = true;
    }

    public Point getRealPoint() {
        return realPoint;
    }

    public void setRealPoint(Point realPoint) {
        this.realPoint = realPoint;
    }

    public Point getRepPoint() {
        return repPoint;
    }

    public void setRepPoint(Point repPoint) {
        this.repPoint = repPoint;
    }

    public PolarPoint getOrigin() {
        return origin;
    }

    public void setOrigin(PolarPoint origin) {
        this.origin = origin;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked() {
        isLocked = true;
    }

    public void setUnlocked() {
        isLocked = false;
    }

}
