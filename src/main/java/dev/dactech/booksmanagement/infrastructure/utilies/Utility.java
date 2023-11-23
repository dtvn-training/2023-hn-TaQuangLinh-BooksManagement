package dev.dactech.booksmanagement.infrastructure.utilies;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {
    public static LocalDate formatToDate(String date, String form){
        if (form == null)form = "dd/MM/yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(form);

       return LocalDate.parse(date, formatter);
    }
    public static LocalDateTime formatToDateTime(String dateTime, String form){
        if (form == null)form = "dd/MM/yyyy HH:mm:sss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(form);

        return LocalDateTime.parse(dateTime, formatter);
    }
    public static String formatDateTimeToString(LocalDateTime dateTime, String form){
        if (form == null)form = "dd/MM/yyyy HH:mm:sss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(form);

       return dateTime.format(formatter);
    }
    public static String formatDateTimeToString(LocalDate dateTime, String form){
        if (form == null)form = "dd/MM/yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(form);

        return dateTime.format(formatter);
    }
    public static String typeOfSort(String sortBy){
        return sortBy.substring(sortBy.indexOf(':') + 1);
    }
}
