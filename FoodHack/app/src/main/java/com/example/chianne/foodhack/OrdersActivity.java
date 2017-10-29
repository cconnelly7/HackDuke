package com.example.chianne.foodhack;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

public class OrdersActivity extends Fragment {

    @Nullable


    FrameLayout frame;
    View inflatedView;
    int CONTENT_VIEW_ID = 42;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        inflatedView = inflater.inflate(R.layout.fragment_request_submission, container, false);
        FrameLayout frame = (FrameLayout) inflatedView.findViewById(R.id.ordersFrame);
        frame.setId(CONTENT_VIEW_ID);
        getActivity().setContentView(frame, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        if (savedInstanceState == null) {
            Fragment newFragment = new RequestSubmissionFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(CONTENT_VIEW_ID, newFragment).commit();
        }

        return inflater.inflate(R.layout.activity_orders, container, false);
    }

}
