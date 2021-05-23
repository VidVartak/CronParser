public class TimeFieldHelper {
    private static String printOneValue(String value, int minVal, int maxVal, String desc){
        String retVal;
        if (Integer.valueOf(value)<minVal || Integer.valueOf(value)>maxVal){
            throw new RuntimeException("Value "+value+" is out of range for "+desc+". Valid range is between "+minVal+" and "+maxVal);
        } else {
            retVal=" " + value;
        }
        return retVal;
    }

    private static String printOneValue(int value, int minVal, int maxVal){
        return printOneValue(""+value, minVal, maxVal, null);
    }

    private static String printAllValues(int minVal, int maxVal) {
        String retVal="";
        for (int i = minVal; i <= maxVal; i++) {
            retVal=retVal.concat(" " + i);
        }
        return retVal;
    }

    private static String printCSV(String expression, int minVal, int maxVal, String desc){
        String retVal="";
        String[] parts = expression.split(",");
        for (String p:parts){
            retVal=retVal.concat(printOneValue(p,  minVal,  maxVal, desc));
        }
        return retVal;
    }

    private static String printRange(String expression, int minVal, int maxVal){
        String retVal="";
        String[] parts = expression.split("-");
        for (int i=Integer.valueOf(parts[0]); i<=Integer.valueOf(parts[1]); i++){
            retVal=retVal.concat(printOneValue(i,  minVal, maxVal));
        }
        return retVal;
    }

    private static String printIntervals(int interval, int minVal, int maxVal){
        String retVal="";
        int count=0;
        while (minVal+count*interval<=maxVal){
            retVal=retVal.concat(printOneValue((minVal+count*interval), minVal, maxVal));
            count++;
        }
        return retVal;
    }

    public static String parse(String expression, int minVal, int maxVal, String desc) {
        String output="";
        output=output.concat(String.format("%1$-14s",desc));
        if (expression.equals("*")){
            output=output.concat(printAllValues(minVal, maxVal));
        } else if (expression.contains("*/")){
            output=output.concat(printIntervals(Integer.valueOf(expression.substring(2)), minVal, maxVal));
        } else if (expression.contains(",")){
            output=output.concat(printCSV(expression, minVal, maxVal, desc));
        } else if (expression.contains("-")){
            output=output.concat(printRange(expression, minVal, maxVal));
        } else {
            output=output.concat(printOneValue(expression, minVal, maxVal, desc));
        }
        output=output.concat("\n");
        return output;
    }

}
