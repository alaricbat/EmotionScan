package com.advance.emotionscanapp.uil.di


object DIContainer {

    private val instances = mutableMapOf<String, Any?>()

    fun <T> singleton(clazz: Class<T>, instance: T) {
        instances[clazz.name] = instance
    }

    internal inline fun <reified T> get(): T {

        val className = T::class.java.name

        if (instances.containsKey(className)) {
            return instances[className] as T
        }

        throw IllegalStateException("Dependency ${T::class.java.name} not registered")

    }

    internal inline fun <reified T : Any> inject() = get<T>()

}
