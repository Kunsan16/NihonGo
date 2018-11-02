package com.kunsan.nihon.app.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kunsan.nihon.WordRepository
import com.kunsan.nihon.WordViewModel
import java.security.Provider

/**
 * Created by moge on 2018/10/27.
 */
class ViewModelFactory(private val mDataSource: WordRepository) : ViewModelProvider.Factory{



    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return  when {
            modelClass.isAssignableFrom(WordViewModel::class.java) -> WordViewModel(mDataSource) as T
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }

    }


}