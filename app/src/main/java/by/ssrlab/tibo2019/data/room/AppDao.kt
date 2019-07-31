package by.krokam.biarescie.data.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
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

    @Query("SELECT * FROM section")
    fun getSectonsRx() : Flowable<List<Section>>

    @Query("SELECT * FROM exhibit ")
    fun getExpositionsRx() : Flowable<List<Exhibit>>
}
