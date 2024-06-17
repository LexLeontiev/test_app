package com.lexleontiev.tapyou.test.app

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.lexleontiev.tapyou.test.app.di.Injector
import com.lexleontiev.tapyou.test.app.remote.Api
import com.lexleontiev.tapyou.test.app.remote.PointRepository
import com.lexleontiev.tapyou.test.app.remote.PointRepositoryImpl
import com.lexleontiev.tapyou.test.app.remote.RetrofitBuilder
import retrofit2.Retrofit


class TestApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.run {
            provide(Cicerone::class) { Cicerone.create() }
            provide(Router::class) { inject(Cicerone::class).router as Router }
            provide(NavigatorHolder::class) { inject(Cicerone::class).getNavigatorHolder() }
            provide(Retrofit::class) { RetrofitBuilder.getRetrofit() }
            provide(Api::class) { RetrofitBuilder.getApi(inject(Retrofit::class)) }
            provide(PointRepository::class) { PointRepositoryImpl() }
        }
    }
}