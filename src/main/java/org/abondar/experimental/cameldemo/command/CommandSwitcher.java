package org.abondar.experimental.cameldemo.command;


import org.abondar.experimental.cameldemo.file.ContentBasedFileCopier;
import org.abondar.experimental.cameldemo.file.FileCopier;
import org.abondar.experimental.cameldemo.file.FilePrinter;
import org.abondar.experimental.cameldemo.file.FileWriter;
import org.abondar.experimental.cameldemo.ftp.FtpFileWriter;
import org.abondar.experimental.cameldemo.ftp.FtpToJmsCopier;
import org.abondar.experimental.cameldemo.ftp.FtpToJmsWithErrorHandler;
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

                case FP:
                    FilePrinter filePrinter = new FilePrinter();
                    executor.executeCommand(filePrinter);
                    break;

                case FTE:
                    FtpToJmsWithErrorHandler ftj = new FtpToJmsWithErrorHandler();
                    executor.executeCommand(ftj);
                    break;

                case FTJ:
                    FtpToJmsCopier ftpToJmsCopier = new FtpToJmsCopier();
                    executor.executeCommand(ftpToJmsCopier);
                    break;

                case FTW:
                    FtpFileWriter ftpFileWriter = new FtpFileWriter();
                    executor.executeCommand(ftpFileWriter);
                    break;

                case FW:
                    FileWriter fileWriter = new FileWriter();
                    executor.executeCommand(fileWriter);
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
