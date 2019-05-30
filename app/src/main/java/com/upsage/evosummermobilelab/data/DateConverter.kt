package com.upsage.evosummermobilelab.data

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun timestampToDate(time: Long): Date {
        return Date(time)
    }
}
