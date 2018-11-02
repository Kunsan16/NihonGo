package com.kunsan.nihon.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update



/**
 * Created by moge on 2018/11/1.
 */
@Dao
interface WordListDao{

    @Query("SELECT * FROM WordList")
    fun getAll(): List<WordList>

    @Query("SELECT * FROM WordList WHERE collect='1'")
    fun getAllCollect():LiveData<List<WordList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(wordList: WordList)

    @Update
    fun updateWords( users: WordList)
}