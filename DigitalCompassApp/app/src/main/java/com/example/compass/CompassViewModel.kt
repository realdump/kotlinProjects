package com.example.compass

import android.content.Context
import android.hardware.*
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class CompassViewModel(context: Context) : ViewModel(), SensorEventListener {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

    private var gravity: FloatArray? = null
    private var geomagnetic: FloatArray? = null

    private val _azimuth = mutableStateOf(0f)
    val azimuth: State<Float> = _azimuth

    init {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI)
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return

        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> gravity = event.values.clone()
            Sensor.TYPE_MAGNETIC_FIELD -> geomagnetic = event.values.clone()
        }

        val R = FloatArray(9)
        val I = FloatArray(9)

        if (gravity != null && geomagnetic != null &&
            SensorManager.getRotationMatrix(R, I, gravity, geomagnetic)
        ) {
            val orientation = FloatArray(3)
            SensorManager.getOrientation(R, orientation)

            val azimuthInRadians = orientation[0]
            val azimuthInDegrees = (Math.toDegrees(azimuthInRadians.toDouble()) + 360) % 360
            _azimuth.value = azimuthInDegrees.toFloat()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onCleared() {
        super.onCleared()
        sensorManager.unregisterListener(this)
    }
}