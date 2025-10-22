package com.example.fitquality.repository

import android.content.Context


/**
 * Provee una única instancia de AuthRepository para toda la app.
 * Debes llamar a RepoHolder.init(context) una vez (por ejemplo en MainActivity.onCreate)
 * antes de usar RepoHolder.repo
 */
object RepoHolder {
    lateinit var repo: AuthRepository
        private set

    fun init(context: Context) {
        if (!this::repo.isInitialized) {
            repo = AuthRepository(context.applicationContext)
        }
    }
}