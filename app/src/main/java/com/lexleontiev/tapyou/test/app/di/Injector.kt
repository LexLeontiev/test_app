package com.lexleontiev.tapyou.test.app.di

import kotlin.reflect.KClass


// A very simple DI for instances with lifecycle within the application scope
object Injector {

    private val dependencies = HashMap<KClass<*>, Any>()
    private val creators = HashMap<KClass<*>, () -> Any>()

    fun <T: Any> provide(clazz: KClass<T>, creator: () -> T) {
        creators[clazz] = creator
    }

    fun clear(clazz: KClass<*>) {
        dependencies.remove(clazz)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T: Any> inject(clazz: KClass<T>): T {
        val dependency = dependencies[clazz] as? T
        if (dependency != null) return dependency
        val newInstance = creators[clazz]!!.invoke()
        dependencies[clazz] = newInstance
        return newInstance as T
    }
}