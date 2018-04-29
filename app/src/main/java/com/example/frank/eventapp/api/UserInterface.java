package com.example.frank.eventapp.api;

import com.example.frank.eventapp.data.Response;
import com.example.frank.eventapp.data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by FRANK on 28/04/2018.
 */

public interface UserInterface {

    @POST("/user/newUser")
    Call<Response> createNewUser(@Body User user);

}
