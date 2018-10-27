package com.kunsan.nihon.app.base

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel

/**
 * Created by moge on 2018/10/27.
 */
open class BaseViewModel(var repository : BaseRepository) : ViewModel(){


    override fun onCleared() {
        super.onCleared()

        repository
    }


}