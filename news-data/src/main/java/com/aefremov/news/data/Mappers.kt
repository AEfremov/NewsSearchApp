package com.aefremov.news.data

import com.aefremov.news.data.model.Article
import com.aefremov.news.data.model.Source
import com.aefremov.news.database.models.ArticleDBO
import com.aefremov.news.database.models.Source as SourceDBO
import com.aefremov.newsapi.models.ArticleDTO
import com.aefremov.newsapi.models.SourceDTO

internal fun ArticleDBO.toArticle() : Article {
    return Article(
        cacheId = id,
        source = source.toSource(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
    )
}

internal fun SourceDBO.toSource(): Source {
    return Source(id = id, name = name)
}

internal fun ArticleDTO.toArticle() : Article {
    return Article(
        source = source.toSource(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
    )
}

internal fun SourceDTO.toSource(): Source {
    return Source(id = id ?: name, name = name)
}

internal fun ArticleDTO.toArticleDbo(): ArticleDBO {
    return ArticleDBO(
        source = source.toSourceDbo(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
    )
}

internal fun SourceDTO.toSourceDbo(): SourceDBO {
    return SourceDBO(id = id ?: name, name = name)
}
