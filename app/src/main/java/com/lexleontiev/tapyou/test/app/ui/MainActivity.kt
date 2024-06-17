package com.lexleontiev.tapyou.test.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.lexleontiev.tapyou.test.app.R
import com.lexleontiev.tapyou.test.app.databinding.ActivityMainBinding
import com.lexleontiev.tapyou.test.app.di.Injector


class MainActivity : AppCompatActivity() {

    private val router: Router = Injector.inject(Router::class)
    private val navigatorHolder = Injector.inject(NavigatorHolder::class)
    private val navigator = AppNavigator(this, R.id.fragment_container)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            router.newRootScreen(Screens.input())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
