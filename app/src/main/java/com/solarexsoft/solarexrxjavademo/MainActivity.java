package com.solarexsoft.solarexrxjavademo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.solarexsoft.solarexrxjava.Function;
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
        findViewById(R.id.btn_map).setOnClickListener(this);
        findViewById(R.id.btn_io).setOnClickListener(this);
        findViewById(R.id.btn_main).setOnClickListener(this);
        findViewById(R.id.btn_iomain).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_normal:
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(Observer<? super String> observer) {
                        Log.d(TAG, "this = " + this + ",normal before subscribe --> " + Thread.currentThread().getName());
                        observer.onNext("solarex");
                        Log.d(TAG, "this = " + this + ",normal after subscribe --> " + Thread.currentThread().getName());
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
                break;
            case R.id.btn_map:
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(Observer<? super String> observer) {
                        Log.d(TAG, "this = " + this + ",map before subscribe --> " + Thread.currentThread().getName());
                        observer.onNext("solarex");
                        Log.d(TAG, "this = " + this + ",map after subscribe --> " + Thread.currentThread().getName());
                    }
                }).map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String s) {
                        Log.d(TAG, "this = " + this + ",map apply " + s + " --> " + Thread.currentThread().getName());
                        return Bitmap.createBitmap(100, 100, Bitmap.Config.ALPHA_8);
                    }
                }).subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onNext(Bitmap bitmap) {
                        Log.d(TAG, "map " + this + " onNext " + bitmap + " --> " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
                break;
            case R.id.btn_io:
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(Observer<? super String> observer) {
                        Log.d(TAG, "this = " + this + ",io before subscribe --> " + Thread.currentThread().getName());
                        observer.onNext("solarex");
                        Log.d(TAG, "this = " + this + ",io after subscribe --> " + Thread.currentThread().getName());
                    }
                }).subscribeOnIO().subscribe(new Observer<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "this = " + this + ",io " + this + " onNext " + s + " --> " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
                break;
            case R.id.btn_main:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "main run --> " + Thread.currentThread().getName());
                        Observable.create(new ObservableOnSubscribe<String>() {
                            @Override
                            public void subscribe(Observer<? super String> observer) {
                                Log.d(TAG, "this = " + this + ",main before subscribe --> " + Thread.currentThread().getName());
                                observer.onNext("solarex");
                                Log.d(TAG, "this = " + this + ",main after subscribe --> " + Thread.currentThread().getName());
                            }
                        }).subscribeOnMain().subscribe(new Observer<String>() {
                            @Override
                            public void onNext(String s) {
                                Log.d(TAG, "this = " + this + ",main " + this + " onNext " + s + " --> " + Thread.currentThread().getName());
                            }

                            @Override
                            public void onError(Throwable throwable) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                    }
                }, "solarex-thread").start();
                break;
            case R.id.btn_iomain:
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(Observer<? super String> observer) {
                        Log.d(TAG, "io/main before subscribe --> " + Thread.currentThread().getName());
                        observer.onNext("solarex");
                        Log.d(TAG, "io/main after subscribe --> " + Thread.currentThread().getName());
                    }
                }).subscribeOnIO().subscribeOnMain().subscribe(new Observer<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "io/main " + this + " onNext " + s + " --> " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
                break;
        }
    }
}
