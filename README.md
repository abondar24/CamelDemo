# CamelDemo
Some Camel Demos

1. Greet (gr) - spring based camel greeter.

2. Mina (mn) - dummy server.

3. Content Based File Copier (cfc) - file copy from source to destination based on file content

4. File Copier (fc) - file copy from source to destination

5. File Printer (fp) - prints contents of files in directory

6. File Writer (fw) - write message from stream to file

7. FTP FileWriter (fw) - write message from stream to file located on ftp server

8. FTP to JMS (ftj) - write files from ftp to jms queue

9. FTP to JMS with error handler (fte) - write files from ftp to jms queue with additional error handling

10. Text to CSV (tc)- write text data to csv file

11. Text to CSV bindy (tcb) - write text data to csv file using bindy

12. Text to Custom Format (tcf) - write text data to custom file format.

13. Text to Json (tj) - write text data to json file

14. Text to XML JAXB (txj) - write text data to xml file using JAXB

15. Text to XML Xstream (txx) - write text data to xml file using Xstream

16. Concurrent File Processor (cfp) - process files from source directory in parrallel

17. Concurrent Component (cc) - usage of concurrent component in camel.

18. File Read ThreadPool (fth) - read files from source directory using thread pool 

19. Some demos are in unit tests. See concurency and data conversion modules

Note: Arg name for running is in ()


## Build and run
```yaml
./mvnw clean install 

java -jar <jar-location>/camelDemo <demo-name>
```
