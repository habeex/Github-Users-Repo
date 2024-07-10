package com.example.githubrepo.util

import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.math.abs

object TimeUtils {
    fun formatTimeAgo(timestamp: String?): String {
        if (timestamp.isNullOrEmpty()) return ""
        val instant = Instant.parse(timestamp)
        val currentDateTime = LocalDateTime.now()

        val dateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime()

        val duration = Duration.between(dateTime, currentDateTime)
        val seconds = abs(duration.seconds)
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val weeks = days / 7
        val months = currentDateTime.month.value - dateTime.month.value + (currentDateTime.year - dateTime.year) * 12
        val years = currentDateTime.year - dateTime.year

        return when {
            years > 0 -> "$years ${if (years == 1) "year" else "years"} ago"
            months > 0 -> "$months ${if (months == 1) "month" else "months"} ago"
            weeks > 0 -> "$weeks ${if (weeks == 1L) "week" else "weeks"} ago"
            days > 0 -> if (days == 1L) "yesterday" else "$days days ago"
            hours > 0 -> "$hours ${if (hours == 1L) "hour" else "hours"} ago"
            minutes > 0 -> "$minutes ${if (minutes == 1L) "minute" else "minutes"} ago"
            else -> "$seconds ${if (seconds == 1L) "second" else "seconds"} ago"
        }
    }
}