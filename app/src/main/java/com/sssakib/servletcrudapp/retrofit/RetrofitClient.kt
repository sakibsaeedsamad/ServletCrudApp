package com.sssakib.servletcrudapp.retrofit

import com.google.gson.GsonBuilder
import com.sssakib.servletcrudapp.R
import com.sssakib.servletcrudapp.model.LoginModel
import com.sssakib.servletcrudapp.model.UserDataModel
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitClient  {

    var baseurl = "http://192.168.43.96:8084/JavaServletApp/"

   /* private val api = Retrofit.Builder()
        .baseUrl(baseurl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(API::class.java)*/

    var okHttpClient: OkHttpClient? = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()


    var gson = GsonBuilder()
        .setLenient()
        .create()

    private val api = Retrofit.Builder()
        .baseUrl(baseurl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(API::class.java);

    fun doLogin(model: LoginModel): Single<UserDataModel> {
        return api.doLogin(
            model.requestCode,
            model.phone,
            model.password

        )
    }

}
