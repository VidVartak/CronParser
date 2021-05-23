# CronParser

This is a simple Java application that reads a Cron expression as the only command line parameter and outputs a string representing the details of the scheduling options.

It consists of these java files:

1. CronParser.java: contains the main method that runs when the application is run
2. CronField: Interface that defines the CronField functionality
3. TimeField.java: Implements CronField interface and defines different time fields (e.g. MIN, HOUR and DAYOFWEEK) and their order in one expression format
4. TimeFieldHelper.java: contains the logic to apply different cron expressions (e.g. */15 or 1-5) to the time fields 
5. CronFieldFactory.java: returns the required type of CronField implementation (currently only one)

Unit tests can be found in the class called CronParserTest.java. The tests are automatically run as part of the Maven build process before the jar file is generated.

How to run on Linux:

Download and install Java Runtime Environment version 8 from https://java.com/en/download/ 

Download the executable jar file for the project from here: https://github.com/VidVartak/CronParser/blob/main/target/CronParser-1.0-SNAPSHOT.jar

Run the jar file by using the following command (change the parameter in quotes at the end to your own values to test):

java -jar CronParser-1.0-SNAPSHOT.jar "*/15 0 1,15 * 1-5 /usr/bin/find"

