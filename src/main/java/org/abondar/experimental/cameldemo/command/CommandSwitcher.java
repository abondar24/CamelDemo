package org.abondar.experimental.cameldemo.command;


import org.abondar.experimental.cameldemo.greeter.GreetCommand;

public class CommandSwitcher {

    private final CommandExecutor executor;

    public CommandSwitcher() {
        this.executor = new CommandExecutor();
    }


    public void executeCommand(String cmd){
        try {
            switch (Commands.valueOf(cmd)){
                case GR:
                    GreetCommand greetCommand = new GreetCommand();
                    executor.executeCommand(greetCommand);
                    break;

            }
        } catch (IllegalArgumentException ex){
            System.out.println("Check documentation for command list");
            System.exit(1);
        }

    }
}
