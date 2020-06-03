package com.example.rxandroidlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.Observable;
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

        Observable<Task> taskObservable = Observable
                .fromIterable(DataSource.createTaskList())
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<Task>() {
                    @Override
                    public boolean test(Task task) throws Exception {
                        Log.d(TAG, "test: " + Thread.currentThread().getName());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        return task.isComplected();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());

        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: called");
                disposable.add(d);
            }

            @Override
            public void onNext(Task task) {
                Log.d(TAG, "onNext: " + Thread.currentThread().getName());
                Log.d(TAG, "onNext: " + task.getDescription());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e);

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: called");

            }
        });
        //also another way to Observable, also dont forget to add to disposable, because its subscribe  is public final Disposable, need to add disposable.add();
        disposable.add(taskObservable.subscribe(new Consumer<Task>() {
            @Override
            public void accept(Task task) throws Exception {

            }
        }));
    }
 //need to create disposable, and clear its just clear subscribers on observable, but dispose clear everything, suggest to use clear, also in MVVM or MVC need in those class'es create clear disposable.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}