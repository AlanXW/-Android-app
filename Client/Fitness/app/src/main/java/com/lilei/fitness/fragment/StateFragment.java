package com.lilei.fitness.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lilei.fitness.R;
import com.lilei.fitness.utils.Constants;
import com.lilei.fitness.view.MainActivity;
import com.lilei.fitness.view.VideoPlayer;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;

import okhttp3.Call;


public class StateFragment extends Fragment implements View.OnClickListener {

    private Button start;
    private Button stop;

    private TextView time;
    private TextView mileage;
    private TextView speed;
    private TextView calorie;

    private boolean isStopCount = false;

    private boolean isPause = true;

    private Handler mHandler = new Handler();

    private long timer = 0;

    private double mileages = 0;
    private double speeds = 0;

    private String timeStr = "";



    private int type = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_state, null);
        findViewById(v);
        initView();
        return v;
    }

    public void findViewById(View v) {
        start =  (Button) v.findViewById(R.id.start);
        stop =  (Button) v.findViewById(R.id.stop);
        time = (TextView) v.findViewById(R.id.timeInfo);
        mileage = (TextView) v.findViewById(R.id.mileageInfo);
        speed = (TextView) v.findViewById(R.id.speedInfo);
        calorie = (TextView) v.findViewById(R.id.calorieInfo);
    }

    public void initView() {
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("select type");
                final String[] types = {"walk", "run"};
                builder.setItems(types, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(getActivity(), "type：" + types[which], Toast.LENGTH_SHORT).show();
                        type = which;
                        timer = 0;
                        mileages = 0;
                        isPause = true;
                        isStopCount = false;
                        countTimer();
                    }
                });
                builder.show();
            break;
            case R.id.stop:
                isPause = false;
                isStopCount = true;
                mHandler.removeCallbacks(TimerRunnable);
                String url = Constants.BASE_URL + "Train?method=addNewTrainRecord";
                OkHttpUtils
                        .post()
                        .url(url)
                        .id(1)
                        .addParams("userId", Constants.USER.getUserId() + "")
                        .addParams("duration", String.valueOf(timer/10000))
                        .build()
                        .execute(new MyStringCallback());
                break;
        }
    }

    public void DisplayToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
    public class MyStringCallback extends StringCallback {
        @Override
        public void onResponse(String response, int id) {
            switch (id) {
                case 1:
                    if (response.contains("error")) {
                        DisplayToast("Synchronization failure of exercise record");
                    }
                    break;
                default:
                    DisplayToast("what?");
                    break;
            }
        }

        @Override
        public void onError(Call arg0, Exception arg1, int arg2) {
            Toast.makeText(getActivity(), "Network error!", Toast.LENGTH_SHORT).show();
        }
    }

    private Runnable TimerRunnable = new Runnable() {

        @Override
        public void run() {
            if(!isStopCount){
                timer += 1000;
                timeStr = getFormatTime(timer);
                if(type==0){
                    mileages += Math.random()*100/100;
                }else {
                    mileages += Math.random()*100/20;
                }
                speeds = mileages/(timer/1000);
                time.setText(timeStr);
                mileage.setText(String.format("%.2f", mileages));
                calorie.setText(String.format("%.2f", getCalorie(mileages)));
                speed.setText(String.format("%.2f", speeds));
            }
            countTimer();
        }
    };

    private double getCalorie(double mileages) {
        return Constants.USER.getWeight()*mileages/1000*1.036;
    }


    private void countTimer(){
        mHandler.postDelayed(TimerRunnable, 1000);
    }

    public static String getFormatTime(long time) {
        SimpleDateFormat df1 = new SimpleDateFormat(
                "mm:ss");
        return df1.format(time);
    }

}
