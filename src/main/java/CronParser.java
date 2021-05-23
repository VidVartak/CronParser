import java.io.IOException;

public class CronParser {
    /**
     * The method to parse the cron expression.
     * @param input The cron expression to be parsed
     * @return String containing the parsed expression.
     */
    static String parseCron(String input){
        StringBuilder output=new StringBuilder();
        String[] parts = input.split(" ");
        int partNumber=0;
        CronField[] cf = CronFieldFactory.getCronFields("TimeField");
        for (CronField tf:cf){
            output.append(tf.parse(parts[partNumber]));
            partNumber++;
        }
        //print the remaining parts as the command.
        output.append(String.format("%1$-14s", "command"));
        for (int i=partNumber; i<parts.length; i++){
            output.append(" ");
            output.append(parts[i]);
        }
        output.append("\n");
        return output.toString();
    }

    /**
     * main method which prints out the expanded and parsed version of a cron expression.
     * It uses the method parseCron to do the parsing.
     * @param args One string expected, containing the cron expression.
     */
    public static void main(String[] args){
        if (args.length!=1){
            throw new RuntimeException("Please provide one parameter, the cron expression.");
        } else {
            String input = args[0];
            System.out.println(parseCron(input));
        }
    }
}
