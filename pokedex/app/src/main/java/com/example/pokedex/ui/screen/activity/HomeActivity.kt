package com.example.pokeapiegsys.ui.screen.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokeapiegsys.R
import com.example.pokeapiegsys.ui.screen.fragments.HomeFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var fragment: HomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragment = HomeFragment()
        fragment.arguments = intent.extras
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_home, fragment)
        transaction.commitNow()
    }
}