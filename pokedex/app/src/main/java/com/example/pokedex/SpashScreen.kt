package com.example.pokedex

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokeapiegsys.R
import com.example.pokedex.ui.screen.activity.HomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SpashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_splash)
        supportActionBar?.hide()
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            displayMainScreen()
        }
    }

    private fun displayMainScreen() {
        val intent = Intent(
            this,
            HomeActivity::class.java
        )
        startActivity(intent)
        finish()
    }
}
