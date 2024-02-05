//package com.app.cityparm.dialog
//
//import android.app.Dialog
//import android.content.Context
//import android.graphics.Color
//import android.graphics.drawable.ColorDrawable
//import android.os.Build
//import android.view.View
//import android.view.WindowManager
//import android.webkit.CookieManager
//import android.webkit.WebSettings
//import android.webkit.WebView
//import com.dgbcap.moffice.R
//import com.dgbcap.moffice.databinding.ViewModalDialogBinding
//import com.dgbcap.moffice.util.Utils
//import com.dgbcap.moffice.web.BaseWebChromeClient
//import com.dgbcap.moffice.web.BaseWebClient
//import com.dgbcap.moffice.web.HybridController
//import com.dgbcap.moffice.web.JavascriptBridge
//
//class ModalDialog @JvmOverloads constructor(
//    context: Context, url: String,
//    closeButtonListener: (ModalDialog) -> Unit = {},
//    closeTodayButtonListener: (ModalDialog) -> Unit = {}
//) : Dialog(context, R.style.DialogFadeAnim) {
//
//    private var onCloseTodayButtonListener: ((ModalDialog) -> Unit)? = null
//    private var onCloseButtonListener: ((ModalDialog) -> Unit)? = null
//    private var hybridController: HybridController = HybridController()
//
//    private val binding by lazy {
//        ViewModalDialogBinding.inflate(layoutInflater)
//    }
//
//    init {
//        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
//        setContentView(binding.root)
//
//        setCancelable(false)
//
//        onCloseTodayButtonListener = closeTodayButtonListener
//        onCloseButtonListener = closeButtonListener
//
//        setupWebview(binding.webView)
//        binding.webView.loadUrl(url)
//
//
//    }
//
//    private fun setupWebview(webView: WebView) {
//        setupDefaultWebSettings(webView)
//        webView.apply {
//            overScrollMode = View.OVER_SCROLL_NEVER
//            scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
//            isScrollbarFadingEnabled = true
//            isVerticalScrollBarEnabled = true
//            isScrollbarFadingEnabled = true
//
//            webViewClient = BaseWebClient(webView, context)
//            webChromeClient = BaseWebChromeClient()
//            addJavascriptInterface(JavascriptBridge(webView, hybridController), "AppInfoWebInterface")
//            addJavascriptInterface(JavascriptBridge(webView, hybridController), "LoginWebInterface")
//            addJavascriptInterface(JavascriptBridge(webView, hybridController), "CommWebInterface")
//            addJavascriptInterface(JavascriptBridge(webView, hybridController), "CertWebInterface")
//            addJavascriptInterface(JavascriptBridge(webView, hybridController), "SignWebInterface")
//            addJavascriptInterface(JavascriptBridge(webView, hybridController), "FidoWebInterface")
//        }
//    }
//
//    private fun setupDefaultWebSettings(webView: WebView) {
//        webView.settings.apply {
//            userAgentString += " ".plus(String.format(R.string.webview_user_agent.toResString, Utils.getAppVersion(context)))
//            javaScriptEnabled = true
//            cacheMode = WebSettings.LOAD_NO_CACHE
//            builtInZoomControls = false
//            displayZoomControls = false
//            domStorageEnabled = true
//            useWideViewPort = true
//            loadWithOverviewMode = true
//            loadsImagesAutomatically = true
//            allowFileAccess = true
//            javaScriptCanOpenWindowsAutomatically = true
//            allowContentAccess = false
//            domStorageEnabled = true
//            databaseEnabled = true
//            textZoom = 100
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
//            CookieManager.getInstance().setAcceptCookie(true)
//            setSupportZoom(false)
//            setGeolocationEnabled(true)
//            setSupportMultipleWindows(true)
//            setNeedInitialFocus(false)
//        }
//    }
//
//
//    override fun show() {
//        binding.btCloseToday.setOnClickListener {
//            onCloseTodayButtonListener?.let {
//                onCloseTodayButtonListener!!(this)
//            }
//
//            this.dismiss()
//        }
//
//        binding.btClose.setOnClickListener {
//            onCloseButtonListener?.let {
//                onCloseButtonListener!!(this)
//            }
//            this.dismiss()
//        }
//
//        super.show()
//    }
//
//}