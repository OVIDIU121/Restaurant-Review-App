package com.example.myfffd.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
    public static Long getDateTime()
    {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
        // convert date to string before uploading to FB or save it as long, remove backslashes in order to sort
        Date d = new Date("yyyyMMddHHmmss");// save it as string
        Long date = Long.getLong(d.toString()); // save it to db
        return date;
        //List<Sorting.Reviews> reviews = new ArrayList<>();
        // Collection.sort(reviews.subList(0));
    }

}
