package com.sssakib.servletcrudapp.model

import com.google.gson.annotations.SerializedName

data class UserDataModel (
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String?,

    @SerializedName("outCode")
    var outCode: String?,

    @SerializedName("outMessege")
    var outMessege: String?,

        )