package vn.kozu.data.remote.osu

import retrofit2.http.GET
import retrofit2.http.Query

interface OsuApi {

    @GET("beatmapsets/search")
    suspend fun searchBeatmapsets(
        @Query("q") query: String,
        @Query("page") page: Int = 1
    ): BeatmapsetSearchResponse
}