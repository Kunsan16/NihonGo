package com.kunsan.nihon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.kunsan.nihon.adapter.WordAdapter
import com.kunsan.nihon.bean.LocalWordBean
import com.kunsan.nihon.bean.WordBean
import com.kunsan.nihon.utils.JsonConvertUtils
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by moge on 2018/10/24.
 */
class MainActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Utils.init(this)

        LogUtils.getConfig().setSingleTagSwitch(true).setBorderSwitch(false)
        initView()
    }

    private fun initView() {


        var bean = JsonConvertUtils.JsonToObject(this,"nihon_language.json",LocalWordBean::class.java)

        val list = bean.data

        LogUtils.i("json数据 "+list)
        val adapter = WordAdapter(list)
        rv_main.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        rv_main.adapter = adapter
    }

}