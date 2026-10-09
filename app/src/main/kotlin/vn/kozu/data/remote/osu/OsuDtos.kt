
package vn.kozu.data.remote.osu

import com.google.gson.annotations.SerializedName

data class BeatmapsetSearchResponse(
    @SerializedName("beatmapsets")
    val beatmapsets: List<BeatmapsetDto> = emptyList(),

    @SerializedName("total")
    val total: Int = 0,

    @SerializedName("cursor_string")
    val cursorString: String? = null
)

data class BeatmapsetDto(
    val id: Long,
    val title: String,
    val artist: String,
    val creator: String,

    @SerializedName("covers")
    val covers: BeatmapCoversDto? = null
)

data class BeatmapCoversDto(
    val cover: String? = null,
    val card: String? = null,
    val list: String? = null
)