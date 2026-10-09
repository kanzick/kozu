package vn.kozu.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import vn.kozu.data.repository.FakeBeatmapRepository
import vn.kozu.domain.model.Beatmap

data class SearchUiState(
    val query: String = "",
    val results: List<Beatmap> = emptyList(),
    val isLoading: Boolean = false,
    val hasSearched: Boolean = false,
    val errorMessage: String? = null
)

class SearchViewModel : ViewModel() {

    private val repository = FakeBeatmapRepository()

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun onQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(
            query = query,
            results = emptyList(),
            hasSearched = false,
            errorMessage = null
        )
    }

    fun search() {
        val query = _uiState.value.query.trim()
        if (query.isBlank()) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                hasSearched = true,
                results = emptyList(),
                errorMessage = null
            )

            try {
                val results = repository.search(query)
                _uiState.value = _uiState.value.copy(
                    results = results,
                    isLoading = false
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    results = emptyList(),
                    isLoading = false,
                    errorMessage = "Không thể tìm kiếm. Hãy thử lại."
                )
            }
        }
    }
}