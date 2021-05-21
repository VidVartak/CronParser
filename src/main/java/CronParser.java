import java.io.IOException;

public class CronParser {
    static String parseCron(String input){
        StringBuilder output=new StringBuilder();
        String[] parts = input.split(" ");
        int partNumber=0;
        for (TimeField tf:TimeField.values()){
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

    public static void main(String[] args){
        if (args.length!=1){
            throw new RuntimeException("Please provide one parameter, the cron expression.");
        } else {
            String input = args[0];
            System.out.println(parseCron(input));
        }
    }
}
