package com.kunsan.nihon.dao

import android.arch.persistence.room.Room
import android.content.Context

/**
 * Created by moge on 2018/10/26.
 */
class DBHelper constructor(context: Context){

    var appDb: AppDataBase? = null
    val DB_NAME = "word_db.db"

    init {
        appDb = Room.databaseBuilder(context,
                AppDataBase::class.java,
                DB_NAME)
                .build()
    }


    fun getWordDao():WordDao{
        return appDb!!.wordDao()
    }


    companion object {
        private var INSTANCE :DBHelper? = null

        fun init(context: Context):DBHelper{

          if (INSTANCE == null){
              synchronized(DBHelper::class){

                  if (INSTANCE == null){
                      INSTANCE = DBHelper(context)
                  }
              }
          }
          return INSTANCE!!
        }
    }
}