package com.ukeje.testapplication

import android.app.ProgressDialog
import android.net.http.SslError
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_about_alc.*

class AboutAlcActivity : AppCompatActivity() {

    private lateinit var progDailog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_alc)

        progDailog = ProgressDialog.show(this, "Loading","Please wait...", true)
        progDailog.setCancelable(false)



        val webView = findViewById<WebView>(R.id.webview)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "About ALC"

        webView.getSettings().javaScriptEnabled = true
        webView.getSettings().loadWithOverviewMode = true
        webView.getSettings().useWideViewPort = true
        webView.webViewClient = object : WebViewClient(){

            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {

                progDailog.show()
                view?.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progDailog.dismiss()
            }

            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }
        }

        webView.loadUrl("http://andela.com/alc/")

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        when(item.itemId){
            android.R.id.home ->
                finish()
        }
        return true
    }

}
