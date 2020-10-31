package com.dmgdavid2109.skeletonproject.feature.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dmgdavid2109.skeletonproject.R
import com.dmgdavid2109.skeletonproject.common.ui.customviews.LoadingView
import com.dmgdavid2109.skeletonproject.common.ui.viewBinding
import com.dmgdavid2109.skeletonproject.databinding.MainFragmentBinding
import com.dmgdavid2109.skeletonproject.di.ViewModelFactory
import javax.inject.Inject

class MainFragment @Inject constructor(
    private val viewModelFactory: ViewModelFactory<MainViewModel>
) : Fragment(R.layout.main_fragment){

    private val binding by viewBinding(MainFragmentBinding::bind)
    private val mainViewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
        binding.loginWebView.setViewModel(mainViewModel)
        binding.loginWebView.loadLoginPage()
    }

    private fun bindView() {
        mainViewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            if(viewState.isLoading) {
                binding.loadingView.status(LoadingView.VisibilityState.LOADING)
                binding.loginWebView.visibility = View.GONE
            } else {
                binding.loadingView.status(LoadingView.VisibilityState.GONE)
            }

            viewState.streamUrl.let { url ->
                if(url.isEmpty().not()) {
                    reproduceVideo(url)
                }
            }
        })
    }

    private fun reproduceVideo(urlStream: String) {
        binding.loginWebView.visibility = View.GONE
        binding.videoView.visibility = View.VISIBLE
        binding.videoView.setMediaController(MediaController(context))
        binding.videoView.setVideoURI(Uri.parse(urlStream))
        binding.videoView.start()
    }
}
