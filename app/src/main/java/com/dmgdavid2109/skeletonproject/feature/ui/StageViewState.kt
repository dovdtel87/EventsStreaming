package com.dmgdavid2109.skeletonproject.feature.ui

import com.dmgdavid2109.skeletonproject.common.ui.LceViewState

data class StageViewState(
    override val isLoading: Boolean = false,
    override val errorMessage: Int? = null,
    val streamUrl: String = "",
    val status: String = ""
) : LceViewState
