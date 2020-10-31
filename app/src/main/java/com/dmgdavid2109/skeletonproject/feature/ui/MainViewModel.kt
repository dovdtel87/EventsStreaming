package com.dmgdavid2109.skeletonproject.feature.ui

import androidx.lifecycle.LiveData
import com.auth0.android.jwt.JWT
import com.dmgdavid2109.skeletonproject.R
import com.dmgdavid2109.skeletonproject.common.ui.BaseViewModel
import com.dmgdavid2109.skeletonproject.common.ui.LceViewModelInputs
import com.dmgdavid2109.skeletonproject.common.ui.ViewStateLiveData
import com.dmgdavid2109.skeletonproject.feature.domain.usecase.GetSessionTokenUseCase
import com.dmgdavid2109.skeletonproject.feature.domain.usecase.GetStagesUseCase
import com.dmgdavid2109.skeletonproject.feature.domain.usecase.GetStreamingUrlUseCase
import com.uber.autodispose.autoDispose
import io.reactivex.functions.Consumer
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getSessionTokenUseCase: GetSessionTokenUseCase,
    private val getStagesUseCase: GetStagesUseCase,
    private val getStreamingUrlUseCase: GetStreamingUrlUseCase
) : BaseViewModel(), LceViewModelInputs {

    private val _viewState = ViewStateLiveData(StageViewState())
    private lateinit var userToken :String
    val viewState: LiveData<StageViewState>
        get() = _viewState

    fun getStreamingUrl(userToken: String) {
        this.userToken = userToken
         getSessionTokenUseCase
            .invoke(userToken)
             .doOnSubscribe{
                 _viewState.value = _viewState.value?.copy(isLoading = true)
             }
            .map { sessionToken ->
                val eventId = extractEventId(sessionToken)
                getStagesUseCase.invoke(sessionToken, eventId)
                    .map { uuid ->
                        getStreamingUrlUseCase.invoke(sessionToken, eventId, uuid).autoDispose(this).subscribe(
                            Consumer<String>{ url ->
                                _viewState.value = _viewState.value?.copy(isLoading = false, streamUrl = url)
                            }, this)
                    }.autoDispose(this).subscribe(Consumer {}, this)
            }.autoDispose(this)
            .subscribe(Consumer {}, this)
        }

    private fun extractEventId(sessionToken : String) : String {
        val jwt = JWT(sessionToken)
        val allClaims = jwt.claims

        return allClaims["event_id"]?.asString() ?: run { "" }
    }

    override fun retry() {
        getStreamingUrl(userToken)
    }

    override fun accept(t: Throwable?) {
        _viewState.value = _viewState.value?.copy(isLoading = false, errorMessage = R.string.generic_error)
    }
}
