package com.delta.batterymonitor.presentation

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import java.io.File
import java.io.FileOutputStream
import java.util.Date
import java.util.Locale

class FileHandler (filesDir: File) {
    private val appStartTimeReadable = SimpleDateFormat("yyyy-MM-dd_HH_mm_ss", Locale.ENGLISH).format(
        Date()
    )
    private var fBatteryLog: FileOutputStream
    private val mFilesDir = filesDir
    init {
        File(mFilesDir, appStartTimeReadable).mkdir()
        fBatteryLog = FileOutputStream(File(filesDir, "$appStartTimeReadable/battery.csv"))
    }
    fun writeToLog(msg:String){
        fBatteryLog.write("${Calendar.getInstance().timeInMillis},$msg\n".toByteArray())
    }
}