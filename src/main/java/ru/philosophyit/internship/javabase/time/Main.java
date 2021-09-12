package ru.philosophyit.internship.javabase.time;

import java.time.format.DateTimeFormatter;
import java.util.*;

class ConsoleCalendar{
    private static List<String> calendar_matrix = new ArrayList<>();
    private void createCalendar(){
        Calendar cal = new GregorianCalendar();
        int today = cal.get(Calendar.DAY_OF_MONTH);
        cal.add(Calendar.DAY_OF_MONTH, - (today - 1)); //Back to first day of month

        String num = ""; //Our day field
        int day_offset = cal.get(Calendar.DAY_OF_WEEK) - 3;
        int day_of_week = 0;
        int days_in_month = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        cal.add(Calendar.MONTH, -1); //To get amount days in past month
        int days_in_past_month = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = -day_offset; i <= days_in_month; i++){  //Building calendar view structure
            if (i <= 0){
                num = " " + (days_in_past_month + i);
            }else if (( i / 10) == 0 ){
                num = "  " + i;
            }else
                num = " " + i;


            if (i == today)
                num +="* "; //Marking this day
            else
                num +="  ";


            calendar_matrix.add(num);
            num = "";


            day_of_week++;

            if (day_of_week == 7){
                calendar_matrix.add("\n");
                day_of_week = 0;
            }
        }
    }
    public void drawCalendar(){
        createCalendar();
        System.out.println("MON  TUE  WED  THU  FRI  SAT  SUN");
        for(String string: calendar_matrix)
            System.out.print(string);
    }

}

public class Main {
    public static void main(String ...args) {
        ConsoleCalendar conCal = new ConsoleCalendar();
        conCal.drawCalendar();
    }
}
