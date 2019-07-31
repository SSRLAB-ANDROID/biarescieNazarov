package by.krokam.biarescie.util

import android.view.View
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun View.isGone(value : Boolean){
    visibility = if(value) View.GONE else View.VISIBLE
}

fun<T> Observable<T>.executeInBackSubOnMain() : Observable<T> {
    return this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun<T> Observable<T>.executeInBack() : Observable<T> {
    return this.subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
}

fun<T> Flowable<T>.executeInBackSubOnMain() : Flowable<T> {
    return this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun<T> Flowable<T>.executeInBack() : Flowable<T> {
    return this.subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
}

fun Disposable.addTo(composite: CompositeDisposable) {
    composite.add(this)
}