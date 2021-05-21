public enum TimeField {
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

    private String printOneValue(String value){
        String retVal;
        if (Integer.valueOf(value)<minVal || Integer.valueOf(value)>maxVal){
            throw new RuntimeException("Value "+value+" is out of range for "+desc+". Valid range is between "+minVal+" and "+maxVal);
        } else {
            retVal=" " + value;
        }
        return retVal;
    }

    private String printOneValue(int value){
        return printOneValue(""+value);
    }

    public String printAllValues() {
        String retVal="";
        for (int i = minVal; i <= maxVal; i++) {
            retVal=retVal.concat(" " + i);
        }
        return retVal;
    }

    public String printCSV(String expression){
        String retVal="";
        String[] parts = expression.split(",");
        for (String p:parts){
            retVal=retVal.concat(printOneValue(p));
        }
        return retVal;
    }

    public String printRange(String expression){
        String retVal="";
        String[] parts = expression.split("-");
        for (int i=Integer.valueOf(parts[0]); i<=Integer.valueOf(parts[1]); i++){
            retVal=retVal.concat(printOneValue(i));
        }
        return retVal;
    }

    public String printIntervals(int interval){
        String retVal="";
        int count=0;
        while (minVal+count*interval<=maxVal){
            retVal=retVal.concat(printOneValue((minVal+count*interval)));
            count++;
        }
        return retVal;
    }

    public String parse(String expression) {
        String output="";
        output=output.concat(String.format("%1$-14s",desc));
        if (expression.equals("*")){
            output=output.concat(printAllValues());
        } else if (expression.contains("*/")){
            output=output.concat(printIntervals(Integer.valueOf(expression.substring(2))));
        } else if (expression.contains(",")){
            output=output.concat(printCSV(expression));
        } else if (expression.contains("-")){
            output=output.concat(printRange(expression));
        } else {
            output=output.concat(printOneValue(expression));
        }
        output=output.concat("\n");
        return output;
    }
}

