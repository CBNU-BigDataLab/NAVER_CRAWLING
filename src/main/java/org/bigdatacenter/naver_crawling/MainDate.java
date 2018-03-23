package org.bigdatacenter.naver_crawling;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by HP1 on 6/30/2017.
 */
public class MainDate {

    public static void main(String args[]){
        for(int month = 1; month<=12; month++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(2016 + "/" + month + "/" + "01"));
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            for(int day = 0; day< maxDay; day++){
                String startDate = sdf.format(calendar.getTime());
                calendar.add(Calendar.DATE, 1);
                System.out.println(maxDay + ", START DATE ==> " + startDate + ", END DATE ==> " + startDate);
            }
        }
    }
}
