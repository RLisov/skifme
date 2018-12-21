package com.shaq.skifme.data.room;

import android.arch.persistence.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DaysConverter {

    @TypeConverter
    public String fromDays(List<String> days) {

        String string = "";
        for(String s : days) string += (s + ",");

        return string;
    }

    @TypeConverter
    public ArrayList<String> toDays(String concatenatedStrings) {
        ArrayList<String> myStrings = new ArrayList<>();

        for(String s : concatenatedStrings.split(",")) {

            myStrings.add(s);
        }

        return myStrings;
    }

}