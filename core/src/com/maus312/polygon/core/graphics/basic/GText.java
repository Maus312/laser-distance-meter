package com.maus312.polygon.core.graphics.basic;

import com.maus312.polygon.core.ui.StyleWrapper;

public class GText {
    private Point position;
    private String text;
    private StyleWrapper style;

    public GText(Point position, String text, StyleWrapper style) {
        this.position = position;
        this.text = text;
        this.style = style;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public StyleWrapper getStyle() {
        return style;
    }

    public void setStyle(StyleWrapper style) {
        this.style = style;
    }
}
