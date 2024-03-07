package com.aefremov.news.main

import com.aefremov.news.data.ArticlesRepository
import com.aefremov.news.data.RequestResult
import com.aefremov.news.data.model.Article

import kotlinx.coroutines.flow.Flow

class GetAllArticlesUseCase(private val repository: ArticlesRepository) {

    operator fun invoke(): RequestResult<Flow<List<Article>>> {
        return repository.getAll()
    }
}