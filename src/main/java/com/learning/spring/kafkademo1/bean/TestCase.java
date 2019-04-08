package com.learning.spring.kafkademo1.bean;

import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Data
public class TestCase {
    private int id;
    private String name;
    private String simpleName;
    private String module;
    private byte[] data;
    private boolean invalid;
    private int steps;
    private Date lastModified;
    public static final String CASE_PATTERN = "com.successfactors.test.qray.cases.";

    public String getPayloadSize() {
        if (data == null) return "0K";
        return data.length / 1024 + "K";
    }

    public void setData(String data) {
        if (data == null) return;
        try {
            this.data = data.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
