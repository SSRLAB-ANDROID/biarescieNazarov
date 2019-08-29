package by.krokam.biarescie.data.items

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName

@Entity
class Section(
        var id : String = "",
        @SerializedName("id_locale")
        @PrimaryKey
        @NonNull
        var idLocale : Int = 0,
        var name : String = "",
        var lang : Int = 1,
        var logo : String = "",
        @SerializedName("last_edit_time")
        var lastEdittime : String = "",
        var visible : String = "",
        @SerializedName("name_prefix")
        var namePrefix: String=""
)