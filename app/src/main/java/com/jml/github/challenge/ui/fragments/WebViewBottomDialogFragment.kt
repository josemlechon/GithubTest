package com.jml.github.challenge.ui.fragments


import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.DialogFragment
import com.jml.github.challenge.R
import com.jml.github.challenge.databinding.DialogWebviewBinding
import com.jml.github.challenge.ui.fragments.utils.viewBinding


class WebViewBottomDialogFragment (private val url : String): DialogFragment(R.layout.dialog_webview) {

    private val binding by viewBinding(DialogWebviewBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) return
        setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val urlWeb = url
        with(binding.webView) {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(urlWeb)
        }
    }

    override fun onStart() {
        super.onStart()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) return
        val dialog = dialog
        if (dialog != null) {
            val window = dialog.window
            if (window != null) {
                val params = window.attributes
                params.width = ViewGroup.LayoutParams.MATCH_PARENT
                params.height = ViewGroup.LayoutParams.MATCH_PARENT
                window.attributes = params
            }
        }
    }
}