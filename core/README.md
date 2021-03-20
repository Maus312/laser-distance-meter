# Polygon core 
## Conception
The core is based on the command architecture it means that commands is the main programm unit that changes programm state, it changes the UI, Graphics, Scanning process and else. The commands are comming to the core from the MessageSource object, that receive it in the String format, after that it makes command recognizing and translating into the CommandInterface - an object that realize command and makes action with the core. After command is completing, core waits for the next command and so on.
## Requirements 
The polygon core are written Java 8

## Main modules
PC has the next modules:
- Communication
- Executable
- Graphic
- UI
- Locale

Communication module is responsible for sending and receiving command and translating it to the CommandInterface objects.
Executable module is responsible for executing commands, changing state of the core. Also it contains all commands for the core.
Graphic module is responsible for drawing the grid, objects and else on the DrawView.
UI module is responsible for ui. It contains the basic interfaces for the UI elements like Button or TextView. It has  Color wrapper and Style wrapper that are used for describing the style of the UI elements. Also it has UITable - an object that contains references on the all UI elements that are used by the core.
Locale module is responsible for the providing translation for string resources that are used in the programm.

## Command executing process

## WrapperComponentTable
WrapperComponentTable is the table are used for the initializing process.
Basically, the WrapperComponetTable is used for connection PC and PCLI for initalization process. It contains UITable and MessageSource. UITable contains a references on a different UI elements like Buttons, TextView and else that are used for interaction between PCLI and PC. MessageSource is entity that sends commands from PCLI to PC and also recieve command from PC to PCLI.
UITable and MessageSource should be setted from the PCLI to the table, and after that table should be sended to the core throught ExecutableManager.
## Initialization
PC has an WrapperComponetTable, it has the next entities - UITable and MessageSource. UITable contains a references on a different UI elements like Buttons, TextView and else that are used for interaction between PCLI and PC.  MessageSource is entity that sends commands from PCLI to PC and also recieve command from PC to PCLI.
The first action of initialization is creation WrapperComponetTable object and initialization of it. For correct work all values of WrapperComponetTable properties should be initialized. The next step is creation of ExecutableManager. ExecutableManager are responceble for initializing of main blocks like Graphic, UI and else. The constructor of ExecutableManager object are initialized with WrapperComponetTable. The last step of initialization is the sending "CommandServiceOnInit", to the core. This command start initialization all core with using objects from the WrapperComponetTable. The command could be sended throught method "sendCommandToCore" from ExecutableManager class.
After that core is initialized and work in ordinal mode.