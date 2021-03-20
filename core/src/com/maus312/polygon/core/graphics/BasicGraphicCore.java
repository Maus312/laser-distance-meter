package com.maus312.polygon.core.graphics;

import com.maus312.polygon.core.Constants;
import com.maus312.polygon.core.MainSingle;
import com.maus312.polygon.core.graphics.basic.*;
import com.maus312.polygon.core.ui.ColorWrapper;
import com.maus312.polygon.core.ui.StyleWrapper;
import com.maus312.polygon.core.utils.PointStorage;
import com.maus312.polygon.core.utils.Utils;

import java.util.List;

public class BasicGraphicCore implements GraphicCore {
    private GraphicWrapper wrapper;
    private StyleWrapper gridStyle;
    private StyleWrapper rayStyle;
    private StyleWrapper firstLayerPoint;
    private StyleWrapper secondLayerPoint;
    private StyleWrapper thirdLayerPoint;
    private StyleWrapper fourthLayerPoint;

    private Point viewSize = new Point(100, 100);
    private Point paddingViewSize = new Point(100, 100);

    private int padding = 18;
    private int targetSize = 7;

    public BasicGraphicCore() {
        wrapper = MainSingle.getInstance().getGraphicWrapper();
        gridStyle = new StyleWrapper(ColorWrapper.getBLACKColor(), true, 1, 12);
        rayStyle = new StyleWrapper(ColorWrapper.getREDColor(), true, 5, 12);
        firstLayerPoint = new StyleWrapper(ColorWrapper.getBLUEColor(), true, 3, 12);
        secondLayerPoint = new StyleWrapper(ColorWrapper.getCYANColor(), true, 3, 12);
        thirdLayerPoint = new StyleWrapper(ColorWrapper.getMAGENTAColor(), true, 3, 12);
        fourthLayerPoint = new StyleWrapper(ColorWrapper.getGREENColor(), true, 3, 12);
    }

    @Override
    public Point getWindowSize() {
        return viewSize;
    }

    @Override
    public Point convertFromRealToView(Point realPoint) {
        Point viewPoint = new Point();
        Point padSize = new Point(viewSize.getX() - (2 * padding),viewSize.getY() - (2 * padding));

        if (viewSize.getIntX() != 0 && viewSize.getIntY() != 0) {
            viewPoint.setX((padSize.getX() + ((realPoint.getX() / Constants.REAL_X) * padSize.getIntX())) + padding);
            viewPoint.setY((padSize.getY() - (realPoint.getY() / Constants.REAL_Y) * padSize.getIntY()) + padding);
        }

        return viewPoint;
    }

    @Override
    public void drawRay(double angle, double difAngle) {
        if (difAngle < 1) {
            wrapper.drawLine(new GLine(Utils.getPointSquareRayFromCenter(paddingViewSize, paddingViewSize.getIntX() - padding, Math.toRadians(angle), true),
                    new Point(paddingViewSize.getIntX(), paddingViewSize.getIntY()),
                    rayStyle));
        } else {
            wrapper.drawLine(new GLine(Utils.getPointSquareRayFromCenter(paddingViewSize, paddingViewSize.getIntX() - padding, Math.toRadians(angle + difAngle), true),
                    new Point(paddingViewSize.getIntX(), paddingViewSize.getIntY()),
                    rayStyle));
            wrapper.drawLine(new GLine(Utils.getPointSquareRayFromCenter(paddingViewSize, paddingViewSize.getIntX() - padding, Math.toRadians(angle - difAngle), true),
                    new Point(paddingViewSize.getIntX(), paddingViewSize.getIntY()),
                    rayStyle));
        }
    }

    @Override
    public void drawGrid() {
        drawLines(8);
        drawRadius(180, 90, (paddingViewSize.getIntX() - padding) / 4);
    }

    private void drawLines(int quantities) {
        wrapper.drawLine(new GLine(new Point(padding, padding),
                new Point(paddingViewSize.getIntX(), padding),
                gridStyle));
        wrapper.drawLine(new GLine(new Point(padding, padding),
                new Point(padding, paddingViewSize.getIntY()),
                gridStyle));
        for (float i = 0; i < Math.PI / 2f; i += (Math.PI / 2f / ((float) quantities))) {
            wrapper.drawLine(new GLine(Utils.getPointSquareRayFromCenter(paddingViewSize, paddingViewSize.getIntX() - padding, i, true),
                    new Point(paddingViewSize.getIntX(), paddingViewSize.getIntY()),
                    gridStyle));

            wrapper.drawText(new GText(Utils.getPointSquareRayFromCenter(paddingViewSize, paddingViewSize.getIntX() - (padding / 2), i, true),
                    Utils.getDoubleString(Math.toDegrees(i), 2),
                    gridStyle));
        }
    }

    private void drawRadius(float startAngle, float endAngle, float step) {
        int maxI = (int) Math.floor((paddingViewSize.getIntX()) / step);
        for (int i = 0; i <= maxI; i++) {
            GArc arc = new GArc(new Point(paddingViewSize.getIntX() - step * i, paddingViewSize.getIntY() - step * i),
                    new Point(paddingViewSize.getIntX() + step * i, paddingViewSize.getIntY() + step * i),
                    startAngle,
                    endAngle,
                    gridStyle);
            wrapper.drawArc(arc);
            wrapper.drawText(new GText(new Point(paddingViewSize.getIntX() - step * i, paddingViewSize.getIntY() + padding / 2),
                    Integer.toString(Constants.REAL_X / maxI * i),
                    gridStyle));

            wrapper.drawText(new GText(new Point(paddingViewSize.getIntX(), paddingViewSize.getIntY() - step * i),
                    Integer.toString(Constants.REAL_Y / maxI * i),
                    gridStyle));
        }
    }

    @Override
    public void drawTarget(Point position, int layer) {
        wrapper.drawCircle(new GCircle(position,
                targetSize,
                getLayerStyle(layer)));
    }

    @Override
    public void drawTargets(List<PointStorage> pointStorageList) {
        if (pointStorageList.isEmpty()) {
            return;
        }

        int layer = pointStorageList.get(0).getLayer();
        StyleWrapper style = getLayerStyle(layer);

        for(PointStorage pointStorage : pointStorageList) {
            if(layer != pointStorage.getLayer()) {
                layer = pointStorage.getLayer();
                style = getLayerStyle(layer);
            }

            wrapper.drawCircle(new GCircle(pointStorage.getRepPoint(),
                    targetSize,
                    style));
        }
    }

    private StyleWrapper getLayerStyle(int layer){
        StyleWrapper style;
        switch (layer){
            case 0:
                style = firstLayerPoint;
                break;
            case 1:
                style = secondLayerPoint;
                break;
            case 2:
                style = thirdLayerPoint;
                break;
            case 3:
                style = fourthLayerPoint;
                break;
            default:
                style = firstLayerPoint;
                break;
        }
        return style;
    }



    @Override
    public void onResize(Point newSize) {
        viewSize = newSize;
        paddingViewSize = new Point(newSize.getX() - padding, newSize.getY() - padding);
        for (PointStorage points : MainSingle.getInstance().getExecutableManager().getPoints()) {
            points.setRepPoint(convertFromRealToView(points.getRealPoint()));
        }
        onUpdate();
    }

    @Override
    public void onUpdate() {
        drawGrid();
        drawTargets(MainSingle.getInstance().getExecutableManager().getPoints());
        drawRay(MainSingle.getInstance().getStateManager().getCurrentAngle(), 2);
        wrapper.repaint();
    }

    @Override
    public void invalidate() {
        wrapper.invalidate();
    }

    public void setWrapper(GraphicWrapper wrapper) {
        this.wrapper = wrapper;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }
}
