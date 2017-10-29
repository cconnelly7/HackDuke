package com.example.chianne.foodhack;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RequestSubmissionFragment requestFragment = new RequestSubmissionFragment();
                getActivity().getSupportFragmentManager().findFragmentById(R.id.ordersFrame);
        setFragment(requestFragment);

        return inflater.inflate(R.layout.activity_orders, container, false);
    }

    // This could be moved into an abstract BaseActivity
    // class for being re-used by several instances
    protected void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ordersFrame, fragment);
        fragmentTransaction.commit();
    }
}
