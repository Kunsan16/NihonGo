package com.kunsan.nihon.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by moge on 2018/10/26.
 */
@Database(entities = [(Word ::class),(WordList::class)],version = 1)
abstract class AppDataBase : RoomDatabase(){


     abstract fun wordDao():WordDao

     abstract fun wordListDao():WordListDao

}