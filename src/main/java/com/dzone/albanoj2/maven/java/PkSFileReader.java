package com.dzone.albanoj2.maven.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PkSFileReader {
    private static Pattern fileExtnPtrn = Pattern.compile("([^\\s]+(\\.(?i)(csv))$)");
    private PKSRecordParser pKSRecordParser;

    public PkSFileReader(PKSRecordParser pKSRecordParser) {
        this.pKSRecordParser = pKSRecordParser;

    }

    public List<String> processInputFile(final String fileName) {
        List<String> inputList = new ArrayList<>();
        List list = new ArrayList();
        validateFileExtn(fileName);
        Charset charset = StandardCharsets.UTF_8;
        try (
                InputStream inputFS = getClass()
                        .getClassLoader().getResourceAsStream(fileName);

                BufferedReader br = new BufferedReader(new InputStreamReader(inputFS, charset))

        ) {
            // File inputF = new File(inputFilePath);
            // InputStream inputFS = new FileInputStream(inputF);

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                list.add(line);
            }


            // skip the header of the csv
            //    inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
            pKSRecordParser.parseReceiveInformation(list);
        } catch (IOException e) {

        }
        return null;
    }


    /*private Function<String, Object> mapToItem = (line) -> {
        String COMMA =",";
        String[] p = line.split(COMMA);// a CSV has comma separated lines
        YourJavaItem item = new YourJavaItem();
        item.setItemNumber(p[0]);//<-- this is the first column in the csv file
        if (p[3] != null && p[3].trim().length() > 0) {
            item.setSomeProeprty(p[3]);
        }
        //more initialization goes here
        return item;
    }*/


    public boolean validateFileExtn(String userName) {
        Matcher mtch = fileExtnPtrn.matcher(userName);
        return mtch.matches();
    }

}
