package com.example.quotesapp.ui.authors

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.domain.ResultData
import com.example.quotesapp.domain.mapper.AuthorDomainToUIMapper
import com.example.quotesapp.domain.model.AuthorDomain
import com.example.quotesapp.domain.usecase.authors.FetchAuthorsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthorsViewModel(
    private val fetchAuthorsUseCase: FetchAuthorsUseCase,
    private val authorDomainToUIMapper: AuthorDomainToUIMapper<AuthorUIState>,
    private val authorCommunication: AuthorsCommunication,
) : ViewModel() {

    fun fetchAuthors() {
        authorCommunication.map(listOf(AuthorUIState.Progress))
        viewModelScope.launch {
            authorCommunication.map(listOf(AuthorUIState.Progress))
            when (val authorDomain = fetchAuthorsUseCase.fetchAuthors()) {
                is ResultData.Success -> {
                    val authorDomain: Set<AuthorDomain.AuthorDomainModel> =
                        authorDomain.data.toSet()
                    val authorUI: List<AuthorUIState> = authorDomain.map {
                        it.map(authorDomainToUIMapper)
                    }
                    authorCommunication.map(authorUI)
                }
            }
        }
    }

    fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<List<AuthorUIState>>) {
        authorCommunication.observe(lifecycleOwner, observer)
    }

    fun search(author: String) {
        authorCommunication.map(listOf(AuthorUIState.Progress))
        viewModelScope.launch {
            when (val list = fetchAuthorsUseCase.fetchAuthorsByQuery(author)) {
                is ResultData.Success -> {
                    val authorDomain: Set<AuthorDomain.AuthorDomainModel> = list.data.toSet()
                    val authorUI: List<AuthorUIState> = authorDomain.map {
                        it.map(authorDomainToUIMapper)
                    }
                    authorCommunication.map(authorUI)
                }
            }
        }
    }
}