package com.example.rxandroidlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    private CompositeDisposable disposable = new CompositeDisposable();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);

 Observable<Integer> taskObservable = Observable
         .range(0,4)
         .subscribeOn(Schedulers.io())
         .repeat(4) //its gonna repeat 4 times from 0 to 3;
         .observeOn(AndroidSchedulers.mainThread());

 taskObservable.subscribe(new Observer<Integer>() {
     @Override
     public void onSubscribe(Disposable d) {

     }

     @Override
     public void onNext(Integer integer) {
         Log.d(TAG, "onNext: "+ integer);
     }

     @Override
     public void onError(Throwable e) {

     }

     @Override
     public void onComplete() {

     }
 });




    }
}