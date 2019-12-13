package parserOfLogs;

import parserOfLogs.query.DateQuery;
import parserOfLogs.query.IPQuery;
import parserOfLogs.query.UserQuery;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery {
    private Path logDir;
    private DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);

    public LogParser (Path logDir) {
        this.logDir = logDir;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
//возвращать количество уникальных IP адресов за выбранный период. Здесь и далее, если в методе есть параметры Date after и Date before,
//                  то нужно возвратить данные касающиеся только данного периода (включая даты after и before).
//Если параметр after равен null, то нужно обработать все записи, у которых дата меньше или равна before.
//Если параметр before равен null, то нужно обработать все записи, у которых дата больше или равна after.
//Если и after, и before равны null, то нужно обработать абсолютно все записи (без фильтрации по дате).
        List<String> sList = readFile();
//        int count = 0;
//        try {
//            sList = Files.readAllLines(logDir, StandardCharsets.UTF_8);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for(String str : sList) {
//            System.out.println(str);

//            Pattern p = Pattern.compile("((\\d{1,2}.){1,2}\\d{4} (\\d{1,2}:){1,2}\\d{1,2})");
//            Matcher m = p.matcher(str);
//            while (m.find()){
//                dataStr = str.subSequence(m.start(), m.end()).toString();
////                System.out.println("Pattern: "+dataStr);
//                try {
////                    date = new SimpleDateFormat("d?d.MM.yyyy HH:mm:ss").parse(dataStr);
//                    date = new SimpleDateFormat().parse(dataStr);
////                    System.out.println("date = "+date.toString()+"  date1 = "+after+"   date2 = "+before);
//                    if ((after == null || date.after(after)  && (before == null || date.before(before)))) {
//                        count++;
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            Date date = regersDate(str);
//            if ((after == null || date.after(after) || after.equals(date)) &&
//                    (before == null || date.before(before) || before.equals(date)))
//                count++;
//        }
//        return count;

        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
//   возвращать множество, содержащее все не повторяющиеся IP. Тип в котором будем хранить IP будет String
        List<String> sList = readFile();
        Set<String> sSet = new HashSet<>();

        for (String str : sList){
            String[] splitStr = splitList(str);
//            System.out.println(str);
//            Pattern p = Pattern.compile("^(\\d+.){1,3}(\\d.){1,3}(\\d.){1,3}(\\d){1,3})");

//            Pattern p = Pattern.compile("^(\\d+.){3}\\d+");
//            Matcher m = p.matcher(str);
//            while (m.find()) {
//                String idStr = str.substring(m.start(), m.end());
////                System.out.println("Pattern: "+ idStr);
//                sSet.add(idStr);
//            }
//            Date date = regersDate(str);
            Date date = formatLogDate(splitStr[2]);
            if (isDate(date, after, before))
//            if ((after == null || date.after(after) || after.equals(date)) &&
//                    (before == null || date.before(before) || before.equals(date)))
//            sSet.add(regarsId(str));
                sSet.add(splitStr[0]);
        }
//        for (String s : sSet) System.out.println(s);
        return sSet;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
//  возвращать IP, с которых работал переданный пользователь.
        Set<String> sSet = new HashSet<>();
        List<String> sList = readFile();

        for (String str : sList){
//            System.out.println(str);
//            Pattern p = Pattern.compile("\\t([A-Z][a-z]{1,10}(( [A-Z][a-z]{1,10}){0,1}){0,2})\\t");
//            Matcher m = p.matcher(str);
//            while (m.find()) {
//                String idUser = str.substring(m.start(), m.end()).trim();
//                System.out.println(idUser+" - "+user);

//                    Pattern p2 = Pattern.compile("^(\\d+.){3}\\d+");
//                    Matcher m2 = p2.matcher(str);
//                    while (m2.find()) {
//                        String idStr = str.substring(m2.start(), m2.end());
////                System.out.println("Pattern: "+ idStr);
//                        sSet.add(idStr);
//                    }
//                    idUser = regarsId(str);
//                Date date = regersDate(str);
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);
            String idUser = splitList[1];

            if (idUser.equals(user) && isDate(date, after, before))
//                        && (after == null || date.after(after) || after.equals(date)) &&
//                        (before == null || date.before(before) || before.equals(date)))
//                    sSet.add(regarsId(str));
                sSet.add(splitList[0]);
        }
        return sSet;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
//  возвращать IP, с которых было произведено переданное событие.
        Set<String> sSet = new HashSet<>();
        List<String> sList = readFile();

        for (String str : sList){
//            System.out.println(str);
//            Pattern p = Pattern.compile("((\\t([A-Z]{4,8}))(_){0,1}[A-Z]{0,6})");
//            Matcher m = p.matcher(str);
//            while (m.find()) {
//                String eventStr = str.substring(m.start(), m.end()).trim();
////                System.out.println(idStr+"-event = "+event.toString());
//                Date date = regersDate(str);
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);
            String eventStr = splitList[3];
            if (eventStr.equals(event.toString()) && isDate(date, after, before))
//                        && (after == null || date.after(after) || after.equals(date)) &&
//                        (before == null || date.before(before) || before.equals(date)))
//                    sSet.add(regarsId(str));
                sSet.add(splitList[0]);
        }
        return sSet;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
//  возвращать IP, события с которых закончилось переданным статусом.
        List<String> sList = readFile();
        Set<String> sSet = new HashSet<>();

        for (String str : sList){
//            System.out.println(str);
//            Pattern p = Pattern.compile("\\t[OFE]{1}[A-Z]{1,5}");
//            Matcher m = p.matcher(str);
//            while (m.find()){
//                String statusStr = str.substring(m.start(), m.end()).trim();
////                System.out.println(statusStr);
//                Date date = regersDate(str);
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);
            String statusStr = splitList[4];
            if ((statusStr.equals(status.toString())) && isDate(date, after, before))
//                    sSet.add(regarsId(str));
                sSet.add(splitList[0]);
        }
        return sSet;
    }
    //____________________________________________________________________________________________________________________
    // Чтение файла
    public List<String> readFile(){
        List<String> sList = new ArrayList<>();

        try {
            if (Files.isDirectory(logDir)) {
//  ищем файлы с расширением лог в данной директории, затем считываем в sList
                for (File f : logDir.toFile().listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith(".log");
                    }
                }))
                {
//                    System.out.println("fNames = "+f);
                    List<String> tempList = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
//                    System.out.println("fileNames = ");
//                    for (String s : sList)  System.out.println(s);
//                    System.out.println("_______________________________________");
                    sList.addAll(tempList);
                }
            } else {
                sList = Files.readAllLines(logDir, StandardCharsets.UTF_8);
            }

//            for (String s : sList)  System.out.println("fileNames = "+s);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("result:");
//        for (String s : sList)  System.out.println(s);
        return sList;
    }


    // Разбивка строки на массив по табуляции
    private String[] splitList(String str){
        String[] arrStr = new String[6];
        String[] arr = str.split("\\t");
// последний элемнт содержит номер задачи
        for (int i=0; i<arr.length; i++)
            arrStr[i] = arr[i];

        if (arrStr[3].contains(" ")) {
            String tempStr = arrStr[3];
            arrStr[3] = tempStr.substring(0, tempStr.indexOf(" "));
            arrStr[5] = tempStr.substring(tempStr.indexOf(" ")).trim();
        }
//        System.out.println("SPLIT:");
//        for (int i=0; i<arrStr.length; i++)     System.out.println(arrStr[i]);
//        System.out.println("____________________________________");
        return arrStr;
    }

    //  Проверка даты под условие
    private boolean isDate(Date date, Date after, Date before){

        if ((after == null || date.after(after) || after.equals(date)) &&
                (before == null || date.before(before) || before.equals(date)))
            return true;
        return false;
    }

    //      форматирование заданной даты
    private Date formatLogDate(String sDate){
        try {
            return dateFormat.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


//    private Date regersDate(String str) {
//        Date date = null;
//        Pattern p = Pattern.compile("((\\d{1,2}.){1,2}\\d{4,5} (\\d{1,2}:){1,2}\\d{1,2})");
//        Matcher m = p.matcher(str);
//        while (m.find()) {
//            String dataStr = str.subSequence(m.start(), m.end()).toString();
//            try {
//                date = new SimpleDateFormat().parse(dataStr);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//        return date;
//    }
//
//    private String regarsId(String str){
//        String idStr = null;
//        Pattern p = Pattern.compile("^(\\d+.){3}\\d+");
//        Matcher m = p.matcher(str);
//        while (m.find()) {
//            idStr = str.substring(m.start(), m.end());
////                System.out.println("Pattern: "+ idStr);
////            sSet.add(idStr);
//        }
//        return idStr;
//
//__________________________________________________________________________________

    //______________________________________________________________________
    @Override
    public Set<String> getAllUsers() {
        //  должен возвращать всех пользователей.
        List<String> sList = readFile();
        Set<String> sSet = new HashSet<>();

        for (String str : sList){
            String[] splitList = splitList(str);
            sSet.add(splitList[1]);
        }
        return sSet;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        // должен возвращать количество уникальных пользователей.
        List<String> sList = readFile();
        Set<String> sSet = new HashSet<>();

        for (String str : sList){
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);
            if (isDate(date, after, before))
                sSet.add(splitList[1]);
        }
        return sSet.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        //  должен возвращать количество событий от определенного пользователя.
        List<String> sList = readFile();
        Set<String> sSet = new HashSet<>();

        for (String str : sList){
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);
            if (user.equals(splitList[1]) && isDate(date, after, before))
                sSet.add(splitList[3]);
        }
        return sSet.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        //  должен возвращать пользователей с определенным IP.
        //  Несколько пользователей могут использовать один и тот же IP.
        List<String> sList = readFile();
        Set<String> sSet = new HashSet<>();

        for (String str : sList){
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);
//            System.out.println("id= "+splitList[0]+" - "+splitList[1]);

            if (ip.equals(splitList[0]) && isDate(date, after, before))
                sSet.add(splitList[1]);
        }
        return sSet;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        //  должен возвращать пользователей, которые делали логин.
        List<String> sList = readFile();
        Set<String> sSet = new HashSet<>();

        for (String str : sList){
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);
//            System.out.println("id= "+splitList[0]+" - "+splitList[1]);

            if ("LOGIN".equals(splitList[3]) && isDate(date, after, before))
                sSet.add(splitList[1]);
        }
        return sSet;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        //  должен возвращать пользователей, которые скачали плагин.
        List<String> sList = readFile();
        Set<String> sSet = new HashSet<>();

        for (String str : sList){
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);
//            System.out.println("id= "+splitList[0]+" - "+splitList[1]);

            if (Event.DOWNLOAD_PLUGIN.toString().equals(splitList[3])
                    && Status.OK.toString().equals(splitList[4])
                    && isDate(date, after, before))
                sSet.add(splitList[1]);     //  проверка на OK нужна????
        }
        return sSet;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        //  должен возвращать пользователей, которые отправили сообщение.
        List<String> sList = readFile();
        Set<String> sSet = new HashSet<>();

        for (String str : sList){
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);
//            System.out.println("id= "+splitList[0]+" - "+splitList[1]);

            if (Event.WRITE_MESSAGE.toString().equals(splitList[3])
                    && Status.OK.toString().equals(splitList[4])
                    && isDate(date, after, before))
                sSet.add(splitList[1]);     //  проверка на OK нужна????
        }
        return sSet;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        //  должен возвращать пользователей, которые решали любую задачу.
        List<String> sList = readFile();
        Set<String> sSet = new HashSet<>();

        for (String str : sList){
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);
//            System.out.println("id= "+splitList[0]+" - "+splitList[1]);

            if (Event.SOLVE_TASK.toString().equals(splitList[3])
                    && isDate(date, after, before))
                sSet.add(splitList[1]);
        }
        return sSet;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        //  должен возвращать пользователей, которые решали задачу с номером task.
        List<String> sList = readFile();
        Set<String> sSet = new HashSet<>();

        for (String str : sList){
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);
//            System.out.println("id= "+splitList[0]+" - "+splitList[1]);

            if (Event.SOLVE_TASK.toString().equals(splitList[3])
                    && Integer.parseInt(splitList[5]) == task
                    && isDate(date, after, before))
                sSet.add(splitList[1]);
        }
        return sSet;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        //  должен возвращать пользователей, которые решили любую задачу.
        List<String> sList = readFile();
        Set<String> sSet = new HashSet<>();

        for (String str : sList){
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);

            if (Event.DONE_TASK.toString().equals(splitList[3])
//                    && Status.OK.toString().equals(splitList[4])
                    && isDate(date, after, before))
                sSet.add(splitList[1]);
        }
        return sSet;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        //  должен возвращать пользователей, которые решили задачу с номером task.
        List<String> sList = readFile();
        Set<String> sSet = new HashSet<>();

        for (String str : sList){
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);

            if (Event.DONE_TASK.toString().equals(splitList[3])
                    && Integer.parseInt(splitList[5]) == task
//                    && Status.OK.toString().equals(splitList[4])
                    && isDate(date, after, before))
                sSet.add(splitList[1]);
        }
        return sSet;
    }

//_______________________________DateQuery______________________________________________________________________________________

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        // должен возвращать даты, когда определенный пользователь произвел определенное событие.
        List<String> sList = readFile();
        Set<Date> sSet = new HashSet<>();

        for (String str : sList){
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);

            if (user.equals(splitList[1])
                    && event.toString().equals(splitList[3])
                    && isDate(date, after, before))
                sSet.add(date);
        }
        return sSet;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        //    должен возвращать даты, когда любое событие не выполнилось (статус FAILED).
        List<String> sList = readFile();
        Set<Date> sSet = new HashSet<>();

        for (String str : sList){
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);

            if (Status.FAILED.toString().equals(splitList[4])
                    && isDate(date, after, before))
                sSet.add(date);
        }
        return sSet;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        // должен возвращать даты, когда любое событие закончилось ошибкой (статус ERROR).
        List<String> sList = readFile();
        Set<Date> sSet = new HashSet<>();

        for (String str : sList){
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);

            if (Status.ERROR.toString().equals(splitList[4])
                    && isDate(date, after, before))
                sSet.add(date);
        }
        return sSet;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        //   должен возвращать дату, когда пользователь залогинился впервые за указанный период. Если такой даты в логах нет - null.
        List<String> sList = readFile();
        Set<Date> sSet = new HashSet<>();

        TreeSet<Date> dateSet = new TreeSet<>();

        for (String str : sList){
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);
            if (user.equals(splitList[1])
                    && Event.LOGIN.toString().equals(splitList[3])
                    && isDate(date, after, before))
                dateSet.add(date);
        }
        if (dateSet.size() > 0)
            return dateSet.first();
        return null;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        //   должен возвращать дату, когда пользователь впервые попытался решить определенную задачу. Если такой даты в логах нет - null.
        List<String> sList = readFile();
        Set<Date> sSet = new HashSet<>();

        TreeSet<Date> dateSet = new TreeSet<>();

        for (String str : sList){
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);
            if (user.equals(splitList[1])
                    && Event.SOLVE_TASK.toString().equals(splitList[3])
                    && Integer.parseInt(splitList[5]) == task
                    && isDate(date, after, before))
                dateSet.add(date);
        }
        if (dateSet.size() > 0)
            return dateSet.first();
        return null;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        //  должен возвращать дату, когда пользователь впервые решил определенную задачу. Если такой даты в логах нет - null.
        List<String> sList = readFile();
//        Set<Date> sSet = new HashSet<>();

        TreeSet<Date> dateSet = new TreeSet<>();

        for (String str : sList){
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);
            if (user.equals(splitList[1])
                    && Event.DONE_TASK.toString().equals(splitList[3])
                    && Integer.parseInt(splitList[5]) == task
                    && isDate(date, after, before))
                dateSet.add(date);
        }
        if (dateSet.size() > 0)
            return dateSet.first();
        return null;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        //  должен возвращать даты, когда пользователь написал сообщение.
        List<String> sList = readFile();
        Set<Date> dateSet = new HashSet<>();

        for (String str : sList){
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);
            if (user.equals(splitList[1])
                    && Event.WRITE_MESSAGE.toString().equals(splitList[3])
//                    && Status.OK.toString().equals(splitList[4])
                    && isDate(date, after, before)
            )
                dateSet.add(date);
        }
//        if (dateSet.size() > 0)
        return dateSet;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        //  должен возвращать даты, когда пользователь скачал плагин.
        List<String> sList = readFile();
        Set<Date> dateSet = new HashSet<>();

        for (String str : sList){
            String[] splitList = splitList(str);
            Date date = formatLogDate(splitList[2]);
            if (user.equals(splitList[1])
                    && Event.DOWNLOAD_PLUGIN.toString().equals(splitList[3])
//                    && Status.OK.toString().equals(splitList[4])
                    && isDate(date, after, before)
            )
                dateSet.add(date);
        }
//        if (dateSet.size() > 0)
        return dateSet;
    }

}
