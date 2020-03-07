package com.solarexsoft.solarexrxjavademo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.solarexsoft.solarexrxjava.Observable;
import com.solarexsoft.solarexrxjava.ObservableOnSubscribe;
import com.solarexsoft.solarexrxjava.Observer;
import com.solarexsoft.solarexrxjava.RxJavaPlugins;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RxJavaPlugins.openDebug();
        findViewById(R.id.btn_normal).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_normal) {
            Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(Observer<? super String> observer) {
                    Log.d(TAG, "normal before subscribe --> " + Thread.currentThread().getName());
                    observer.onNext("solarex");
                    Log.d(TAG, "normal after subscribe --> " + Thread.currentThread().getName());
                }
            }).subscribe(new Observer<String>() {
                @Override
                public void onNext(String s) {
                    Log.d(TAG, "normal " + this + " onNext " + s + " --> " + Thread.currentThread().getName());
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onComplete() {

                }
            });
        }
    }
}
