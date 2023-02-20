package com.example.cricketapp

import android.app.Application
import com.example.cricketapp.data.Repo

class ApplicationClass : Application() {
    lateinit var repo: Repo

    override fun onCreate() {
        super.onCreate()
        initializeRepo()
    }

    private fun initializeRepo() {
//        repo = Repo(applicationContext)
    }
}