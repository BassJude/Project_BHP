package pl.coderslab.app;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) {

        LocalDateTime date = LocalDateTime.now();
        int year=date.getYear();
        int month=date.getMonthValue();

        int day = date.getDayOfMonth();

        int hour =date.getHour();
        int minute=date.getMinute();

         String lastTime = year+"-"+month+"-"+day+"|"+hour+":"+minute;
        System.out.println(lastTime);
    }
}
