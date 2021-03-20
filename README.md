# Polygon

## Conception

Polygon is the project that demonstrates using of self made lidar for security purposes. Or in other words - using radar for detecting violators. 
Scan angle
Across serial mode 
Serial scan mode
precision mode
Scanning process

## Requirements 
Project use the next software and hardware: 
- Java 8
- Arduino Nano
- VL53L0X-V2 - range meter
- UART to USB convertor
- UART to BLUETOOTH UART convertor

## General instances
The project have the next generl instances:
- Polygon device (PD)
- Polygon core (PC)
- Polygon client (PCLI)
- Polygon application (PAPP)

Polygon device (PD) - is the device that does scanning process and send it throught UART or Bluetooth UART to the PAPP.
Polygon core(PC) - part of PAPP, it contain cross-platform core that realizes functions of communication and processing data from PCLI.
Polygon client (PCLI) - part of PAPP, it contain platform depended function for communicated with PD and platform depended UI.
Polygon application (PAPP) - software for interaction with PD and displaying scanned values.

## Commands
All commands could be divided into two groups **General** - between PD and PAPP and **Service** - between PCLI an PC.
Main part of general commands required confirmation of it receiving and for confirmation it send back command + OK 

### General
Commands for interaction between PD and PAPP
- SCA XXX      - set scan angle
- SCA  XX      - set scan angle
- SCA   X      - set scan angle
- SCA XXX OK   - set scan angle confirmation
- SCA  XX OK   - set scan angle confirmation
- SCA   X OK   - set scan angle confirmation
- STR LSR      - enable laser
- STR LSR OK   - enable laser confirmation
- STP LSR      - disable laser
- STP LSR OK   - disable laser confirmation
- LXXXXAXXX#   - new value of lenght and angle
- STR AST      - start "Across serial scan mode"
- STR AST OK   - start "Across serial scan mode" confirmation
- STR SER      - start "Serial scan mode"
- STR SER OK   - start "Serial scan mode" confirmation
- STR PRC      - enable precision mode
- STR PRC OK   - enable precision mode confirmation
- STP PRC      - disable precision mode
- STP PRC OK   - disable precision mode confirmation
- RES CPS      - reset current scanning, and start scanning again
- RES CPS OK   - reset current scanning, and start scanning again
- STR SCV      - start scanning process
- STR SCV OK   - start scanning process confirmation
- STP SCV      - stop scanning process
- STP SCV OK   - stop scanning process confirmation

### Service
Service commands for debugging and iteraction between PCLI and PC. 

- CommandClearStack               - clear queue of commands in the PC.
- CommandServiceConnected         - scanning device was connected, PC should enable all functional for controlling PD.
- CommandServiceDisconnected      - scanning device was disconnected, PC should disable all functional for controlling PD.
- CommandServiceOnInit            - init core, should be called when, WrapperComponentTable is initialized.
- CommandPopFirst                 - delete first command of queue.
- CommandPrintStack               - print all commands in the queue to the console.
