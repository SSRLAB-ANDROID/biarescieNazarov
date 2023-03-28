package by.krokam.biarescie.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.krokam.biarescie.data.items.Exhibit
import by.krokam.biarescie.data.items.Section
import io.reactivex.Flowable

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSectons(items : List<Section>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpositions(items : List<Exhibit>)

    @Query("SELECT * FROM section")
    fun getSectons() : LiveData<List<Section>>

    @Query("SELECT * FROM exhibit")
    fun getExpositions() : LiveData<List<Exhibit>>

    @Query("SELECT * FROM section ")
    fun getSectonsRx() : Flowable<List<Section>>

    @Query("SELECT * FROM exhibit ")
    fun getExpositionsRx() : Flowable<List<Exhibit>>
}
