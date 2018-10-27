package com.kunsan.nihon

import android.arch.lifecycle.LiveData
import com.kunsan.nihon.app.base.BaseRepository
import com.kunsan.nihon.app.base.BaseViewModel
import com.kunsan.nihon.bean.WordBean
import com.kunsan.nihon.dao.Word

/**
 * Created by moge on 2018/10/27.
 */
class WordViewModel constructor( var wordRepository: WordRepository) :BaseViewModel(wordRepository){



        fun getAllCollectWords():LiveData<List<Word>>{
            return wordRepository.getAllWords()
        }

    fun collectWord(word: WordBean){

        wordRepository.insertWord(word)
    }

}