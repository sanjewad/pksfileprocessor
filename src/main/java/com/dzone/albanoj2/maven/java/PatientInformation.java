package com.dzone.albanoj2.maven.java;

import java.util.Map;

public class PatientInformation {

    @CsvField(name="Date")
    private String observationDate;

    public String getObservationDate() {
        return observationDate;
    }

    public void setObservationDate(String observationDate) {
        this.observationDate = observationDate;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Map<String, String> getPatientData() {
        return patientData;
    }

    public void setPatientData(Map<String, String> patientData) {
        this.patientData = patientData;
    }

    @CsvField(name="Patient Id")
    private String patientId;
   /* @CsvField(name="Attribute Name")
    private String attributeName;
    @CsvField(name="Attribute Value")
    private String attributeValue;
*/
    Map<String, String> patientData;

}
