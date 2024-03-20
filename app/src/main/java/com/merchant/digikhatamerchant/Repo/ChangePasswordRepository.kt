package com.merchant.digikhatamerchant.Repo

import android.content.SharedPreferences
import com.digikhata.merchant.Api.ApiEndpoints
import com.digikhata.merchant.Extensions.logD
import com.digikhata.merchant.Extensions.logE
import com.digikhata.merchant.Utils.Resource
import com.example.authdemo.models.response.auth.SignUpResponse

import com.google.gson.Gson
import com.merchant.digikhatamerchant.Model.Request.ChangePasswordRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Response

class ChangePasswordRepository(
    private val apiService: ApiEndpoints,
    private val sharedPrefs: SharedPreferences,
) {
    suspend fun callChangePassResponse(
        request: ChangePasswordRequest,
        accessToken: String?
    ): Flow<Resource<SignUpResponse>> =
        flow {
            try {
                val contentType = "application/json; charset=utf-8"  //text/plain
                val header = getHeader(accessToken)
                val requestBody =
                    getRequestBody(request).toRequestBody(contentType.toMediaTypeOrNull())
                var apiResponse: Response<SignUpResponse>? = null
                apiResponse = apiService.geChangePassword(header, requestBody)

                if (apiResponse.isSuccessful) {
                    val response = apiResponse.body()!!
                    //val status = response.status

                    logD("CheckVerifyEmailIdResponse", "${response!!}")

                    if (apiResponse.code() == 200) {
                        try {
                            val jsonObject = JSONObject(Gson().toJson(response))
                            //val message = jsonObject.getString("message")
                            /*val jsonMain = jsonObject.getJSONObject("hourly")
                            val gson = Gson()
                            logD("CheckVerifyEmailIdResponseIns", "${jsonObject!!}")
                            val weatherData = gson.fromJson(jsonMain.toString(), SignUpResponse::class.java)*/
                            emit(Resource.Success(response))
                        } catch (e: Exception) {
                            logD(
                                "CheckVerifyEmailIdDataExceptionIn200",
                                e.localizedMessage ?: "Exception"
                            )
                            emit(Resource.Error("Token is expired"))
                        }
                    }
                }
            } catch (e: Exception) {
                logD("CheckVerifyEmailIdDataExceptionInCatch", e.localizedMessage ?: "Exception")
                emit(Resource.Error("Token is expired"))
            }

        }

    private fun getHeader(
        accessToken: String?
    ): HashMap<String, String> {
        val headerMap = HashMap<String, String>()
        headerMap["Content-Type"] = "application/json"
        headerMap["authorization"] = "bearer " + accessToken
        return headerMap
    }

    private fun getRequestBody(verifyEmailIdRequest: ChangePasswordRequest): String {
        val body = JSONObject()
        body.put("BusinessId", verifyEmailIdRequest.BusinessId)
        body.put("DeviceCode", verifyEmailIdRequest.DeviceCode.toString())
        body.put("DevicePassword", verifyEmailIdRequest.DevicePassword)
        body.put("NewPassword", verifyEmailIdRequest.NewPassword.toString())
        logE("CheckVerifyEmailIdRequest", "getRequestBody: ${body.toString()}")
        return body.toString().replace("\\n", "")
    }

}
