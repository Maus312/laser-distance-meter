package com.maus312.polygon.desktop;

import com.maus312.polygon.core.communication.MessageSource;
import jssc.*;

public class UARTMessageSource implements MessageSource {
    CustomMessageSource.OnTransmit onTransmit = new CustomMessageSource.OnTransmit() {
        @Override
        public void onTransmit(String data) {
            try {
                if (serialPort == null) {
                    return;
                }
                serialPort.writeString(data);
                MainWrapperStart.getInstance().getFrame().getDebugOutArea().append(data + " onTransmit \n");
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        }
    };
    SerialPort serialPort;


    public String[] getListOfPorts() {
        return SerialPortList.getPortNames("COM");
    }

    public boolean initPort(String port) {
        if (port.isEmpty()) {
            return false;
        }

        serialPort = new SerialPort(port);
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
            int mask = SerialPort.MASK_RXCHAR;
            serialPort.setEventsMask(mask);
            serialPort.addEventListener(new SerialPortReader());
            return true;
        } catch (SerialPortException ex) {
            System.out.println(ex);
            return false;
        }
    }

    public void cleanPort() {
        if (serialPort == null) {
            return;
        }
        try {
            serialPort.removeEventListener();
            serialPort.closePort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        serialPort = null;

    }

    public void sendDataToProgram(String data) {
        this.onReceive(data);
    }


    @Override
    public void onTransmit(String message) {
        onTransmit.onTransmit(message);
    }


    public void setOnTransmit(CustomMessageSource.OnTransmit onTransmit) {
        this.onTransmit = onTransmit;
    }

    class SerialPortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                    String data = serialPort.readString();
                    onReceive(data);
                    MainWrapperStart.getInstance().getFrame().getDebugOutArea().append(data + " onReceive \n");
                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
    }


    public interface OnTransmit {
        void onTransmit(String data);
    }
}
