package org.abondar.experimental.cameldemo.command;


import org.abondar.experimental.cameldemo.concurency.ConcurrentFileProcessor;
import org.abondar.experimental.cameldemo.dataconversion.command.TextToXmlXstream;
import org.abondar.experimental.cameldemo.dataconversion.command.TextToCSV;
import org.abondar.experimental.cameldemo.dataconversion.command.TextToCsvBindy;
import org.abondar.experimental.cameldemo.dataconversion.command.TextToCustomFormat;
import org.abondar.experimental.cameldemo.dataconversion.command.TextToJson;
import org.abondar.experimental.cameldemo.dataconversion.command.TextToXmlJaxb;
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

                case CFP:
                    ConcurrentFileProcessor cfp = new ConcurrentFileProcessor();
                    executor.executeCommand(cfp);
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

                case TC:
                    TextToCSV textToCSV = new TextToCSV();
                    executor.executeCommand(textToCSV);
                    break;

                case TCB:
                    TextToCsvBindy textToCsvBindy = new TextToCsvBindy();
                    executor.executeCommand(textToCsvBindy);
                    break;

                case TCF:
                    TextToCustomFormat textToCustomFormat = new TextToCustomFormat();
                    executor.executeCommand(textToCustomFormat);
                    break;

                case TJ:
                    TextToJson textToJson = new TextToJson();
                    executor.executeCommand(textToJson);
                    break;

                case TXJ:
                    TextToXmlJaxb textToXmlJaxb = new TextToXmlJaxb();
                    executor.executeCommand(textToXmlJaxb);
                    break;

                case TXX:
                    TextToXmlXstream textToXmlXstream = new TextToXmlXstream();
                    executor.executeCommand(textToXmlXstream);
                    break;

            }
        } catch (IllegalArgumentException ex){
            System.out.println("Check documentation for command list");
            System.exit(1);
        }

    }
}
