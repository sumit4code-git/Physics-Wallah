package com.example.physicswallah.repo

import com.example.physicswallah.api.TeachersApi
import com.example.physicswallah.model.UsersResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class AuthRepository @Inject constructor(
     val teachersApi: TeachersApi
) {

    /*********** Get Details Of Teachers*************/
     fun getUserInfo(
        onSuccess: (UsersResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        teachersApi.getTeachersDetails().enqueue(object : Callback<UsersResponse> {

            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    onSuccess(response.body()!!)
                } else if (response.code() == 400) {
                    if (response.errorBody() != null) {
                        onError(JSONObject(response.errorBody()!!.string()).optString("message"))
                    }
                } else {
                    onError("Something went wrong, please try again")
                }
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                onError("Something went wrong, please try again")
            }

        })
    }


}