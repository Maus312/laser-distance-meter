package com.maus312.polygon.core.graphics.basic;

import com.maus312.polygon.core.ui.StyleWrapper;

public class GRectangle {
    private Point min;
    private Point max;
    private StyleWrapper style;


    public GRectangle(Point min, Point max, StyleWrapper style) {
        this.min = min;
        this.max = max;
        this.style = style;
    }

    public Point getMin() {
        return min;
    }

    public void setMin(Point min) {
        this.min = min;
    }

    public Point getMax() {
        return max;
    }

    public void setMax(Point max) {
        this.max = max;
    }

    public StyleWrapper getStyle() {
        return style;
    }

    public void setStyle(StyleWrapper style) {
        this.style = style;
    }
}
