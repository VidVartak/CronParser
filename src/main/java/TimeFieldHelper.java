import java.util.HashMap;

public class TimeFieldHelper {

    private static final HashMap<String, Integer> dayNames = new HashMap<>();

    static {
        dayNames.put("SUN", 0);
        dayNames.put("MON", 1);
        dayNames.put("TUE", 2);
        dayNames.put("WED", 3);
        dayNames.put("THU", 4);
        dayNames.put("FRI", 5);
        dayNames.put("SAT", 6);
    }

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
        int startDayNum, endDayNum;
        if (dayNames.containsKey(parts[0])){
            startDayNum=dayNames.get(parts[0]);
            endDayNum=dayNames.get(parts[1]);
        } else {
            startDayNum=Integer.valueOf(parts[0]);
            endDayNum=Integer.valueOf(parts[1]);
        }

        if (startDayNum> endDayNum){ //e.g. 5 and 1 we want to print 5 6 0 1
            endDayNum+=7;
        }

        for (int i=startDayNum; i<=endDayNum; i++){
            int j=i%7;
            retVal=retVal.concat(printOneValue(j,  minVal, maxVal));
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

    static String parse(String expression, int minVal, int maxVal, String desc) {
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
