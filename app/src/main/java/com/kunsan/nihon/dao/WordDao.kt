package com.kunsan.nihon.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

/**
 * Created by moge on 2018/10/26.
 */
@Dao
interface WordDao{


    @Query("SELECT * FROM Word")
    fun getAll():LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word)

    @Delete
    fun delete(word:Word)
}