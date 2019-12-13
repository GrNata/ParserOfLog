package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

public class testDateQueryMetod {

    public Date randomBirthday() {
        Date now = new Date();
        long sixMonthsAgo = (now.getTime() - 15552000000l);
        long today = now.getTime();
            long ms = ThreadLocalRandom.current().nextLong(sixMonthsAgo, today);
            return new Date(ms);
    }


    @Test
    public void getDateForTest(){
        System.out.println(randomBirthday().toString());
        System.out.println(randomBirthday().toString());
    }
}
