package org.abondar.experimental.cameldemo.command;


import org.abondar.experimental.cameldemo.file.ContentBasedFileCopier;
import org.abondar.experimental.cameldemo.file.FileCopier;
import org.abondar.experimental.cameldemo.greeter.GreetCommand;
import org.abondar.experimental.cameldemo.mina.MinaServer;

public class CommandSwitcher {

    private final CommandExecutor executor;

    public CommandSwitcher() {
        this.executor = new CommandExecutor();
    }


    public void executeCommand(String cmd){
        try {
            switch (Commands.valueOf(cmd)){

                case CFC:
                    ContentBasedFileCopier cfc = new ContentBasedFileCopier();
                    executor.executeCommand(cfc);
                    break;

                case GR:
                    GreetCommand greetCommand = new GreetCommand();
                    executor.executeCommand(greetCommand);
                    break;

                case FC:
                    FileCopier fileCopier = new FileCopier();
                    executor.executeCommand(fileCopier);
                    break;

                case MN:
                    MinaServer mn = new MinaServer();
                    executor.executeCommand(mn);
                    break;

            }
        } catch (IllegalArgumentException ex){
            System.out.println("Check documentation for command list");
            System.exit(1);
        }

    }
}
