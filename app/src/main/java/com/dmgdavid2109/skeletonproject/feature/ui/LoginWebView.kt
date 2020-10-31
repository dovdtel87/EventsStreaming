package com.dmgdavid2109.skeletonproject.feature.ui

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.webkit.*

class LoginWebView(
    context: Context,
    attributes: AttributeSet
) : WebView(context, attributes) {


    private lateinit var viewModel: MainViewModel
    private val initialUrl = "https://staging.hopin.to/sign_in"

    fun setViewModel(viewModel : MainViewModel) {
        this.viewModel = viewModel
    }

    init {
        setupWebViewSettings()

        webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }

            @Deprecated("Required for API 23 support")
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                return true
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                val token = extractUserToken(CookieManager.getInstance().getCookie(view?.getUrl()))
                if(token.isEmpty().not()) {
                    viewModel.getStreamingUrl(token)
                }
                super.onPageFinished(view, url)
            }
        }
    }

    private val regex = "user.token=([^;]+)".toRegex()
    private fun extractUserToken(cookies : String) : String {
        return regex.find(cookies)?.let {
          val (text) = it.destructured
            text
        } ?: kotlin.run {
            ""
        }
    }

    fun loadLoginPage() {
        loadUrl(initialUrl)
    }

    private fun setupWebViewSettings() {
        val settings = settings
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.javaScriptEnabled = true
    }
}
