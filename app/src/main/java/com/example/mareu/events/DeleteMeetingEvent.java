package com.example.mareu.events;

import com.example.mareu.model.Meeting;

public class DeleteMeetingEvent {

    public Meeting meeting;

    public DeleteMeetingEvent(Meeting meeting){ this.meeting = meeting;}
}
