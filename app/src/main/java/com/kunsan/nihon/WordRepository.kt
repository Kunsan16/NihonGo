package com.kunsan.nihon

import android.arch.lifecycle.LiveData
import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.kunsan.nihon.app.base.BaseRepository
import com.kunsan.nihon.bean.WordBean
import com.kunsan.nihon.dao.DBHelper
import com.kunsan.nihon.dao.Word

/**
 * Created by moge on 2018/10/27.
 */
open class WordRepository constructor(context: Context): BaseRepository(){


    private var dbHelper = DBHelper.init(context)


    fun getAllWords(): LiveData<List<Word>> {
        return dbHelper.getWordDao().getAll()
    }

    fun insertWord(word: WordBean){
        LogUtils.i("数据库插入数据 "+word.chinese)
        val word1= Word()
        word1.chinese = word.chinese
        word1.id = word.wordId
        word1.japanese = word.japanese
        dbHelper.getWordDao().insert(word1)
    }
}