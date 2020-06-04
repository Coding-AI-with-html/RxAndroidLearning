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

 Observable<Task> taskObservable = Observable
         .range(0,9)
         .subscribeOn(Schedulers.io())
         .map(new Function<Integer, Task>() {
             @Override
             public Task apply(Integer integer) throws Exception {
                 Log.d(TAG, "apply: " + Thread.currentThread().getName());
                 return new Task("this is task with priority" + String.valueOf(integer), false, integer);
             }
         })
         .takeWhile(new Predicate<Task>() {
             @Override
             public boolean test(Task task) throws Exception {
                 return task.getPriority() < 9;
             }
         })
         .observeOn(AndroidSchedulers.mainThread());

 taskObservable.subscribe(new Observer<Task>() {
     @Override
     public void onSubscribe(Disposable d) {

     }

     @Override
     public void onNext(Task task) {
         Log.d(TAG, "onNext: "+ task.getPriority());
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