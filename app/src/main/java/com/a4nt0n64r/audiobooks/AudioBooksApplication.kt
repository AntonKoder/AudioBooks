package com.a4nt0n64r.audiobooks

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class AudioBooksApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val realmConfig = RealmConfiguration.Builder()
            .name("AudioBooks.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .build()

        Realm.setDefaultConfiguration(realmConfig)
    }
}
