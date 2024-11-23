package com.bon.apetitum.demo;

public class Desc {
    private String DE;
    private String EN;

    public Desc() {
    }

    public Desc(String DE, String EN) {

        this.DE = DE;
        this.EN = EN;
    }


    public String getDE() {
        return DE;
    }

    public void setDE(String DE) {
        this.DE = DE;
    }

    public String getEN() {
        return EN;
    }

    public void setEN(String EN) {
        this.EN = EN;
    }
}
