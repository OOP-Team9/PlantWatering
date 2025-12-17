package com.example.plantwatering.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.plantwatering.presentation.model.ui.theme.BackGroundGreen
import com.example.plantwatering.presentation.model.ui.theme.PlantWateringTheme
import com.example.plantwatering.presentation.screen.navigation.AppNavigation
import com.google.firebase.BuildConfig
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth


class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("UID!!!", "onCreate start")
        FirebaseApp.initializeApp(this)

        ensureAnonymousAuth()

        enableEdgeToEdge()
        setContent {
            PlantWateringTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackGroundGreen
                ) {
                    AppNavigation()
                }
            }
        }
    }

    /** 100% 지피티 .. **/
    private fun ensureAnonymousAuth() {
        val auth = FirebaseAuth.getInstance()

        Log.e("UID!!!", "currentUser=${auth.currentUser?.uid}")

        val opt = FirebaseApp.getInstance().options
        Log.e("UID!!!", "options.applicationId=${opt.applicationId}")
        Log.e("UID!!!", "options.projectId=${opt.projectId}")
        Log.e("UID!!!", "options.apiKey=${opt.apiKey.take(6)}...")

        auth.currentUser?.uid?.let { uid ->
            Log.e("UID!!!", "already signed in uid=$uid")
            return
        }

        auth.signInAnonymously()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val u = auth.currentUser
                    Log.e("UID!!!", "signIn SUCCESS uid=${u?.uid}, isAnonymous=${u?.isAnonymous}")
                } else {
                    Log.e("UID!!!", "signIn FAIL", task.exception)
                }
            }

    }
}
