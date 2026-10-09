
package vn.kozu.domain.model

data class Beatmap(
    val id: Long,
    val title: String,
    val artist: String,
    val mapper: String,
    val coverUrl: String? = null
)