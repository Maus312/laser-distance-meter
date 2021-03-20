package com.maus312.polygon.desktop;

import com.maus312.polygon.core.communication.MessageSource;

public class CustomMessageSource implements MessageSource {
    OnTransmit onTransmit = new OnTransmit() {
        @Override
        public void onTransmit(String data) {
            return;
        }
    };

    public void sendDataToProgram(String data) {
        this.onReceive(data);
    }

    @Override
    public void onTransmit(String message) {
        onTransmit.onTransmit(message);
    }

    public void setOnTransmit(OnTransmit onTransmit) {
        this.onTransmit = onTransmit;
    }

    public interface OnTransmit {
        void onTransmit(String data);
    }
}
