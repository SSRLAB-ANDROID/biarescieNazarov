package by.krokam.biarescie.retrofit

import by.krokam.biarescie.data.items.Exhibit
import by.krokam.biarescie.data.items.Section
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("get_cities/1/")
    fun getSections(): Observable<List<Section>>

    @GET("get_points/1/")
    fun getExhibits(): Observable<List<Exhibit>>
}