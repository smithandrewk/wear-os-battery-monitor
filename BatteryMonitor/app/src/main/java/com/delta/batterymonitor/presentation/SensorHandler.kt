package com.delta.batterymonitor.presentation

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class SensorHandler(fileHandler: FileHandler, sensorManager: SensorManager) : SensorEventListener {
    private var mSensorManager: SensorManager = sensorManager
    private var mFileHandler: FileHandler = fileHandler

    init {
        val samplingRateHertz = 100
        val mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val samplingPeriodMicroseconds = 1000000/samplingRateHertz
        mSensorManager.registerListener(this, mAccelerometer, samplingPeriodMicroseconds)
    }

    override fun onSensorChanged(event: SensorEvent) {
        mFileHandler.writeAccelerometerEvent(event)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    fun unregister() {
        mSensorManager.unregisterListener(this)
    }
}