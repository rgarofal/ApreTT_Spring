package it.fastweb.apreTT.config;

import com.jcraft.jsch.ChannelSftp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Utility {

    public static LocalDate convertVectorToLocalDate (ChannelSftp.LsEntry file) {

        String dateFile = file.getAttrs().getMtimeString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        return LocalDate.parse(dateFile, formatter);
    }

 public static Date convertVectorToDate (ChannelSftp.LsEntry f) throws ParseException {

        String dateFile = f.getAttrs().getMtimeString();
        Date currentFileDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(dateFile);
        return currentFileDate;
 }

    public static LocalDate convertDateToLocalDate (Date date) {

        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }






}
