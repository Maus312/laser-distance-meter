package com.maus312.polygon.core.graphics.basic;

import com.maus312.polygon.core.ui.StyleWrapper;

public class GCircle {
    private Point c;
    private double size;
    private StyleWrapper style;

    public GCircle(Point c, double size, StyleWrapper style) {
        this.c = c;
        this.size = size;
        this.style = style;
    }

    public Point getC() {
        return c;
    }

    public void setC(Point c) {
        this.c = c;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public StyleWrapper getStyle() {
        return style;
    }

    public void setStyle(StyleWrapper style) {
        this.style = style;
    }
}
