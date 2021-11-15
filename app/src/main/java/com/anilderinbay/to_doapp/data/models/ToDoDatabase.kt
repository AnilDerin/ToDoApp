package com.anilderinbay.to_doapp.data.models

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anilderinbay.to_doapp.data.models.Model.ToDoTask


@Database(entities = [ToDoTask::class], version = 1 , exportSchema = false)
abstract class ToDoDatabase: RoomDatabase() {

    abstract fun toDoDao() : ToDoDao

}