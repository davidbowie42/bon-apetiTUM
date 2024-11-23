package com.bon.apetitum.demo;



public class Label {
    private String enum_name;
    private Desc text;
    private String abbreviation;

    public Label(String enum_name, Desc text, String abbreviation) {
        this.enum_name = enum_name;
        this.text = text;
        this.abbreviation = abbreviation;
    }

    public Label() {
    }

    public String getEnum_name() {
        return enum_name;
    }

    public void setEnum_name(String enum_name) {
        this.enum_name = enum_name;
    }

    public Desc getText() {
        return text;
    }

    public void setText(Desc text) {
        this.text = text;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
