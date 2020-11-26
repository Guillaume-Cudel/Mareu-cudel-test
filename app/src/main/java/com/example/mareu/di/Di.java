package com.example.mareu.di;

import com.example.mareu.service.DummyMeetingApiService;
import com.example.mareu.service.MeetingApiService;

public class Di {

    private static MeetingApiService service = new DummyMeetingApiService();

    public static MeetingApiService getMeetingApiService(){ return service; }

    /**
     * Get alway a new instance. Useful for test.
     * @return
     */
    public static MeetingApiService getNewInstanceApiService(){
        return new DummyMeetingApiService();
    }
}
