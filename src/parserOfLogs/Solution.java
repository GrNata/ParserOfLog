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

            System.out.println("\ngetAllUsers__________________________________________________________________");
            System.out.println(logParser.getAllUsers());
            System.out.println("\ngetNumberOfUsers__________________________________________________________________");
            System.out.println(logParser.getNumberOfUsers(null, null));
            System.out.println("c "+ date1+" по "+date3);
            System.out.println(logParser.getNumberOfUsers(date1, date3));
            System.out.println("\ngetNumberOfUserEvents__________________________________________________________________");
            System.out.println(logParser.getNumberOfUserEvents("Eduard Petrovich Morozko", null, null));
            System.out.println("c "+ date1+" по "+date2);
            System.out.println(logParser.getNumberOfUserEvents("Eduard Petrovich Morozko", date1, date2));
            System.out.println("\ngetUsersForIP__________________________________________________________________");
            System.out.println(logParser.getUsersForIP("127.0.0.1", null, null));
            System.out.println("c "+ date1+" по "+date3);
            System.out.println(logParser.getUsersForIP("127.0.0.1", date1, date3));
            System.out.println("\ngetLoggedUsers__________________________________________________________________");
            System.out.println(logParser.getLoggedUsers(null, null));
            System.out.println("c "+ date1+" по "+date3);
            System.out.println(logParser.getLoggedUsers(date1, date3));
            System.out.println("\ngetDownloadedPluginUsers__________________________________________________________________");
            System.out.println(logParser.getDownloadedPluginUsers(null, null));
            System.out.println("c "+ date1+" по "+date2);
            System.out.println(logParser.getDownloadedPluginUsers(date1, date2));
            System.out.println("\ngetWroteMessageUsers__________________________________________________________________");
            System.out.println(logParser.getWroteMessageUsers(null, null));
            System.out.println("c "+ date1+" по "+date2);
            System.out.println(logParser.getWroteMessageUsers(date1, date2));
            System.out.println("\ngetSolvedTaskUsers__________________________________________________________________");
            System.out.println(logParser.getSolvedTaskUsers(null, null));
            System.out.println("c "+ date3+" по "+null);
            System.out.println(logParser.getSolvedTaskUsers(date3, null));
            System.out.println("\ngetSolvedTaskUsers - NUMBER__________________________________________________________________");
            System.out.println(logParser.getSolvedTaskUsers(null, null, 15));
            System.out.println("c "+ date3+" по "+null);
            System.out.println(logParser.getSolvedTaskUsers(date3, null, 18));
            System.out.println("\ngetDoneTaskUsers__________________________________________________________________");
            System.out.println(logParser.getDoneTaskUsers(null, null));
            System.out.println("c "+ date1+" по "+null);
            System.out.println(logParser.getDoneTaskUsers(date1, null));
            System.out.println("\ngetDoneTaskUsers - NUMBER__________________________________________________________________");
            System.out.println(logParser.getDoneTaskUsers(null, null, 15));
            System.out.println("c "+ date1+" по "+null);
            System.out.println(logParser.getDoneTaskUsers(date1, null, 48));

            System.out.println("\ngetDatesForUserAndEvent__________________________________________________________________");
            System.out.println(logParser.getDatesForUserAndEvent("Eduard Petrovich Morozko", Event.LOGIN, null, null));
            System.out.println("c "+ date3+" по "+null);
            System.out.println(logParser.getDatesForUserAndEvent("Amigo", Event.SOLVE_TASK, date3, null));
            System.out.println("\ngetDatesWhenSomethingFailed__________________________________________________________________");
            System.out.println(logParser.getDatesWhenSomethingFailed(null, null));
            System.out.println("c "+ date3+" по "+null);
            System.out.println(logParser.getDatesWhenSomethingFailed(date3, null));
            System.out.println("\ngetDatesWhenErrorHappened__________________________________________________________________");
            System.out.println(logParser.getDatesWhenErrorHappened(null, null));
            System.out.println("c "+ date3+" по "+null);
            System.out.println(logParser.getDatesWhenErrorHappened(date3, null));
            System.out.println("\ngetDateWhenUserLoggedFirstTime__________________________________________________________________");
            System.out.println(logParser.getDateWhenUserLoggedFirstTime("Eduard Petrovich Morozko", null, null));
            System.out.println("c "+ date2+" по "+null);
            System.out.println(logParser.getDateWhenUserLoggedFirstTime("Vasya Pupkin", date2, null));
            System.out.println("\ngetDateWhenUserSolvedTask__________________________________________________________________");
            System.out.println(logParser.getDateWhenUserSolvedTask("Amigo", 18, null, null));
            System.out.println("c "+ date2+" по "+null);
            System.out.println(logParser.getDateWhenUserSolvedTask("Vasya Pupkin", 1, date2, null));
            System.out.println("\ngetDateWhenUserDoneTask__________________________________________________________________");
            System.out.println(logParser.getDateWhenUserDoneTask("Vasya Pupkin", 15, null, null));
            System.out.println("c "+ date2+" по "+null);
            System.out.println(logParser.getDateWhenUserDoneTask("Eduard Petrovich Morozko", 48, date2, null));
            System.out.println("\ngetDatesWhenUserWroteMessage__________________________________________________________________");
            System.out.println(logParser.getDatesWhenUserWroteMessage("Vasya Pupkin", null, null));
            System.out.println("c "+ date1+" по "+date2);
            System.out.println(logParser.getDatesWhenUserWroteMessage("Eduard Petrovich Morozko", date1, date2));
            System.out.println("\ngetDatesWhenUserDownloadedPlugin__________________________________________________________________");
            System.out.println(logParser.getDatesWhenUserDownloadedPlugin("Vasya Pupkin", null, null));
            System.out.println("c "+ date2+" по "+null);
            System.out.println(logParser.getDatesWhenUserDownloadedPlugin("Eduard Petrovich Morozko", null, null));

            System.out.println("\ngetNumberOfAllEvents__________________________________________________________________");
            System.out.println(logParser.getNumberOfAllEvents(null, null));
            System.out.println("c "+ date1+" по "+date2);
            System.out.println(logParser.getNumberOfAllEvents(date1, date2));
            System.out.println("\ngetAllEvents__________________________________________________________________");
            System.out.println(logParser.getAllEvents(null, null));
            System.out.println("c "+ date1+" по "+date2);
            System.out.println(logParser.getAllEvents(date1, date2));
            System.out.println("\ngetEventsForIP__________________________________________________________________");
            System.out.println(logParser.getEventsForIP("192.168.100.2", null, null));
            System.out.println("c "+ date1+" по "+date2);
            System.out.println(logParser.getEventsForIP("146.34.15.5", date1, date2));
            System.out.println("\ngetEventsForUser__________________________________________________________________");
            System.out.println(logParser.getEventsForUser("Amigo", null, null));
            System.out.println("c "+ date1+" по "+date2);
            System.out.println(logParser.getEventsForUser("Eduard Petrovich Morozko", date1, date2));
            System.out.println("\ngetFailedEvents__________________________________________________________________");
            System.out.println(logParser.getFailedEvents(null, null));
            System.out.println("c "+ date1+" по "+date2);
            System.out.println(logParser.getFailedEvents(date1, date2));
            System.out.println("\ngetErrorEvents__________________________________________________________________");
            System.out.println(logParser.getErrorEvents(null, null));
            System.out.println("c "+ date1+" по "+date2);
            System.out.println(logParser.getErrorEvents(date1, date2));
            System.out.println("\ngetNumberOfAttemptToSolveTask__________________________________________________________________");
            System.out.println(logParser.getNumberOfAttemptToSolveTask(18, null, null));
            System.out.println("c "+ date1+" по "+null);
            System.out.println(logParser.getNumberOfAttemptToSolveTask(1, date1, null));
            System.out.println("\ngetNumberOfSuccessfulAttemptToSolveTask__________________________________________________________________");
            System.out.println(logParser.getNumberOfSuccessfulAttemptToSolveTask(15, null, null));
            System.out.println("c "+ date1+" по "+null);
            System.out.println(logParser.getNumberOfSuccessfulAttemptToSolveTask(48, date1, null));
            System.out.println("\ngetAllSolvedTasksAndTheirNumber__________________________________________________________________");
            System.out.println(logParser.getAllSolvedTasksAndTheirNumber(null, null));
            System.out.println("c "+ date1+" по "+null);
            System.out.println(logParser.getAllSolvedTasksAndTheirNumber(date1, null));
            System.out.println("\ngetAllDoneTasksAndTheirNumber__________________________________________________________________");
            System.out.println(logParser.getAllDoneTasksAndTheirNumber(null, null));
            System.out.println("c "+ date1+" по "+null);
            System.out.println(logParser.getAllDoneTasksAndTheirNumber(date1, null));

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
