package org.abondar.experimental.cameldemo;

import org.abondar.experimental.cameldemo.command.CommandSwitcher;

public class Main {

    public static void main(String[] args) {
        CommandSwitcher cs = new CommandSwitcher();

        String cmd = args[0].toUpperCase();

        cs.executeCommand(cmd);
    }
}
