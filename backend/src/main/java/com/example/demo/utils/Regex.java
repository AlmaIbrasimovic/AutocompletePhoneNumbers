package com.example.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static Boolean containsLetter(String query) {
        String regexPattern = ".*[a-zA-Z].*";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcherText = pattern.matcher(query);

        return  matcherText.matches();
    }
}
