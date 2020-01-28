package net.waloyodev.speedbrowser5g

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import okhttp3.*
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit


class SplashActivity : AppCompatActivity() {

    private lateinit var mInterstitialAd: InterstitialAd
    private var adRespons = true
    private var runable: Runnable? = null
    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val linkClient = OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()
        val request = Request.Builder()
                .url("https://jogjamall.com/ternak2/app2.txt")
                .build()

        val call = linkClient.newCall(request)
        call.enqueue(object : Callback {
            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()
                val restr: String
                restr = if (body!=null) {
                    body.string()
                }else{
                    ""
                }
                runOnUiThread { lanjutkan(restr) }
            }

            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread { lanjutkan("") }
                Log.d("XXXXXXXXXXXX", "Failuer")
            }
        })
    }

    fun lanjutkan(tipeiklanx: String){
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        var lastopen = prefs.getInt("lastopen", -1)
        val editor = prefs.edit()

        if(tipeiklanx.length > 2){
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$tipeiklanx")))
            } catch (anfe: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$tipeiklanx")))
            }
            finish()
        }

        editor.putInt("lastopen", Date().day)
        editor.apply()
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713")

        lastopen = Date().day
        if (lastopen == Date().day){
            Log.d("Ads", "Last open")
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
            return
        }

        Log.d("Ads", "Inters")
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                if(!adRespons)
                    return

                handler.removeCallbacks(runable)
                handler.post(runable)
                Log.i("Ads", "onAdLoaded")
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                if(!adRespons)
                    return

                handler.removeCallbacks(runable)
                handler.post(runable)
                Log.i("Ads", "onAdFailedToLoad")
            }

            override fun onAdOpened() {
                Log.i("Ads", "onAdOpened")
            }

            override fun onAdLeftApplication() {
                Log.i("Ads", "onAdLeftApplication")
            }

            override fun onAdClosed() {
                Log.i("Ads", "onAdClosed")
                val i = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(i)
            }
        }

        runable = Runnable {
            adRespons = false
            if (mInterstitialAd.isLoaded) {
                Log.i("Ads", "onAdSHOOOW")
                mInterstitialAd.show()
            } else {
                Log.d("Ads", "The interstitial wasn't loaded yet.")
                val i = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(i)
            }
        }

        startDelay(4000)
    }

    fun startDelay(time: Long){
        handler.removeCallbacks(runable)
        handler.postDelayed(runable, time)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
