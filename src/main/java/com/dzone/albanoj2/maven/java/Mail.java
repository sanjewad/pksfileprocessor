package com.fxdms.ams.notification.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Mail {

    private String from;
    private String to;
    private String subject;
    private String content;
    private Map model = new HashMap();

    public Mail() {
    }

}
