package com.example.workshopccsit;

public class ModleWorkshop {

    private int id;
    private byte[] image;
    private String title, Duraton,seatNo, Presenter, Date,Location;

    public ModleWorkshop(byte[] image,int id, String title,  String Presenter,String Duration, String Date,String Location ,String seatNo) {

        this.title = title;
        this.Duraton = Duration;
        this.Presenter = Presenter;
        this.Date = Date;
        this.Location=Location;
        this.seatNo=seatNo;
        this.id=id;
        this.image = image;
    }
//id
public int getId() {
    return id;
}

    public void setId(int id) {
        this.id = id;
    }

//image
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

//Title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


//Presenter name
    public String getPresentern() {
        return Presenter;
    }

    public void setPresenter(String Presenter) {
        this.Presenter = Presenter;
    }



//date
    public String getData() {
        return Date;
    }

    public void setData(String Date) {
        this.Date = Date;
    }


//Location
    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

//Duraton
    public String getDuraton() {
        return Duraton;
    }

    public void setDuraton(String Duraton) { this.Duraton = Duraton; }



//seatNo

    public String getSeatNo(){
        return seatNo;
    }

    public void setSeatNo(String SeatNo){
        this.seatNo=SeatNo;
    }


    }
