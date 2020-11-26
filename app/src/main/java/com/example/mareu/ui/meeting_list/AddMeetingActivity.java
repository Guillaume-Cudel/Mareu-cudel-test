package com.example.mareu.ui.meeting_list;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mareu.R;
import com.example.mareu.di.Di;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;

import static com.example.mareu.ui.meeting_list.MeetingFragment.BUNDLE_EXTRA_COLOR;
import static com.example.mareu.ui.meeting_list.MeetingFragment.BUNDLE_EXTRA_DATE;
import static com.example.mareu.ui.meeting_list.MeetingFragment.BUNDLE_EXTRA_HOUR;
import static com.example.mareu.ui.meeting_list.MeetingFragment.BUNDLE_EXTRA_ID;
import static com.example.mareu.ui.meeting_list.MeetingFragment.BUNDLE_EXTRA_LOCATION;
import static com.example.mareu.ui.meeting_list.MeetingFragment.BUNDLE_EXTRA_PARTICIPANTS;
import static com.example.mareu.ui.meeting_list.MeetingFragment.BUNDLE_EXTRA_SUBJECT;


public class AddMeetingActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.add_color)
    ImageView mAddColor;
    @BindView(R.id.spinner_location)
    Spinner mSpinnerLocation;
    @BindView(R.id.select_date)
    EditText mSelectDate;
    @BindView(R.id.button_date)
    Button mButtonDate;
    @BindView(R.id.select_hour)
    EditText mSelectHour;
    @BindView(R.id.button_hour)
    Button mButtonHour;
    @BindView(R.id.add_subject)
    TextInputEditText mAddSubject;
    @BindView(R.id.add_participants)
    TextInputEditText mAddParticipants;
    @BindView(R.id.add_save)
    MaterialButton mSave;

    private MeetingApiService mApiService;
    private int mMeetingColor = 0;
    private String[] room = {"Room A", "Room B", "Room C", "Room D", "Room E", "Room F","Room G", "Room H", "Room I", "Room J"};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        mButtonDate.setOnClickListener(this);
        mButtonHour.setOnClickListener(this);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, room);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerLocation.setAdapter(adapter1);


        mApiService = Di.getNewInstanceApiService();
        init();
    }

   /* @OnItemSelected(R.id.spinner_location)
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), "Selected room: "+ item, Toast.LENGTH_SHORT).show();
    }*/

   @Override
   public void onClick(View v){
       if (v == mButtonDate){
           final Calendar c = Calendar.getInstance();
           int mYear = c.get(Calendar.YEAR);
           int mMonth = c.get(Calendar.MONTH);
           int mDay = c.get(Calendar.DAY_OF_MONTH);

           DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
               @Override
               public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                   mSelectDate.setText(dayOfMonth + "-" + month + "-" + year);
               }
           }, mYear, mMonth, mDay);
           datePickerDialog.show();
       }
       if (v == mButtonHour){
           final Calendar c = Calendar.getInstance();
           int mHour = c.get(Calendar.HOUR_OF_DAY);
           int mMinute = c.get(Calendar.MINUTE);

           TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
               @Override
               public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                   mSelectHour.setText(hourOfDay + ":" + minute);
               }
           }, mHour, mMinute, true);
           timePickerDialog.show();
       }
   }


    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void init(){
       changeColor(mMeetingColor);
       Glide.with(this).load(mMeetingColor).apply(RequestOptions.circleCropTransform()).into(mAddColor);
        mAddParticipants.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) { mSave.setEnabled(s.length() > 0);}
        });
    }


     @OnClick(R.id.add_color)
    void onClickChangeColor(){
       changeColor(new Random().nextInt(5));
    }

    @OnClick(R.id.add_save)
    void addMeeting(){
        Intent intent = getIntent();

        long idChoosed = System.currentTimeMillis();
        int colorChoosed = mMeetingColor;
        String roomChoosed = mSpinnerLocation.getSelectedItem().toString();
        String dateChoosed = mSelectDate.getEditableText().toString();
        String hourChoosed = mSelectHour.getEditableText().toString();
        String subjectChoosed = mAddSubject.getEditableText().toString();
        String participantsChoosed = mAddParticipants.getEditableText().toString();

        intent.putExtra(BUNDLE_EXTRA_ID, idChoosed);
        intent.putExtra(BUNDLE_EXTRA_COLOR, colorChoosed);
        intent.putExtra(BUNDLE_EXTRA_LOCATION, roomChoosed);
        intent.putExtra(BUNDLE_EXTRA_DATE, dateChoosed);
        intent.putExtra(BUNDLE_EXTRA_HOUR, hourChoosed);
        intent.putExtra(BUNDLE_EXTRA_SUBJECT, subjectChoosed);
        intent.putExtra(BUNDLE_EXTRA_PARTICIPANTS, participantsChoosed);


       /* Meeting meeting = new Meeting(
                System.currentTimeMillis(),
                mMeetingColor,
                roomChoosed,
                mSelectDate.getEditableText().toString(),
                mSelectHour.getEditableText().toString(),
                mAddSubject.getEditableText().toString(),
                mAddParticipants.getEditableText().toString());

        intent.putExtra(BUNDLE_EXTRA_ID, meeting.getId());
        intent.putExtra(BUNDLE_EXTRA_COLOR, meeting.getColor());
        intent.putExtra(BUNDLE_EXTRA_LOCATION, meeting.getLocation());
        intent.putExtra(BUNDLE_EXTRA_DATE, meeting.getDate());
        intent.putExtra(BUNDLE_EXTRA_HOUR, meeting.getHour());
        intent.putExtra(BUNDLE_EXTRA_SUBJECT, meeting.getSubject());
        intent.putExtra(BUNDLE_EXTRA_PARTICIPANTS, meeting.getParticipants());*/
        // mApiService.createMeeting(meeting);
        setResult(RESULT_OK, intent);
        finish();
    }




    public void changeColor(int color) {
        mMeetingColor = color;
        if (color == 1) {
            mAddColor.setImageResource(R.drawable.ic_lens_cyan);
        } else if (color == 2) {
            mAddColor.setImageResource(R.drawable.ic_lens_green);
        } else if (color == 3) {
            mAddColor.setImageResource(R.drawable.ic_lens_red);
        } else if (color == 4) {
            mAddColor.setImageResource(R.drawable.ic_lens_pink);
        } else if (color == 0) {
            mAddColor.setImageResource(R.drawable.ic_lens);
        }
    }

    /*public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddMeetingActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }*/
}
