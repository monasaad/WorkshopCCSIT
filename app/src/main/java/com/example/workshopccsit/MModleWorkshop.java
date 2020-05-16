package com.example.workshopccsit;

public class MModleWorkshop {

    private int id, reg_count;
    private byte[] image;
    private String title, Duraton,seatNo, Presenter, Date,Location;

    public MModleWorkshop(byte[] image,int id, String title,  String Presenter,String Duration, String Date,String Location ,String seatNo,int reg_count) {

        this.title = title;
        this.Duraton = Duration;
        this.Presenter = Presenter;
        this.Date = Date;
        this.Location=Location;
        this.seatNo=seatNo;
        this.id=id;
        this.image = image;
        this.reg_count=reg_count;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReg_count() {
        return reg_count;
    }

    public void setReg_count(int reg_count) {
        this.reg_count = reg_count;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuraton() {
        return Duraton;
    }

    public void setDuraton(String duraton) {
        Duraton = duraton;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getPresenter() {
        return Presenter;
    }

    public void setPresenter(String presenter) {
        Presenter = presenter;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
