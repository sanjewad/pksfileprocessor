package com.dzone.albanoj2.maven.java;

import java.util.Map;

public class PatientInformation {

    @CsvField(name="Date")
    private String observationDate;
    @CsvField(name="Patient Id")
    private String patientId;
   /* @CsvField(name="Attribute Name")
    private String attributeName;
    @CsvField(name="Attribute Value")
    private String attributeValue;
*/
    Map<String, Object> patientData;

}
