package com.maus312.polygon.desktop;

import com.maus312.polygon.core.WrapperComponentTable;
import com.maus312.polygon.core.executable.ExecutableManager;
import com.maus312.polygon.desktop.uicomponents.CustomFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainWrapperStart implements CustomMessageSource.OnTransmit {
    private static MainWrapperStart wrapperStart;
    private UARTMessageSource messageSource;
    private WrapperComponentTable table;
    private List<String> autoCommands;
    private CustomFrame frame;

    public static void main(String[] args) {
//        Console console = System.console();
//        if(console == null && !GraphicsEnvironment.isHeadless()){
//            String filename = Main.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
//            Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\""});
//        }else{
//            THEMAINCLASSNAMEGOESHERE.main(new String[0]);
//            System.out.println("Program has ended, please type 'exit' to close the console");
//        }

        MainWrapperStart wrapperStart = MainWrapperStart.getInstance();
        wrapperStart.autoCommandsInit();
        wrapperStart.init();
        wrapperStart.work();
    }

    private void autoCommandsInit() {
        autoCommands = new ArrayList<>(40);
        //autoCommands.add();
        autoCommands.add("L772A10#");
        autoCommands.add("L837A30#");
        autoCommands.add("L772A50#");
        autoCommands.add("L707A70#");
        autoCommands.add("L642A90#");
        autoCommands.add("L772A70#");
        autoCommands.add("L837A50#");
        autoCommands.add("L772A30#");
        autoCommands.add("L707A10#");
        autoCommands.add("L772A10#");
        autoCommands.add("L837A30#");
        autoCommands.add("L772A50#");
        autoCommands.add("L707A70#");
        autoCommands.add("L642A90#");
        autoCommands.add("L772A70#");
        autoCommands.add("L837A50#");
        autoCommands.add("L772A30#");
        autoCommands.add("L707A10#");/**/
        autoCommands.add("L710A10#");
        autoCommands.add("L300A30#");
        autoCommands.add("L54A50#");
        autoCommands.add("L740A70#");
        autoCommands.add("L700A90#");
        autoCommands.add("L74A70#");
        autoCommands.add("L92A50#");
        autoCommands.add("L806A30#");
        autoCommands.add("L710A10#");
        autoCommands.add("L710A10#");
        autoCommands.add("L806A30#");
        autoCommands.add("L912A50#");
        autoCommands.add("L740A70#");
        autoCommands.add("L700A90#");
        autoCommands.add("L740A70#");
        autoCommands.add("L912A50#");
        autoCommands.add("L806A30#");
        autoCommands.add("L710A10#");
        /*autoCommands.add("L300A0#");
        autoCommands.add("L300A20#");
        autoCommands.add("L300A40#");
        autoCommands.add("L300A60#");
        autoCommands.add("L300A80#");
        autoCommands.add("L200A90#");
        autoCommands.add("L200A70#");
        autoCommands.add("L200A50#");
        autoCommands.add("L200A30#");
        autoCommands.add("L200A10#");*/
        /*autoCommands.add("L300A0#");
        autoCommands.add("L300A30#");
        autoCommands.add("L300A60#");
        autoCommands.add("L300A90#");
        autoCommands.add("L400A60#");
        autoCommands.add("L400A30#");
        autoCommands.add("L400A0#");
        autoCommands.add("L200A30#");
        autoCommands.add("L200A60#");
        autoCommands.add("L200A90#");
        autoCommands.add("L100A60#");
        autoCommands.add("L100A30#");
        autoCommands.add("L100A0#");*/
        /*autoCommands.add("CommandServiceConnected");
        autoCommands.add("STR SCV");
        autoCommands.add("STR SCV OK");
        autoCommands.add("STP SCV");
        autoCommands.add("STP SCV OK");
        autoCommands.add("STR SCV");
        autoCommands.add("STR SCV OK");

        autoCommands.add("STR LSR");
        autoCommands.add("STR LSR OK");
        autoCommands.add("STP LSR");
        autoCommands.add("STP LSR OK");
        autoCommands.add("STR LSR");
        autoCommands.add("STR LSR OK");

        autoCommands.add("STR PRC");
        autoCommands.add("STR PRC OK");
        autoCommands.add("STP PRC");
        autoCommands.add("STP PRC OK");
        autoCommands.add("STR PRC");
        autoCommands.add("STR PRC OK");*/
    }

    private void work() {
    }

    private void init() {
        table = new CustomTable();

        messageSource = new UARTMessageSource();

        frame = new CustomFrame(table.getUiTable());
        table.setMessageSource(messageSource);

        ExecutableManager executableManager = new ExecutableManager(table);
        executableManager.sendCommandToCore("CommandServiceOnInit");

        frame.setVisible(true);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String data = "";
            try {
                data = reader.readLine();
                if (data.matches("(QUIT)")) {
                    break;
                } else if (data.matches("(AUTO)")) {
                    Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                        int pos = 0;

                        @Override
                        public void run() {
                            if (pos == autoCommands.size()) {
                                cancel();
                                return;
                            }
                            messageSource.onTransmit(autoCommands.get(pos));
                            pos++;
                        }
                    };

                    timer.schedule(task, 0, 1000);

                } else {
                    //if(messageSource == null) {
                        messageSource.onReceive(data);
                   // }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onTransmit(String data) {
        System.out.println(data);
    }

    public static MainWrapperStart getInstance() {
        if (wrapperStart == null) {
            wrapperStart = new MainWrapperStart();
        }
        return wrapperStart;
    }

    public UARTMessageSource getMessageSource() {
        return messageSource;
    }

    public void createPortFromName(String portName) {
        if (messageSource.initPort(portName)) {
            messageSource.onReceive("CommandServiceConnected");
        }
    }

    public void cleanPort() {
        messageSource.cleanPort();
    }

    public CustomFrame getFrame() {
        return frame;
    }
}
