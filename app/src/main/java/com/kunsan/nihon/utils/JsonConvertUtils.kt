package com.kunsan.nihon.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import com.google.gson.reflect.TypeToken
import java.io.Reader


/**
 * Created by moge on 2018/10/24.
 */
object JsonConvertUtils{


    /**
     * 得到json文件中的内容
     * @param context
     * @param fileName
     * @return
     */
    private fun getJson(context: Context,fileName : String) : String{

        val stringBuilder = StringBuilder()
        val assetManager = context.assets

        val bufferedReader = BufferedReader(InputStreamReader(assetManager.open(fileName),"utf-8") as Reader?)

        var line:String
        while (true){
            line = bufferedReader.readLine() ?: break
            stringBuilder.append(line)
        }
        return stringBuilder.toString()

    }

    /**
     * 将字符串转换为 对象
     * @param json
     * @param type
     * @return
     */
    fun <T>JsonToObject( context: Context,fileName : String, type:Class<T> ):T {

        return Gson().fromJson(getJson(context,fileName), type)
    }


    /**
     * json 转 List<T>
    </T> */
    fun <T> jsonToList(context: Context,fileName : String): List<T> {

        return Gson().fromJson(getJson(context,fileName), object : TypeToken<List<T>>(){}.type)
    }

}