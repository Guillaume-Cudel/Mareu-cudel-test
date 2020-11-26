package com.example.mareu.service;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;
import com.example.mareu.ui.meeting_list.MeetingFragment;
import com.example.mareu.ui.meeting_list.MeetingListActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public List<Meeting> getMeetingsByDateAndRoom(String date, String room) {
        List<Meeting> filterMeetings = new ArrayList<Meeting>();
       for( int i = 0; i < meetings.size(); i++) {
           Meeting getMeeting = meetings.get(i);
           if (getMeeting.getDate().equals(date) && getMeeting.getLocation().equals(room)) {
                   filterMeetings.add(getMeeting);
               }
           if (getMeeting.getDate().equals(date) && room.equals(" - ")) {
                   filterMeetings.add(getMeeting);
               }
           if (date.equals("") && getMeeting.getLocation().equals(room)) {
                   filterMeetings.add(getMeeting);
               }
           }
        return filterMeetings;
    }
}







