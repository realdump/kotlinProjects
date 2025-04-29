package com.abmn.clockwidgetapp

import android.R
import android.content.Context
import android.text.format.DateFormat
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.*
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.glance.unit.dp
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Box
import androidx.glance.layout.Alignment
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.Text
import androidx.glance.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

class ClockWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: androidx.glance.GlanceId) {
        provideContent {
            val time = DateFormat.format("hh:mm:ss a", Calendar.getInstance()).toString()

            Box(
                modifier = GlanceModifier.fillMaxSize().padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = time,
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = ColorProvider(R.Color.WHITE)
                    )
                )
            }
        }
    }
}

import java.util.*

class ClockWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: androidx.glance.GlanceId) {
        provideContent {
            val time = DateFormat.format("hh:mm:ss a", Calendar.getInstance()).toString()

            Box(
                modifier = GlanceModifier.fillMaxSize().padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = time,
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = ColorProvider(0xFFFFFFFF.toInt())
                    )
                )
            }
        }
    }
}
