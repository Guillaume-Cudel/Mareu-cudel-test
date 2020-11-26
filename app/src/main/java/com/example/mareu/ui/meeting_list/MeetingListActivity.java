package com.example.mareu.ui.meeting_list;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeetingListActivity extends AppCompatActivity {

    @BindView(R.id.container)
    ViewPager mViewPager;

    private MeetingApiService mApiService;
    private Meeting meeting;
    PageAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);
        ButterKnife.bind(this);

        mPagerAdapter = new PageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

    }
        @OnClick(R.id.add_meeting)
        void addMeeting () {
        Intent secondeActivite = new Intent(MeetingListActivity.this, AddMeetingActivity.class);
        startActivityForResult(secondeActivite, MeetingFragment.REQUEST_MEETING);
          //  AddMeetingActivity.navigate(this);
        }


}


