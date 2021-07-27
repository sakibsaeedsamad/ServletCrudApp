package com.sssakib.servletcrudapp.retrofit

import com.google.gson.GsonBuilder
import com.sssakib.servletcrudapp.model.LoginModel
import com.sssakib.servletcrudapp.model.UserDataModel
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitClient  {

    var baseurl = "http://192.168.0.7:8084/JavaServletApp/"
    var userPass =
        "Basic XtNYpaTinqHyeJ9bXsQmCutBNUrS6P7NmX+cuPTgUuy8cNKnG0UcmSuhuIfkYHWI2lA3qcGgQGxQlS2ZHW0MEVDa40k3nM7DSbW9C0GD1vNNoMK1yCoebP7DozwGsPloCHU4q28yeTJdp+pQCNFb9KO68ofIoGxiRhFUrJ6IbqhgMcyFkD5vPIV/fhayxSC+ITOEkrjhSlxGyuADKGzccIQ9lNSp07VMf0TodHoJq1IhnPGdEuyywN2Gt9fgCqke/IMKloVgzSFGe7eg7pvA+KNpH+o/BeI5qGBenBcJtipm1pUXtHyf3NXOD2H3UiUe6LJUrEKGiOQBtAUphQ+NrkBSecp1HH8lNuwVdf4UpZoMyDWbbSwzkCZPNjhuOnpfkcxfRCv/VCFQLPH6NNEvipXn25muszD3cWjJK15oGUgoGP0vR6gjnKlz52vHje+2QIz/sM2/jHa5emnITjvupZj6Tl82YuwmklNlpG0Q0DIx/x5+5p/ttBjb/i3HOcZ8UXjijSdPzmVvUIWcixQ1uPQekzTUd1pImJgKsnzYJaUaBFDTRfbIWKx5fJYSg0St+Ktm+IK2FI6oRNrM6iKV3Roa1xxo1qjCoa+X9PivA200ZS2z7VMPGVYuPlMToGvPvBNrVeKJ4Y7tpOmF7NpF2gOy6ioUeYmL6LvEbQr/WyM="


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
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Authorization", userPass)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
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
