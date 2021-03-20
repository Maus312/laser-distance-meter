package com.maus312.polygon.desktop.uicomponents;

import com.maus312.polygon.core.ui.UITable;
import com.maus312.polygon.core.ui.WrapperTView;
import com.maus312.polygon.desktop.MainWrapperStart;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CustomFrame extends JFrame {
    private JPanel mPanel;
    private JPanel lPanel;
    private JPanel rPanel;
    private JPanel statusPanel;
    private JPanel commandButtonsPanel;
    private JPanel additionalButtonsPanel;
    private JPanel connectionPanel;
    private JPanel debugPanel;
    private JTextArea debugOutArea;
    private JTextField debugInField;
    private JButton debugSendCommandButton;
    private Container container;
    private JComboBox<String> uartCombo;
    private JButton connectButton;
    private int preferX = 100;

    public CustomFrame(UITable table) throws HeadlessException {
        super("Polygon Control");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        preferX = (int) (screenSize.height * 0.5);

        container = getContentPane();
        mPanel = new JPanel();
        mPanel.setPreferredSize(new Dimension(2* preferX,preferX));
        container.setLayout(new BorderLayout());
        container.add(mPanel, BorderLayout.CENTER);

        initStatusBar(table);

        lPanel = new CustomDrawable();
        table.setDrawPanelView((CustomDrawable) lPanel);

        initDebug(table);
        initMainViewsInit(table);
        initStatusTextView(table);
        initCommandButtons(table);
        initAdditionalButtons(table);
        initConnectionButtons(table);

        container.add(mPanel);

        mPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;

        constraints.gridx = 0;
        constraints.gridy = 0;
        mPanel.add(lPanel, constraints);

        constraints.gridx = 1;
        mPanel.add(rPanel, constraints);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                Dimension newSize = mPanel.getSize();
                Dimension newSize2 = lPanel.getSize();
                GridBagConstraints fConstraints = ((GridBagLayout) mPanel.getLayout()).getConstraints(lPanel);
                GridBagConstraints sConstraints = ((GridBagLayout) mPanel.getLayout()).getConstraints(rPanel);
                double proportion = ((double) newSize.height) / ((double) newSize.width);

                fConstraints.weightx = proportion;
                sConstraints.weightx = 1 - proportion;
                mPanel.remove(lPanel);
                mPanel.remove(rPanel);
                mPanel.add(lPanel, fConstraints);
                mPanel.add(rPanel, sConstraints);

                revalidate();
                repaint();

                ((CustomDrawable) lPanel).onAction();
            }
        });

        pack();

        rPanel.setSize(300, 300);
    }

    private void initDebug(UITable table) {
        debugPanel = new JPanel();

        JPanel testOutPanel = new JPanel();
        debugOutArea = new JTextArea();
        debugInField = new JTextField();

        JScrollPane jsp = new JScrollPane(debugOutArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        testOutPanel.setLayout(new FlowLayout());
        testOutPanel.add(jsp);

        debugOutArea.setText("Polygon start \n");

        debugPanel.setLayout(new GridBagLayout());
        debugPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;

        debugSendCommandButton = new JButton("Command To Core");
        debugSendCommandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWrapperStart.getInstance().getMessageSource().onReceive(debugInField.getText());
            }
        });

        JButton debugOutClean = new JButton("Clean");
        debugOutClean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                debugOutArea.setText("");
            }
        });

        JTextField debugUartField = new JTextField();

        JButton debugUartButton = new JButton("Command To Hard");
        debugUartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWrapperStart.getInstance().getMessageSource().onTransmit(debugUartField.getText());
            }
        });

        {
            constraints.gridx = 0;
            constraints.gridwidth = 3;
            debugPanel.add(jsp, constraints);
            constraints.gridwidth = 1;

            constraints.gridy = 1;
            constraints.gridx = 0;
            constraints.weightx = 1;
            constraints.weighty = 0;
            debugPanel.add(debugInField, constraints);

            constraints.gridx = 1;
            debugPanel.add(debugSendCommandButton, constraints);

            constraints.gridx = 2;
            debugPanel.add(debugOutClean, constraints);

            constraints.gridwidth = 1;
            constraints.gridy = 2;
            constraints.gridx = 0;
            debugPanel.add(debugUartField, constraints);
            constraints.gridx = 1;
            debugPanel.add(debugUartButton, constraints);

        }
    }

    public JTextArea getDebugOutArea() {
        return debugOutArea;
    }

    private void initMainViewsInit(UITable table) {
        lPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        lPanel.setPreferredSize(new Dimension(1, 1));
        rPanel = new JPanel();
        rPanel.setLayout(new GridBagLayout());
        rPanel.setPreferredSize(new Dimension(1, 1));

        statusPanel = new JPanel();
        commandButtonsPanel = new JPanel();
        additionalButtonsPanel = new JPanel();
        connectionPanel = new JPanel();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;

        {
            constraints.gridy = 0;
            constraints.weighty = 0;
            rPanel.add(statusPanel, constraints);
            constraints.weighty = 1;
        }

        {
            constraints.gridy = 1;
            rPanel.add(commandButtonsPanel, constraints);
        }

        constraints.weighty = 0;

        {
            constraints.gridy = 2;
            rPanel.add(additionalButtonsPanel, constraints);
        }

        {
            constraints.gridy = 3;
            rPanel.add(connectionPanel, constraints);
        }

        if (debugPanel != null){
            constraints.weighty = 1;
            constraints.gridy = 4;
            rPanel.add(debugPanel, constraints);

        }
    }

    private void initStatusTextView(UITable table) {
        statusPanel.setLayout(new GridBagLayout());
        statusPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;

        JLabel mainStatusTVText = new CustomTV("Default:");
        JLabel mainStatusTVValue = new CustomTV("Default:");
        JLabel scanTVText = new CustomTV("Default:");
        JLabel scanTVValue = new CustomTV("Default:");
        JLabel laserTVText = new CustomTV("Default:");
        JLabel laserTVValue = new CustomTV("Default:");
        JLabel modeTVText = new CustomTV("Default:");
        JLabel modeTVValue = new CustomTV("Default:");
        JLabel angleTVText = new CustomTV("Default:");
        JLabel angleTVValue = new CustomTV("Default:");
        JLabel precisionTVText = new CustomTV("Default:");
        JLabel precisionTVValue = new CustomTV("Default:");

        /**/
        {
            constraints.gridx = 0;
            constraints.gridy = 0;
            statusPanel.add(mainStatusTVText, constraints);

            constraints.gridx = 1;
            constraints.gridy = 0;
            statusPanel.add(mainStatusTVValue, constraints);

            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.gridwidth = 2;
            statusPanel.add(new JSeparator(SwingConstants.HORIZONTAL), constraints);
            constraints.gridwidth = 1;
        }

        {
            constraints.gridx = 0;
            constraints.gridy = 3;
            statusPanel.add(scanTVText, constraints);

            constraints.gridx = 1;
            constraints.gridy = 3;
            statusPanel.add(scanTVValue, constraints);

            constraints.gridx = 0;
            constraints.gridy = 4;
            constraints.gridwidth = 2;
            statusPanel.add(new JSeparator(SwingConstants.HORIZONTAL), constraints);
            constraints.gridwidth = 1;
        }

        {
            constraints.gridx = 0;
            constraints.gridy = 5;
            statusPanel.add(laserTVText, constraints);

            constraints.gridx = 1;
            constraints.gridy = 5;
            statusPanel.add(laserTVValue, constraints);

            constraints.gridx = 0;
            constraints.gridy = 6;
            constraints.gridwidth = 2;
            statusPanel.add(new JSeparator(SwingConstants.HORIZONTAL), constraints);
            constraints.gridwidth = 1;
        }

        {
            constraints.gridx = 0;
            constraints.gridy = 7;
            statusPanel.add(modeTVText, constraints);

            constraints.gridx = 1;
            constraints.gridy = 7;
            statusPanel.add(modeTVValue, constraints);

            constraints.gridx = 0;
            constraints.gridy = 8;
            constraints.gridwidth = 2;
            statusPanel.add(new JSeparator(SwingConstants.HORIZONTAL), constraints);
            constraints.gridwidth = 1;
        }

        {
            constraints.gridx = 0;
            constraints.gridy = 9;
            statusPanel.add(angleTVText, constraints);

            constraints.gridx = 1;
            constraints.gridy = 9;
            statusPanel.add(angleTVValue, constraints);

            constraints.gridx = 0;
            constraints.gridy = 10;
            constraints.gridwidth = 2;
            statusPanel.add(new JSeparator(SwingConstants.HORIZONTAL), constraints);
            constraints.gridwidth = 1;
        }

        {
            constraints.gridx = 0;
            constraints.gridy = 11;
            statusPanel.add(precisionTVText, constraints);

            constraints.gridx = 1;
            constraints.gridy = 11;
            statusPanel.add(precisionTVValue, constraints);

            constraints.gridx = 0;
            constraints.gridy = 12;
            constraints.gridwidth = 2;
            statusPanel.add(new JSeparator(SwingConstants.HORIZONTAL), constraints);
            constraints.gridwidth = 1;
        }
        /**/

        table.setMainStatusTVText((WrapperTView) mainStatusTVText);
        table.setMainStatusTVValue((WrapperTView) mainStatusTVValue);
        table.setScanTVText((WrapperTView) scanTVText);
        table.setScanTVValue((WrapperTView) scanTVValue);
        table.setLaserTVText((WrapperTView) laserTVText);
        table.setLaserTVValue((WrapperTView) laserTVValue);
        table.setModeTVText((WrapperTView) modeTVText);
        table.setModeTVValue((WrapperTView) modeTVValue);
        table.setAngleTVText((WrapperTView) angleTVText);
        table.setAngleTVValue((WrapperTView) angleTVValue);
        table.setPrecisionTVText((WrapperTView) precisionTVText);
        table.setPrecisionTVValue((WrapperTView) precisionTVValue);
    }

    void initCommandButtons(UITable table) {
        commandButtonsPanel.setLayout(new GridBagLayout());
        commandButtonsPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;

        JButton scanButton = new CustomButton("Default");
        JButton laserButton = new CustomButton("Default");
        JButton modeButton = new CustomButton("Default");
        JLabel angleTVTextEdit = new CustomTV("Default");
        JTextField angleETVInValue = new CustomETV("Default");
        JButton angleSetButton = new CustomButton("Default");
        JButton precisionButton = new CustomButton("Default");
        JButton resetPositionButton = new CustomButton("Default");
        angleETVInValue.setEnabled(false);
        scanButton.setEnabled(false);
        laserButton.setEnabled(false);
        modeButton.setEnabled(false);
        angleSetButton.setEnabled(false);
        precisionButton.setEnabled(false);
        resetPositionButton.setEnabled(false);
        constraints.gridx = 0;

        {
            constraints.gridy = 1;
            commandButtonsPanel.add(scanButton, constraints);

            constraints.gridy = 2;
            commandButtonsPanel.add(laserButton, constraints);

            constraints.gridy = 3;
            commandButtonsPanel.add(modeButton, constraints);
        }

        {
            constraints.gridy = 4;
            constraints.gridx = 0;
            constraints.gridwidth = 1;
            commandButtonsPanel.add(angleTVTextEdit, constraints);

            constraints.gridx = 1;
            constraints.gridwidth = 1;
            commandButtonsPanel.add(angleETVInValue, constraints);

            constraints.gridx = 2;
            constraints.gridwidth = 1;
            commandButtonsPanel.add(angleSetButton, constraints);
        }

        constraints.gridx = 0;
        constraints.gridwidth = 3;

        {
            constraints.gridy = 5;
            commandButtonsPanel.add(precisionButton, constraints);

            constraints.gridy = 6;
            commandButtonsPanel.add(resetPositionButton, constraints);
        }

        table.setScanButton((CustomButton) scanButton);
        table.setLaserButton((CustomButton) laserButton);
        table.setModeButton((CustomButton) modeButton);
        table.setAngleTVTextEdit((CustomTV) angleTVTextEdit);
        table.setAngleETVInValue((CustomETV) angleETVInValue);
        table.setAngleSetButton((CustomButton) angleSetButton);
        table.setPrecisionButton((CustomButton) precisionButton);
        table.setResetPositionButton((CustomButton) resetPositionButton);
    }

    void initAdditionalButtons(UITable table) {
        additionalButtonsPanel.setLayout(new GridBagLayout());
        additionalButtonsPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;

        JButton recordButton = new CustomButton("Default");
        recordButton.setEnabled(false);
        table.setStartRecordButton((CustomButton) recordButton);
        additionalButtonsPanel.add(recordButton, constraints);
    }

    void initConnectionButtons(UITable table) {
        connectionPanel.setLayout(new GridBagLayout());
        connectionPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;

        JLabel label = new JLabel("Default");

        /*Vector<String> data = new Vector<String>();
        for (int i = 0; i < 3; i++)
            data.add(String.format("COM%d", i));*/

        uartCombo = new JComboBox<String>(MainWrapperStart.getInstance().getMessageSource().getListOfPorts());
        connectButton = new JButton("Connect");
        connectButton.addActionListener(new ActionListener() {
            boolean toggle = false;
            @Override
            public void actionPerformed(ActionEvent e) {

                if(uartCombo.getSelectedItem() instanceof String) {
                    if(!toggle) {
                        MainWrapperStart.getInstance().createPortFromName(((String)uartCombo.getSelectedItem()));
                        connectButton.setText("Disconnect");
                        toggle = true;
                    } else  {
                        MainWrapperStart.getInstance().cleanPort();
                        connectButton.setText("Connect");
                        toggle = false;
                    }

                }
            }
        });

        {
            constraints.gridx = 0;
            connectionPanel.add(label, constraints);

            constraints.gridx = 1;
            connectionPanel.add(uartCombo, constraints);

            constraints.gridx = 2;
            connectionPanel.add(connectButton, constraints);
        }
    }

    void initStatusBar(UITable table) {
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        container.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(mPanel.getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        JLabel statusLabel = new CustomTV("status");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        table.setStatusBarTV((WrapperTView) statusLabel);
        statusPanel.add(statusLabel);
    }

}
