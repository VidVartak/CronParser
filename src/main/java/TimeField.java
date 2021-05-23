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
        return TimeFieldHelper.parse(expression, minVal, maxVal, desc);
    }
//"*/15 0 1,15 * 1-5 /usr/bin/find"

}

