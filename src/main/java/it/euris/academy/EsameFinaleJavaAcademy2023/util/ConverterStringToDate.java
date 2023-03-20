package it.euris.academy.EsameFinaleJavaAcademy2023.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterStringToDate {
    public static Date convertToDate(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.parse(dateString);
    }
}
