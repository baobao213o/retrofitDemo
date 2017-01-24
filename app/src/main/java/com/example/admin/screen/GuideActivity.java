package com.example.admin.screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class GuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        Integer data[]={1,2,3,4,5,6,7,8,9,10};

        Observable.fromArray(data).observeOn(AndroidSchedulers.mainThread()).flatMap(new Function<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> apply(final Integer integer) throws Exception {

                return Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        System.out.println("call: FlatMap " + Thread.currentThread().getName());
                        try {
                            Thread.sleep(2000);
                            emitter.onNext(integer + 100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).subscribeOn(Schedulers.newThread());
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integers) throws Exception {
                return integers+"--aaaaa";
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });

    }



}
