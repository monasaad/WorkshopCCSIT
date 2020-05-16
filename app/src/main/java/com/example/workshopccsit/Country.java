package com.example.workshopccsit;


public class Country {

    String name;
    int ATTEND;
    public Country(String name, int attend) {
        this.name = name;
        this.ATTEND=attend;
    }

    public int getATTEND() {
        return ATTEND;
    }

    public void setATTEND(int ATTEND) {
        this.ATTEND = ATTEND;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
