package com.upsage.evosummermobilelab.data

import android.annotation.SuppressLint
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*


@Entity
class Note(@field:TypeConverters(DateConverter::class)
           @field:ColumnInfo(name = "update_date")
           var updateDate: Date?, @field:ColumnInfo(name = "description")
           var description: String) : Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    val time: String
        @SuppressLint("SimpleDateFormat")
        get() = SimpleDateFormat("HH:mm").format(updateDate)

    val date: String
        @SuppressLint("SimpleDateFormat")
        get() = SimpleDateFormat("dd.MM.YY").format(updateDate)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val note = other as Note?
        return id == note!!.id
    }
}
