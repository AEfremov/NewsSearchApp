package com.aefremov.news.main

import com.aefremov.news.data.ArticlesRepository
import com.aefremov.news.data.RequestResult
import com.aefremov.news.data.map
import com.aefremov.news.data.model.Article

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class GetAllArticlesUseCase @Inject constructor(
    private val repository: ArticlesRepository
) {

    operator fun invoke(query: String): Flow<RequestResult<List<ArticleUI>>> {
        return repository.getAll(query)
            .map { requestResult ->
                requestResult.map { articles -> articles.map { it.toUiArticle() } }
            }
    }

}

private fun Article.toUiArticle(): ArticleUI {
    return ArticleUI(
        id = cacheId,
        title = title,
        description = description,
        imageUrl = urlToImage,
        url = url
    )
}
