package com.manta.oneline

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.subscribeOnBackground(onSuccess : (T)->Unit): Disposable = run {
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe { data -> onSuccess(data) }
}


fun Completable.subscribeOnBackground(onSuccess : ()->Unit) = run {
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe{ onSuccess()}
}
