package com.delta.batterymonitor.presentation

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class SensorHandler(fileHandler: FileHandler, sensorManager: SensorManager) {
    private var mSensorManager: SensorManager = sensorManager
    private var mFileHandler: FileHandler = fileHandler
    private val mAccelerometerListener: AccelerometerListener = AccelerometerListener(mFileHandler)
    private val mGyroscopeListener: GyroscopeListener = GyroscopeListener(mFileHandler)
    init {
        val samplingRateHertz = 100
        val mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val mGyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val samplingPeriodMicroseconds = 1000000/samplingRateHertz
        mSensorManager.registerListener(mAccelerometerListener, mAccelerometer, samplingPeriodMicroseconds)
        mSensorManager.registerListener(mGyroscopeListener, mGyroscope, samplingPeriodMicroseconds)
    }
    fun unregisterAccelerometer() {
        mSensorManager.unregisterListener(mAccelerometerListener)
    }
}