package parserOfLogs;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("c:/JavaNata/log/example.log"));
//        LogParser logParser = new LogParser(Paths.get("c:/JavaNata/log"));

        logParser.readFile();

//        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));

//        System.out.println("__________________________________________________");
        Date date1;
        Date date2;
        Date date3;
        try {
            date1 = new SimpleDateFormat().parse("01.01.2013 00:00:00");
            date2 = new SimpleDateFormat().parse("31.12.2013 00:00:00");
            date3 = new SimpleDateFormat().parse("21.10.2021 19:45:25");

            System.out.println("getNumberOfUniqueIPs:");
            System.out.println(logParser.getNumberOfUniqueIPs(null, null));
            System.out.println("c "+ date1+" по "+date2);
            System.out.println(logParser.getNumberOfUniqueIPs(date1, date2));
            System.out.println("getUniqueIPs________________________________________________________________");
            System.out.println(logParser.getUniqueIPs(null, null));
            System.out.println("c "+ date1+" по "+date3);
            System.out.println(logParser.getUniqueIPs(date1, date3));
            System.out.println("getIPsForUser__________________________________________________________________");
            System.out.println("c "+ date2+" по "+date3);
            System.out.println(logParser.getIPsForUser("Amigo", date2, date3));
            System.out.println(logParser.getIPsForUser("Eduard Petrovich Morozko", null, null));
            System.out.println("getIPsForEvent__________________________________________________________________");
            System.out.println(logParser.getIPsForEvent(Event.DONE_TASK, null, null));
            System.out.println("c "+ date1+" по "+date3);
            System.out.println(logParser.getIPsForEvent(Event.LOGIN, date1, date3));
            System.out.println("getIPsForStatus__________________________________________________________________");
            System.out.println(logParser.getIPsForStatus(Status.ERROR, null, null));
            System.out.println(logParser.getIPsForStatus(Status.OK, date2, date3));
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
