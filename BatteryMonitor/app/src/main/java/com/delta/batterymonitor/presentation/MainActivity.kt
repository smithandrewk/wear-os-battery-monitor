package com.delta.batterymonitor.presentation

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    private lateinit var mFileHandler: FileHandler
    private lateinit var mBatteryHandler: BatteryHandler
    private lateinit var mSensorHandler: SensorHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mFileHandler = FileHandler(getExternalFilesDir(null)!!)
        mBatteryHandler = BatteryHandler(::registerReceiver,::unregisterReceiver, mFileHandler)
        mSensorHandler = SensorHandler(mFileHandler,getSystemService(SENSOR_SERVICE) as SensorManager)

        setContent {
            WearApp("Android")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBatteryHandler.unregister()
    }
}


