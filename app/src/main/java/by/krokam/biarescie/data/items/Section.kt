package by.krokam.biarescie.data.items

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.annotation.NonNull
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