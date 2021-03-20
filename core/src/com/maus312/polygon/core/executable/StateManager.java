package com.maus312.polygon.core.executable;

import com.maus312.polygon.core.Constants;

import java.util.ArrayList;
import java.util.List;

public class StateManager {
    private List<LayerChangeListener> listeners;
    private static final boolean ENABLED = true;
    private static final boolean DISABLED = false;

    private static final boolean ACROSS = true;
    private static final boolean SERIAL = false;

    private static final boolean HIGH = true;
    private static final boolean LOW = false;

    private static final boolean INCREASING = false;
    private static final boolean DECREASING = true;

    private static final int LAYERS_FOR_ACROSS = 4;
    private static final int LAYERS_FOR_SERIAL = 2;

    private int currentAngle;
    private int stepAngle;

    private boolean isScanning;
    private boolean laserEnabled;
    private boolean scanningMode;
    private boolean precision;

    private boolean direction;

    private int maxLayers;
    private int layer;


    public StateManager() {
        isScanning = DISABLED;
        laserEnabled = DISABLED;
        scanningMode = SERIAL;
        precision = LOW;

        currentAngle = Constants.START_ANGLE;
        stepAngle = 30;

        direction = INCREASING;

        layer = 0;
        maxLayers = LAYERS_FOR_SERIAL;
        listeners = new ArrayList<>();
    }

    private void notifyLayerChangeListeners (int oldLayer) {
        if(listeners.isEmpty()) {
            return;
        }
        for(LayerChangeListener listener : listeners) {
            listener.onChange(oldLayer);
        }
    }

    public void registerLayerChangeListener(LayerChangeListener listener) {
        listeners.add(listener);
    }

    public void deleteLayerChangeListener(LayerChangeListener listener) {
        if(listeners.isEmpty()) {
            return;
        }
        listeners.remove(listener);
    }

    public void clearLayerChangeListeners() {
        listeners.clear();
    }


    private void increaseLayer() {
        notifyLayerChangeListeners(layer);
        layer = (layer + 1) % maxLayers;
        if ((layer % 2) == 0) {
            direction = INCREASING;
        } else {
            direction = DECREASING;
        }
    }

    public void increaseAngle(int newAngle) {
        if (direction == INCREASING && currentAngle > newAngle) {
            increaseLayer();
        } else if (direction == DECREASING && currentAngle <= newAngle) {
            increaseLayer();
        }
        currentAngle = newAngle;
    }

    public void autoIncreaseAngle() {
        if (direction == INCREASING) {
            if ((currentAngle + stepAngle) < Constants.END_ANGLE) {
                currentAngle += stepAngle;
            } else {
                increaseLayer();
                currentAngle = Constants.END_ANGLE;
            }
        } else {
            if ((currentAngle - stepAngle) >= Constants.START_ANGLE) {
                currentAngle -= stepAngle;
            } else {
                if (scanningMode == ACROSS) {
                    currentAngle = stepAngle / 2;
                } else {
                    currentAngle = Constants.START_ANGLE;
                }

                increaseLayer();
            }
        }
    }

    public double predictNextAngle() {
        if (direction == INCREASING) {
            if ((currentAngle + stepAngle) < Constants.END_ANGLE) {
                return (currentAngle + stepAngle);
            } else {
                return Constants.END_ANGLE;
            }
        } else {
            if ((currentAngle - stepAngle) >= Constants.START_ANGLE) {
                return (currentAngle - stepAngle);
            } else {
                if (scanningMode == ACROSS) {
                    return stepAngle / 2;
                }
                return Constants.START_ANGLE;
            }
        }
    }

    public int getLayer() {
        return layer;
    }

    public int getCurrentAngle() {
        return currentAngle;
    }

    public void setLayer(int layer) {
        this.layer = layer;
        if ((this.layer % 2) == 0) {
            direction = INCREASING;
        } else {
            direction = DECREASING;
        }
    }

    public boolean isScanningEnabled() {
        return isScanning;
    }

    public void setScanningEnabled() {
        isScanning = ENABLED;
    }

    public void setScanningDisabled() {
        isScanning = DISABLED;
    }

    public boolean isLaserEnabled() {
        return laserEnabled;
    }

    public void setLaserEnabled() {
        isScanning = ENABLED;
    }

    public void setLaserDisabled() {
        isScanning = DISABLED;
    }

    public boolean isModeAcross() {
        return (scanningMode == ACROSS);
    }

    public boolean isModeSerial() {
        return (scanningMode == SERIAL);
    }

    public void setModeSerial() {
        currentAngle = Constants.START_ANGLE;
        scanningMode = SERIAL;
        maxLayers = LAYERS_FOR_SERIAL;
    }

    public void setModeAcross() {
        currentAngle = stepAngle / 2;
        scanningMode = ACROSS;
        maxLayers = LAYERS_FOR_ACROSS;
    }

    public boolean isHighPrecision() {
        return (precision == HIGH);
    }

    public boolean isLowPrecision() {
        return (precision == LOW);
    }

    public void setPrecisionHigh() {
        precision = HIGH;
    }

    public void setPrecisionLow() {
        precision = LOW;
    }

    public boolean isDecreasingDirection() {
        return (direction == DECREASING);
    }

    public boolean isIncreasingDirection() {
        return (direction == INCREASING);
    }

    public void setDecreasingDirection() {
        direction = DECREASING;
    }

    public void setIncreasingDirection() {
        direction = INCREASING;
    }

    public void setAngle(int angle) {
        currentAngle = angle;
    }

    public void setStepAngle(int stepAngle) {
        this.stepAngle = stepAngle;
    }

    interface LayerChangeListener {
        void onChange(int oldLayer);
    }
}
