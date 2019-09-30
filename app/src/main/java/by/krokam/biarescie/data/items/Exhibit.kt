package by.krokam.biarescie.data.items

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName

@Entity
class Exhibit(
        @PrimaryKey
        @NonNull
        var id : String = "",
        @SerializedName("id_point")
        var idPoint : Int = 0,
        var name : String = "",
        @SerializedName("point_museum")
        var pointMuseum : String = "",
        var text : String = "",
        @SerializedName("long_description")
        var textLong : String = "long text",
        var sound : String = "",
        var lang : Int = 1,
        @SerializedName("last_edit_time")
        var lastEdittime : String = "",
        var logo : String = "",
        var photo : String = "",
        @SerializedName("city_id")
        var cityId : String = "",
        var visible : String = "",
        @Ignore
        var images : List<String> = listOf(),
        var image : String = ""

)