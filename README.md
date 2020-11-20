# CamelDemo
Some Camel Demos

1. Greet(gr) - spring based camel greeter.

2. Mina(mn) - dummy server.

3. CXF - cxf and camel usage. (Run unit test to try)

4. Content Based File Copier(cfc) - file copy from source to destination based on file content

5. File Copier(fc) - file copy from source to destination

6. File Printer(fp) - prints contents of files in directory

7. File Writer(fw) - write message from stream to file

8. FTP FileWriter(fw) - write message from stream to file located on ftp server

9. FTP to JMS(ftj) - write files from ftp to jms queue

10. FTP to JMS with error handler (fte) - write files from ftp to jms queue with additional error handling

11. Text to CSV (tc)- write text data to csv file

12. Text to CSV bindy (tcb) - write text data to csv file using bindy

13. Text to Custom Format (tcf) - write text data to custom file format
Arg name for running is in ()


## Build and run
```yaml
mvn clean install 

java -jar <jar-location>/camelDemo <demo-name>
```
