package com.delta.batterymonitor.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    private lateinit var mFileHandler: FileHandler
    private lateinit var mBatteryHandler: BatteryHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mFileHandler = FileHandler(getExternalFilesDir(null)!!)
        mBatteryHandler = BatteryHandler(::registerReceiver,::unregisterReceiver, mFileHandler)

        setContent {
            WearApp("Android")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBatteryHandler.unregister()
    }
}


