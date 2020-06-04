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

 //final Task task = new Task("Wash dishes", false, 3);
 final List<Task> tasks =  DataSource.createTaskList();
 Observable<Task> taskObservable = Observable
         .create(new ObservableOnSubscribe<Task>() {
             @Override
             public void subscribe(ObservableEmitter<Task> emitter) throws Exception {
                 for(Task task: DataSource.createTaskList()){
                     if(!emitter.isDisposed()){
                         emitter.onNext(task);
                     }
                 }
                 if(!emitter.isDisposed()){
                     emitter.onComplete();
                 }
             }
         })
         .subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread());

 taskObservable.subscribe(new Observer<Task>() {
     @Override
     public void onSubscribe(Disposable d) {

     }

     @Override
     public void onNext(Task task) {
         Log.d(TAG, "onNext: "+ task.getDescription());
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