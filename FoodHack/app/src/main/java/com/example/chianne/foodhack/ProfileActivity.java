package com.example.chianne.foodhack;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends Fragment {
    Context c;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        c = container.getContext();
        View v =  inflater.inflate(R.layout.activity_profile, container, false);

        ImageView chinese = (ImageView) v.findViewById(R.id.chinese);
        Picasso.with(c).load(R.drawable.chinese).resize(200,200).into(chinese);
        ImageView italian = (ImageView) v.findViewById(R.id.italian);
        Picasso.with(c).load(R.drawable.italian).resize(200,200).into(italian);
        ImageView indian = (ImageView) v.findViewById(R.id.indian);
        Picasso.with(c).load(R.drawable.indian).resize(200,200).into(indian);
        ImageView mexican = (ImageView) v.findViewById(R.id.mexican);
        Picasso.with(c).load(R.drawable.mexican).resize(200,200).into(mexican);
        return v;
    }

}
