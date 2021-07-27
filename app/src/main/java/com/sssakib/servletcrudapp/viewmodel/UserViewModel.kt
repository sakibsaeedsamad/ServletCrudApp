package com.sssakib.servletcrudapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sssakib.servletcrudapp.model.LoginModel
import com.sssakib.servletcrudapp.model.UserDataModel
import com.sssakib.servletcrudapp.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class UserViewModel : ViewModel() {

    private val apiService = RetrofitClient()
    private val disposable = CompositeDisposable()

    var userLogin_response = MutableLiveData<UserDataModel>();
    var login_response_error = MutableLiveData<Boolean>();

    fun doLogin(model: LoginModel) {

        disposable.add(
            apiService.doLogin(model)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UserDataModel>() {
                    override fun onSuccess(model: UserDataModel) {
                        model?.let {
                            userLogin_response.value = model
                        }

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        login_response_error.value = true
                        Log.e("Login-->", e.toString())

                    }

                })
        )
    }

}