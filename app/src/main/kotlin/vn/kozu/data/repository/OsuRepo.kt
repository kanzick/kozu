
package vn.kozu.data.repository

import vn.kozu.domain.model.Beatmap

class FakeBeatmapRepository {

    private val samples = listOf(
        Beatmap(
            id = 1L,
            title = "夜に駆ける",
            artist = "YOASOBI",
            mapper = "Sample mapper"
        ),
        Beatmap(
            id = 2L,
            title = "Lemon",
            artist = "Kenshi Yonezu",
            mapper = "Sample mapper"
        ),
        Beatmap(
            id = 3L,
            title = "Megalovania",
            artist = "Toby Fox",
            mapper = "Sample mapper"
        ),
        Beatmap(
            id = 4L,
            title = "Bad Apple!!",
            artist = "Masayoshi Minoshima",
            mapper = "Sample mapper"
        )
    )

    suspend fun search(query: String): List<Beatmap> {
        val normalized = query.trim()

        if (normalized.isBlank()) return emptyList()

        return samples.filter { beatmap ->
            beatmap.title.contains(normalized, ignoreCase = true) ||
                    beatmap.artist.contains(normalized, ignoreCase = true) ||
                    beatmap.mapper.contains(normalized, ignoreCase = true)
        }
    }
}