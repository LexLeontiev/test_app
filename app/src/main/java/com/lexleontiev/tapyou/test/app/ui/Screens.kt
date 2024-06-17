package com.lexleontiev.tapyou.test.app.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen


object Screens {
    fun input() = FragmentScreen { InputFragment.newInstance() }
    fun result(count: Int) = FragmentScreen { ResultFragment.newInstance(count) }
}