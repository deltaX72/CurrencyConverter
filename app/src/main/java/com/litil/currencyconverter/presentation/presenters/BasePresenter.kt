package com.litil.currencyconverter.presentation.presenters

import com.litil.currencyconverter.presentation.views.BaseView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<T: BaseView> {
    var view: T? = null

    private val compositeDisposable = CompositeDisposable()

    fun Disposable.untilDestroyed() = compositeDisposable.add(this)

    fun attachView(view: T) {
        this.view = view
        this.onViewAttached()
    }

    open fun onViewAttached() {}

    fun detachView() {
        this.compositeDisposable.clear()
        this.view = null
    }
}