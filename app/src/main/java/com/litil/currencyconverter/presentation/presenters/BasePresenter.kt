package com.litil.currencyconverter.presentation.presenters

import com.litil.currencyconverter.presentation.views.BaseView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<T: BaseView> {
    var view: T? = null

    fun attachView(view: T) {
        this.view = view
        this.onViewAttached()
    }

    open fun onViewAttached() {}

    fun detachView() {
        this.view = null
    }
}