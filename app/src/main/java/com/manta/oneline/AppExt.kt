package com.manta.oneline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

object AppExt {

    /**
     * 메인스레드에서 observer를 실행하고, Maybe가 observer에게 notify하는건 backgroud 스레드에서 한다.
     */
    fun <T> Maybe<T>.subscribeOnBackground(disposable: CompositeDisposable, onComplete: () -> Unit, onError : (Throwable)->Unit = {}, onSuccess : (T)->Unit)= run {
        subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete { onComplete() }
            .doOnError { onError(it) }
            .subscribe{onSuccess(it)}
            .let { disposable.add(it) }
    }


    /**
     * 메인스레드에서 observer를 실행하고, Completable이 observer에게 notify하는건 backgroud 스레드에서 한다.
     */
    fun Completable.subscribeOnBackground(disposable: CompositeDisposable, onComplete : ()->Unit) = run {
        subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { onComplete() }
            .let { disposable.add(it) }
    }


}