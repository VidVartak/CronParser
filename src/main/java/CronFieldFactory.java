public class CronFieldFactory {
    static CronField[] getCronFields(String cronType){
        CronField[] cf=null;
        if (cronType.equals("TimeField")){
            return TimeField.values();
        }
        return cf;
    }
}
