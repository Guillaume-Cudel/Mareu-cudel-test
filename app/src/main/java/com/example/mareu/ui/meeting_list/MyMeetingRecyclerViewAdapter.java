package com.example.mareu.ui.meeting_list;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mareu.R;
import com.example.mareu.events.ChangeColorMeetingEvent;
import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.model.Meeting;


import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;

    public MyMeetingRecyclerViewAdapter(List<Meeting> items) {
        mMeetings = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_meeting, parent, false);
        return new ViewHolder(view);
    }

    public int getDrawableColor(int color) {
        if (color == 1) {
           return R.drawable.ic_lens_cyan;
        } else if (color == 2) {
           return R.drawable.ic_lens_green;
        } else if (color == 3) {
            return R.drawable.ic_lens_red;
        } else if (color == 4) {
            return R.drawable.ic_lens_pink;
        } else {
            return R.drawable.ic_lens;
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Meeting meeting = mMeetings.get(position);
        String location = meeting.getLocation();
        String date = meeting.getDate();
        String hour = meeting.getHour();
        String subject = meeting.getSubject();
        String informations = location + " - " + date + " at "+ hour + " - " + subject;
        holder.mMeetingInformations.setText(informations);
        holder.mMeetingParticipants.setText(meeting.getParticipants());
        Glide.with(holder.mMeetingColor.getContext())
                .load(getDrawableColor(meeting.getColor()))
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mMeetingColor);

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
                }
        });

    }


    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_color)
        public ImageView mMeetingColor;
        @BindView(R.id.item_list_informations)
        public TextView mMeetingInformations;
        @BindView(R.id.item_list_participants)
        public TextView mMeetingParticipants;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
