package com.dzone.albanoj2.maven.java;
import java.time.LocalDate;
import java.util.HashMap;
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


    public Map<Integer, Map<LocalDate, PatientInformation>> parseReceiveInformation(List<String> list) {
        Pattern headerPattern = Pattern.compile(regexPattern);
        Matcher matcher = headerPattern.matcher("");
        Map<Integer, Map<LocalDate, PatientInformation>> patientRecordMap = new HashMap<>();
        for (String line : list) {
            matcher.reset(line);
            if (matcher.find()) {
                if (matcher.group(H_1) != null && matcher.group(H_2) != null) {
                    if (patientRecordMap.containsKey(matcher.group(H_2))) {
                        if (patientRecordMap.get(H_2).containsKey(H_1)) {
                            Map<String, String> data = patientRecordMap.get(H_2).get(H_1).getPatientData();
                            if (matcher.group(H_3) != null) {
                                if (data != null) {
                                    if (data.containsKey(matcher.group(H_3))) {
                                        data.put(matcher.group(H_3), matcher.group(H_4));
                                    }
                                } else {
                                    data = new HashMap<>();
                                    data.put(matcher.group(H_3), matcher.group(H_4));
                                }
                            }

                        } else {
                            PatientInformation patInfo = getPatientInformation(matcher);
                            patientRecordMap.get(matcher.group(H_2)).put(LocalDate.parse(matcher.group(H_1)), patInfo);

                        }
                    } else {
                        PatientInformation patInfo = getPatientInformation(matcher);
                        Map<LocalDate, PatientInformation> patientInformationMap = new HashMap<>();
                        patientInformationMap.put(LocalDate.parse(matcher.group(H_1)), patInfo);
                        patientRecordMap.put(Integer.parseInt(matcher.group(H_2)), patientInformationMap);
                    }
                }


            }
        }
        return null;
    }

    private PatientInformation getPatientInformation(Matcher matcher) {
        Map<String, String> data = new HashMap<>();
        data.put(matcher.group(H_3), matcher.group(H_4));
        PatientInformation patInfo = new PatientInformation();
        patInfo.setObservationDate(matcher.group(H_1));
        patInfo.setPatientId(matcher.group(H_2));
        patInfo.setPatientData(data);
        return patInfo;
    }
}
