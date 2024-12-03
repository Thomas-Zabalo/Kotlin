package com.thomas.applicationandroid


import android.app.Application
import androidx.lifecycle.ViewModel
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltAndroidApp
class MyApplication : Application() {

}
