package com.example.mareu.model;

import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Objects;

public class Meeting {

    private long id;
    private int color;
    private String location;
    private String date;
    private String hour;
    private String subject;
    private String participants;


    public Meeting(long id, int color, String location, String date, String hour, String subject, String participants){
        this.id = id;
        this.color = color;
        this.location = location;
        this.date = date;
        this.hour = hour;
        this.subject = subject;
        this.participants = participants;
    }

    public long getId(){ return id;}

    public void setId(long id){this.id = id;}

    public int getColor(){ return color;}

    public void setColor(int color){ this.color = color;}

    public String getLocation(){ return location;}

    public void setLocation(String location){ this.location = location;}

    public String getDate(){return date;}

    public void setDate(String date){this.date = date;}

    public String getHour(){return hour;}

    public void setHour(String hour){this.hour = hour;}

    public String getSubject(){ return subject;}

    public void setSubject(String subject){ this.subject = subject;}

    public String getParticipants(){return participants;}

    public void setParticipants(String participants){ this.participants = participants;}

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(id , meeting.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}


