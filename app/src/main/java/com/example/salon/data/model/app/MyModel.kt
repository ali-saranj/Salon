package com.example.salon.data.model.app

import com.example.salon.ui.activity.MainActivity
import com.example.tools.StringSpace
import com.example.tools.haversine
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class MyModel(
    @SerializedName("id")
    val id:Long,
    @SerializedName("name")
    val name:String
){

//    constructor(myModel: MyModel) : this(id = myModel.id,name=myModel.name)


//    constructor(str:String) : this(
//        id = str.split(',')[0].toLong(),
//        name = str.split(',')[1]
//    )

    val space:String
        get() = StringSpace(.0,.0,.0,.0)

    val ModelString:String
        get() = "$id,$name"


}
