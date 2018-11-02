package com.kunsan.nihon.dao

import android.arch.persistence.room.Room
import android.content.Context
import com.kunsan.nihon.App

/**
 * Created by moge on 2018/10/26.
 */
class DBHelper{

    var appDb: AppDataBase? = null
    val DB_NAME = "word_db.db"

    init {
        appDb = Room.databaseBuilder(App.instance,
                AppDataBase::class.java,
                DB_NAME).allowMainThreadQueries()
                .build()
    }


    fun getWordDao():WordDao{
        return appDb!!.wordDao()
    }

    fun getWordListDao():WordListDao{
        return appDb!!.wordListDao()
    }

    companion object {
        private var INSTANCE :DBHelper? = null

        fun init():DBHelper{

          if (INSTANCE == null){
              synchronized(DBHelper::class){

                  if (INSTANCE == null){
                      INSTANCE = DBHelper()
                  }
              }
          }
          return INSTANCE!!
        }
    }
}