package com.upsage.evosummermobilelab.data.entries;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.upsage.evosummermobilelab.data.converters.DateConverter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @TypeConverters({DateConverter.class})
    @ColumnInfo(name = "update_date")
    private Date updateDate;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    public Note(Date updateDate, String description) {
        this.updateDate = updateDate;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @SuppressLint("SimpleDateFormat")
    public String getTime() {
        return new SimpleDateFormat("HH:mm").format(updateDate);
    }

    @SuppressLint("SimpleDateFormat")
    public String getDate() {
        return new SimpleDateFormat("dd.MM.YY").format(updateDate);
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id.equals(note.id);
    }
}
