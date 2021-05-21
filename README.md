# CronParser

This is a simple Java application that reads a Cron expression as the only command line parameter and outputs a string representing the details of the scheduling options.

There are two main java files:

CronParser.java: contains the main method that runs when the application is run
TimeField.java: contains the logic to process the cron expression.

Unit tests can be found in the class called CronParserTest.java. The tests are automatically run as part of the Maven build process before the jar file is generated.

How to run on Linux:

Download and install Java Runtime Environment version 8 from https://java.com/en/download/ 

Download the executable jar file for the project from here: https://github.com/VidVartak/CronParser/blob/main/target/CronParser-1.0-SNAPSHOT.jar

Run the jar file by using the following command (change the parameter in quotes at the end to your own values to test):

java -jar CronParser-1.0-SNAPSHOT.jar "*/15 0 1,15 * 1-5 /usr/bin/find"

