package fr.isaacgros.todolist.network

import fr.isaacgros.todolist.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("/login")
    fun getUser(
        @Query("email") email: String,
        @Query("pwd") password: String
    ): Call<User>

}