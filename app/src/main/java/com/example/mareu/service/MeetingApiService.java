package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetings();

    void deleteMeeting(Meeting meeting);

    void createMeeting( Meeting meeting);

    List<Meeting> getMeetingsByDateAndRoom(String date, String room);


}
