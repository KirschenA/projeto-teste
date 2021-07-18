package com.escola.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

public class Utils {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JavaTimeModule());
    }

    public static String objectToJson(Object value) throws Exception {
        return mapper.writeValueAsString(value);
    }

    public static <T> T jsonToObject(String value, Class<T> clazz) throws Exception {
        return mapper.readValue(value, clazz);
    }

    public static String formatDate(String date, String initDateFormat, String endDateFormat) {
        String parsedDate = null;
        try {
            Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
            parsedDate = formatter.format(initDate);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return parsedDate;
    }


    public static Double format(Double input){

        if(input == null) return null;

        BigDecimal bd = new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static <T> Set<T> unmodifiableNullSafe(Set<T> collection) {
        return collection == null ? Collections.emptySet() : Collections.unmodifiableSet(collection);
    }
}
