package com.maus312.polygon.core.graphics.basic;

import com.maus312.polygon.core.ui.StyleWrapper;

public class GLine {
    private Point start;
    private Point end;
    private StyleWrapper style;

    public GLine() {
    }

    public GLine(Point start, Point end, StyleWrapper style) {
        this.start = start;
        this.end = end;
        this.style = style;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public StyleWrapper getStyle() {
        return style;
    }

    public void setStyle(StyleWrapper style) {
        this.style = style;
    }
}
