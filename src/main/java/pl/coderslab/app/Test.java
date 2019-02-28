package pl.coderslab.app;

import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) {

        LocalDateTime date = LocalDateTime.now();
        int year = date.getYear();
        int month = date.getMonthValue();

        int day = date.getDayOfMonth();

        int hour = date.getHour();
        int minute = date.getMinute();

        String lastTime = year + "-" + month + "-" + day + "|" + hour + ":" + minute;
        System.out.println(lastTime);

        String text = "można odstąpić od udzielania pierwszej pomocy w stacji poczucia zagrożenia zdrowia lub życia, jednak nie można odstąpić od podjęcia działań zmierzających do skutecznego powiadomienia o tym zdarzeniu podmiotów ustawowo powołanych do niesienia pomocy osobom w stanie nagłego zagrożenia zdrowotnego;";
        System.out.println(text.length());
    }
}
