package com.kunsan.nihon.dao

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by moge on 2018/10/26.
 */
@Entity
class Word(@PrimaryKey var id:Int) {

    constructor() : this(0)


    @ColumnInfo(name = "japanese")
    var japanese:String? = null


    @ColumnInfo(name = "chinese")
    var chinese:String? = null
}