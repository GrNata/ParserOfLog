package parserOfLogs;

import parserOfLogs.query.IPQuery;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery {

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
            Date date = null;
            try {
                date = dateFormat.parse(splitStr[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
            Date date = null;
            try {
                date = dateFormat.parse(splitList[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
            Date date = null;
            try {
                date = dateFormat.parse(splitList[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
            Date date = null;
            try {
                date = dateFormat.parse(splitList[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
        String[] arrStr = new String[5];
        arrStr = str.split("\\t");
        if (arrStr[3].contains(" "))
            arrStr[3] = arrStr[3].substring(0, arrStr[3].indexOf(" "));
//        System.out.println("SPLIT:");
//        for (int i=0; i<arrStr.length; i++)     System.out.println(arrStr[i]);
//        System.out.println("____________________________________");
        return arrStr;
    }

    //  Проерка даты под условие
    private boolean isDate(Date date, Date after, Date before){

        if ((after == null || date.after(after) || after.equals(date)) &&
                (before == null || date.before(before) || before.equals(date)))
            return true;
        return false;
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
//    }


}