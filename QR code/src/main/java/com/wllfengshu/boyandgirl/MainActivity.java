package com.wllfengshu.boyandgirl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private FragmentManager fm;
    private FragmentTransaction transaction;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getFragmentManager();
        transaction = fm.beginTransaction();
        transaction.replace(R.id.ll_fregment, new FaceFragment());
        transaction.commit();

    }

    public void change(View v) {
        transaction = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.ib_main_face:
                transaction.replace(R.id.ll_fregment, new FaceFragment());
                break;
            case R.id.ib_main_gesture:
                transaction.replace(R.id.ll_fregment, new GestureFragment());
                break;
            case R.id.ib_main_other:
                transaction.replace(R.id.ll_fregment, new OtherFragment());
                break;
        }
        transaction.commit();
    }

}
