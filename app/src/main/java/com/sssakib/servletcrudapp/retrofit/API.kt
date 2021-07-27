package com.sssakib.servletcrudapp.retrofit


import com.sssakib.servletcrudapp.model.UserDataModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*


interface API {
    @FormUrlEncoded
    @POST("api-login")
    fun doLogin(
        @Field("requestCode") requestCode: String?,
        @Field("phone") phone: String?,
        @Field("password") password: String?
    ): Single<UserDataModel>

}
