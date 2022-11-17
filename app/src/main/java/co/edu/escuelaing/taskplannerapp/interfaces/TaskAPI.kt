package co.edu.escuelaing.taskplannerapp.interfaces

import co.edu.escuelaing.taskplannerapp.models.Task
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TaskAPI {
    @GET("/api/v2/tasks/{id}")
    fun find(@Path("id") id: String): Call<Task>
}