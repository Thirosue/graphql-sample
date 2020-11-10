package com.example.demo.resolvers;

import com.example.demo.types.Location;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LocationPublisher {

    private final Flowable<Location> publisher;

    private ObservableEmitter<Location> emitter;

    public LocationPublisher() {
        Observable<Location> commentUpdateObservable = Observable.create(emitter -> {
            this.emitter = emitter;
        });

        ConnectableObservable<Location> connectableObservable = commentUpdateObservable.share().publish();
        connectableObservable.connect();

        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }

    public void publish(final Location location) {
        // データ変更を通知
        emitter.onNext(location);
    }

    public Flowable<Location> getPublisher() {
        // データ変更を配信
        return publisher;
    }
}
