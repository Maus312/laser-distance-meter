package com.maus312.polygon.core.graphics;

import com.maus312.polygon.core.MainSingle;
import com.maus312.polygon.core.UICallback;
import com.maus312.polygon.core.graphics.basic.*;
import com.maus312.polygon.core.ui.WrapperDrawView;

public class BasicGraphicWrapper implements GraphicWrapper {
    WrapperDrawView drawable;
//TODO: Add init to execManager;
//TODO: Add to MainSingle;
    public BasicGraphicWrapper() {
        drawable = MainSingle.getInstance().getUiTable().getDrawPanelView();
        MainSingle.getInstance().getUiTable().getDrawPanelView().setCallback(new UICallback() {
            @Override
            public void onAction() {
                MainSingle.getInstance().getGraphicCore().onResize(drawable.getViewSize());
            }
        });
    }

    @Override
    public Point GetWindowSize() {
        return drawable.getViewSize();
    }

    @Override
    public void drawArc(GArc arc) {
        drawable.drawArc(arc);
    }

    @Override
    public void drawCircle(GCircle circle) {
        drawable.drawCircle(circle);
    }

    @Override
    public void drawLine(GLine line) {
        drawable.drawLine(line);
    }

    @Override
    public void drawRectangle(GRectangle rectangle) {
        drawable.drawRectangle(rectangle);
    }

    @Override
    public void drawText(GText text) {
        drawable.drawText(text);
    }

    @Override
    public void repaint() {
        drawable.repaint();
    }

    @Override
    public void invalidate() {
        drawable.invalidate();
    }

    public void setDrawable(WrapperDrawView drawable) {
        this.drawable = drawable;

        MainSingle.getInstance().getUiTable().getDrawPanelView().setCallback(new UICallback() {
            @Override
            public void onAction() {
                MainSingle.getInstance().getGraphicCore().onResize(drawable.getViewSize());
            }
        });
    }
}
