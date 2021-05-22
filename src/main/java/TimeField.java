import java.io.Serializable;

/**
 * Enum representing parts of the cron expression and
 */
public enum TimeField implements CronField {
    MINUTE(0, 59, "minute"),
    HOUR(0, 23, "hour"),
    DAYOFMONTH(1, 31, "day of month"),
    MONTH(1, 12, "month"),
    DAYOFWEEK(1, 7, "day of week");

    private final int minVal;
    private final int maxVal;
    private final String desc;

    TimeField(int minVal, int maxVal, String desc) {
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.desc = desc;
    }
    public String parse(String expression) {
        String output="";
        output=output.concat(String.format("%1$-14s",desc));
        if (expression.equals("*")){
            output=output.concat(TimeFieldHelper.printAllValues(minVal, maxVal));
        } else if (expression.contains("*/")){
            output=output.concat(TimeFieldHelper.printIntervals(Integer.valueOf(expression.substring(2)), minVal, maxVal));
        } else if (expression.contains(",")){
            output=output.concat(TimeFieldHelper.printCSV(expression, minVal, maxVal, desc));
        } else if (expression.contains("-")){
            output=output.concat(TimeFieldHelper.printRange(expression, minVal, maxVal));
        } else {
            output=output.concat(TimeFieldHelper.printOneValue(expression, minVal, maxVal, desc));
        }
        output=output.concat("\n");
        return output;
    }
//"*/15 0 1,15 * 1-5 /usr/bin/find"

}

