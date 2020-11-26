package com.example.mareu.events;

import com.example.mareu.model.Meeting;

public class ChangeColorMeetingEvent {

    public Meeting meeting;

    public ChangeColorMeetingEvent(Meeting meeting){ this.meeting = meeting;}
}

