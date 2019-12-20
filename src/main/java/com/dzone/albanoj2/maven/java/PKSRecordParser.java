package com.dzone.albanoj2.maven.java;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PKSRecordParser {
    private static final String H_1 = "H1";
    private static final String H_2 = "H2";
    private static final String H_3 = "H3";
    private static final String H_4 = "H4";

    private String regexPattern = "^(?<" + H_1 + ">\\d{4}-\\d{1,2}-\\d{1,2})\\s*,\\s*(?<" + H_2 + ">\\d{1,3}),\\s*(?<" + H_3 + ">\\w+\\s*\\w+),\\s*(?<" + H_4 + ">\\S+\\s*\\S+)";


    public Map<Integer, List<PatientInformation>> parseReceiveInformation(List<String> list) {
        Pattern headerPattern = Pattern.compile(regexPattern);
        Matcher matcher = headerPattern.matcher("");
        for (String line : list) {
            matcher.reset(line);
            if (matcher.find()) {
                if (matcher.group(H_1) != null) {

                }
                if (matcher.group(H_2) != null) {

                }
                if (matcher.group(H_3) != null) {

                }
                if (matcher.group(H_4) != null) {

                }
            }
        }
        return null;
    }
}
