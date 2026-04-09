package com.crispus.wellnessapp

import android.content.Intent
//import android.media.tv.AdRequest
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AdRequest

import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback


class MainActivity : AppCompatActivity() {

//    variable to store ad once it loads
    private  var mInterstitialAd : InterstitialAd?=null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this)
        val adView=findViewById<AdView>(R.id.adView)
        val adRequest= AdRequest.Builder().build()
        adView.loadAd(adRequest)

//        loading interstitial ad
                loadInterstitialAd()



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        healthy recipe intent
//        finding the views from the layout using their id's


//        variable to store butttons

        val recipe = findViewById<Button>(R.id.recipes)

//        set onclick listener

        recipe.setOnClickListener {
//            writing intents
            val recipeIntent = Intent(applicationContext, HealthyRecipes::class.java)

            startActivity(recipeIntent)

//            interstitial ad
            showInterstitialAd()
        }
//
//        meditation intent

        val meditation = findViewById<Button>(R.id.meditation)

        meditation.setOnClickListener {

            val meditationIntent = Intent(applicationContext, Meditation::class.java)

            startActivity(meditationIntent)

            showInterstitialAd()
        }

//        nutrition intent

        val nutrition = findViewById<Button>(R.id.nutrition)


        nutrition.setOnClickListener {

            val nutritionIntent = Intent(applicationContext, NutritionAdvice::class.java)

            startActivity(nutritionIntent)
        }

        val excercise = findViewById<Button>(R.id.excercise)

        excercise.setOnClickListener {
            val excerciseIntent = Intent(applicationContext, ExcerciseActivity::class.java)

            startActivity(excerciseIntent)


        }

        val progress = findViewById<Button>(R.id.progress)

        progress.setOnClickListener {

            val progressIntent = Intent(applicationContext, ProgressActivity::class.java)

            startActivity(progressIntent)
        }

        val goals = findViewById<Button>(R.id.goals)

        goals.setOnClickListener {

            val goalsIntent = Intent(applicationContext, WeeklyGoalsActivity::class.java)

            startActivity(goalsIntent)
        }

       val hydration=findViewById<Button>(R.id.hydration)

        hydration.setOnClickListener {

            val hydrationIntent=Intent(applicationContext, HydrationActivity::class.java)

            startActivity(hydrationIntent)

        }

        val motivation= findViewById<Button>(R.id.motivation)

        motivation.setOnClickListener {

            val motivationIntent = Intent(applicationContext, MotivationActivity::class.java)

            startActivity(motivationIntent)
        }



    }

    fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712", // Test ID
            adRequest,
            object : InterstitialAdLoadCallback() {

                override fun onAdLoaded(ad: InterstitialAd) {
                    mInterstitialAd = ad
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    mInterstitialAd = null
                }
            }
        )
    }
    //Show Interstitial ad
    fun showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        }

    }

}