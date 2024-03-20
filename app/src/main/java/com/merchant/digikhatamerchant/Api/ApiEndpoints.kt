package com.digikhata.merchant.Api

import com.example.authdemo.models.response.auth.SignUpResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST


interface ApiEndpoints {

 /*   @POST("Validate/Retailer")
    suspend fun loginUser(
        @HeaderMap headerMap: HashMap<String, String>,
    ): Response<SignUpResponse>


    @GET("hotelbooking/ppinl")
    suspend fun getHotelUrl(
        @HeaderMap headerMap: HashMap<String, String>
    ): Response<SignUpResponse>


    @GET("railbooking")
    suspend fun getTrainBookUrl(
        @HeaderMap headerMap: HashMap<String, String>
    ): Response<SignUpResponse>

    @GET("Help/Item/{Sub_id}/{id}")
    suspend fun getItemList(
        @HeaderMap headerMap: HashMap<String, String>,
        @Path(value = "Sub_id") Sub_id: String,
        @Path(value = "id") id: String
    ): Response<List<ItemList>>*/
 @POST("Validate/ChangePwd")
 suspend fun geChangePassword(
     @HeaderMap headerMap: HashMap<String, String>,
     @Body data: RequestBody
 ): Response<SignUpResponse>

}