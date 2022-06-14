package com.weslleyqi0.guiamg

import android.app.Application
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.weslleyqi0.guiamg.util.COLLECTION_DATA
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Firebase.database.setPersistenceEnabled(true)
        val database =  Firebase.database.getReference(COLLECTION_DATA)
        database.keepSynced(true)
    }
}