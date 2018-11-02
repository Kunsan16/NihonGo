package com.kunsan.nihon

import android.arch.lifecycle.LiveData
import com.kunsan.nihon.app.base.BaseRepository
import com.kunsan.nihon.app.base.BaseViewModel
import com.kunsan.nihon.bean.WordBean
import com.kunsan.nihon.dao.Word
import com.kunsan.nihon.dao.WordList

/**
 * Created by moge on 2018/10/27.
 */
class WordViewModel constructor(var wordRepository: WordRepository) : BaseViewModel(wordRepository) {



     fun getAllWords():List<WordList> {
         return wordRepository.queryAllWords()
     }

    fun saveAllWords(wordList: List<WordList>){
         wordRepository.saveAllWords(wordList)
    }

    fun getAllCollectWords(): LiveData<List<WordList>> {
        return wordRepository.collectWord()
    }

    fun collectWord(word: WordList) {
        wordRepository.insertWord(word)
    }

    fun unCollectWord(word: WordList){

        wordRepository.deleteWord(word)

    }


    fun updateWords(wordList: WordList){
        wordRepository.updateWordList(wordList)
    }
}