package com.maus312.polygon.desktop.uicomponents;

import com.maus312.polygon.core.UICallback;
import com.maus312.polygon.core.graphics.basic.*;
import com.maus312.polygon.core.ui.StyleWrapper;
import com.maus312.polygon.core.ui.WrapperDrawView;
import com.maus312.polygon.core.graphics.basic.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;

public class CustomDrawable extends JPanel implements WrapperDrawView {
    StyleWrapper prevWrapper;
    List<GArc> arcs = new ArrayList<>();
    List<GCircle> circles = new ArrayList<>();
    List<GLine> lines = new ArrayList<>();
    List<GRectangle> rectangles = new ArrayList<>();
    List<GText> texts = new ArrayList<>();

    UICallback callback = new UICallback() {
        @Override
        public void onAction() {

        }
    };

    public CustomDrawable(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public CustomDrawable(LayoutManager layout) {
        super(layout);
    }

    public CustomDrawable(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public CustomDrawable() {
    }

    @Override
    public Point getViewSize() {
        Dimension dimension = getSize();
        return new Point(dimension.width, dimension.height);
    }

    @Override
    public void drawLine(GLine line) {
        lines.add(line);
    }

    @Override
    public void drawText(GText text) {
        texts.add(text);
    }

    @Override
    public void drawArc(GArc arc) {
        arcs.add(arc);
    }

    @Override
    public void drawCircle(GCircle circle) {
        circles.add(circle);
    }

    @Override
    public void drawRectangle(GRectangle rectangle) {
        rectangles.add(rectangle);
    }

    @Override
    public void setCallback(UICallback callback) {
        this.callback = callback;
    }

    public void onAction() {
        callback.onAction();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!(g instanceof Graphics2D)) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        if (!lines.isEmpty()) {
            for (GLine line : lines) {
                Point start = line.getStart();
                Point end = line.getEnd();
                styleWrapperToGraphics(line.getStyle(), g2).drawLine(start.getIntX(),
                        start.getIntY(),
                        end.getIntX(),
                        end.getIntY());
            }

            lines.clear();
        }

        if (!arcs.isEmpty()) {
            for (GArc arc : arcs) {
                Point min = arc.getMin();
                Point max = arc.getMax();
                styleWrapperToGraphics(arc.getStyle(), g2).draw(new Arc2D.Double(min.getIntX(),
                        min.getIntY(),
                        (max.getIntX() - min.getIntX()),
                        (max.getIntY() - min.getIntY()),
                        arc.getStartAngle(),
                        -arc.getEndAngle(),
                        Arc2D.OPEN));
            }

            arcs.clear();
        }

        if (!circles.isEmpty()) {
            for (GCircle circle : circles) {
                Point position = circle.getC();
                int size = (int) circle.getSize();
                styleWrapperToGraphics(circle.getStyle(), g2).drawOval(position.getIntX() - size,
                        position.getIntY() - size,
                        2 * size,
                        2 * size);
            }

            circles.clear();
        }

        if (!texts.isEmpty()) {
            for (GText text : texts) {
                Point position = text.getPosition();
                styleWrapperToGraphics(text.getStyle(), g2).drawString(text.getText(),
                        position.getFloatX(),
                        position.getFloatY());
            }

            texts.clear();
        }

        if (!rectangles.isEmpty()) {
            for (GRectangle rectangle : rectangles) {
                Point min = rectangle.getMin();
                Point max = rectangle.getMax();
                styleWrapperToGraphics(rectangle.getStyle(), g2).drawRect(min.getIntX(),
                        min.getIntY(),
                        (max.getIntX() - min.getIntX()),
                        (max.getIntY() - min.getIntY()));
            }

            rectangles.clear();
        }
    }

    Graphics2D styleWrapperToGraphics(StyleWrapper wrapper, Graphics2D graphics) {
        if(wrapper == prevWrapper) {
            return graphics;
        }
        prevWrapper = wrapper;
        graphics.setColor(new Color(wrapper.getColor().getR(),
                wrapper.getColor().getG(),
                wrapper.getColor().getB()));
        graphics.setStroke(new BasicStroke((float) wrapper.getWidth()));
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, (int) wrapper.getTextSize()));
        return graphics;
    }
}
