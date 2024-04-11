package com.aefremov.news.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aefremov.news.data.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class NewsMainViewModel @Inject constructor(
    getAllArticlesUseCase: GetAllArticlesUseCase
) : ViewModel() {

    val state: StateFlow<State> = getAllArticlesUseCase.invoke(query = "android")
        .map { it.toState() }
        .stateIn(viewModelScope, SharingStarted.Lazily, State.None)

    fun forceUpdate() {
    }
}

private fun RequestResult<List<ArticleUI>>.toState(): State {
    return when (this) {
        is RequestResult.Error -> State.Error(data)
        is RequestResult.InProgress -> State.Loading(data)
        is RequestResult.Success -> State.Success(data)
    }
}

internal sealed class State(val articles: List<ArticleUI>?) {

    data object None : State(articles = null)
    class Loading(articles: List<ArticleUI>? = null) : State(articles)
    class Error(articles: List<ArticleUI>? = null) : State(articles)
    class Success(articles: List<ArticleUI>) : State(articles)
}