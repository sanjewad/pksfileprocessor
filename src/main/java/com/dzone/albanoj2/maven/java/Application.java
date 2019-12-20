package com.dzone.albanoj2.maven.java;

public class Application {

    public static void main(String[] args) {
      //  Adder adder = new Adder();
        PkSFileReader pks = new PkSFileReader(new PKSRecordParser());
        pks.processInputFile("message.csv5");
    //    System.out.println("2 + 2 = " + adder.add(2, 2));
    }

}
