package com.example.coopdemo2.api

import com.example.coopdemo2.api.model.Breed
import com.example.coopdemo2.api.model.CatPicture
import retrofit2.http.GET
import retrofit2.http.Query

// end points defined here
interface CatApiService {
    @GET("images/search")
    suspend fun getRandomCatPicture(
        @Query("limit") limit: Int = 10
    ): List<CatPicture>

    @GET("breeds")
    suspend fun getBreeds(): List<Breed>

    @GET("images/search")
    suspend fun getPicturesByBreed(
        @Query("breed_ids") breedId: String,
        @Query("limit") limit: Int = 10
    ): List<CatPicture>
}