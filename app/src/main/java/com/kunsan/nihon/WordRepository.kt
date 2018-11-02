package com.kunsan.nihon

import android.arch.lifecycle.LiveData
import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.kunsan.nihon.app.base.BaseRepository
import com.kunsan.nihon.bean.WordBean
import com.kunsan.nihon.dao.DBHelper
import com.kunsan.nihon.dao.Word
import com.kunsan.nihon.dao.WordList
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by moge on 2018/10/27.
 */
open class WordRepository : BaseRepository(){


    private var dbHelper = DBHelper.init()


    /**
     * 保存所有单词
     */
    fun saveAllWords(list:List<WordList>){
        Observable.just<List<WordList>>(list)
                .subscribeOn(Schedulers.io())
                .subscribe { s ->
                    for (i in 0 until s.size){
                        s[i].id = i
                        dbHelper.getWordListDao().insertList(s[i])
                    }
                }
    }


    /**
     * 获取数据库单词列表
     */
    fun queryAllWords():List<WordList>{
        return dbHelper.getWordListDao().getAll()
    }


    /**
     * 收藏单词
     */
    fun collectWord():LiveData<List<WordList>>{
        return dbHelper.getWordListDao().getAllCollect()
    }

    fun getAllWords(): LiveData<List<Word>> {
        return dbHelper.getWordDao().getAll()
    }

    fun updateWordList(word: WordList){
        LogUtils.i("更新数据库 "+word.chinese+" "+word.collect)
        dbHelper.getWordListDao().updateWords(word)
    }

    fun insertWord(word: WordList){

        dbHelper.getWordListDao().insertList(word)
    }

    fun deleteWord(word:WordList){
        val word1= Word()
        word1.chinese = word.chinese

        word1.japanese = word.japanese
        dbHelper.getWordDao().delete(word1)
    }
}