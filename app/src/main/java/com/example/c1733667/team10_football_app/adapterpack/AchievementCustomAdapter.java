package com.example.c1733667.team10_football_app.adapterpack;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.c1733667.team10_football_app.R;

import java.util.ArrayList;

/**
 * Created by c1733667 on 29/03/2018.
 */

public class AchievementCustomAdapter extends ArrayAdapter<String> {
    String[] achievement;
    Integer [] imageID;
    Context mContext;

    public AchievementCustomAdapter(@NonNull Context context, String[] achievementNames, Integer[] images) {
        super(context, R.layout.custom_achievement_list);
        this.achievement = achievementNames;
        this.imageID = images;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return achievement.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView==null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_achievement_list, parent, false);
            mViewHolder.mBadges = convertView.findViewById(R.id.achievementBadge);
            mViewHolder.mAchievements = convertView.findViewById(R.id.achievementLabel);
            convertView.setTag(mViewHolder);
        }else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
            mViewHolder.mBadges.setImageResource(R.mipmap.ic_launcher_round);
            mViewHolder.mAchievements.setText(achievement[position]);
        return convertView;
    }
    static class ViewHolder{
        ImageView mBadges;
        TextView mAchievements;
    }
}
