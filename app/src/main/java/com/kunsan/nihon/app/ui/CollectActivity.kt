package com.kunsan.nihon.app.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.kunsan.nihon.R
import com.kunsan.nihon.WordRepository
import com.kunsan.nihon.WordViewModel
import com.kunsan.nihon.adapter.WordAdapter
import com.kunsan.nihon.app.base.BaseActivity
import com.kunsan.nihon.bean.WordBean
import com.kunsan.nihon.dao.Word
import com.kunsan.nihon.dao.WordList
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by moge on 2018/10/27.
 */
class CollectActivity : BaseActivity<WordViewModel>() {


    override val layoutId: Int
        get() = R.layout.activity_main


    override val createViewModel: WordViewModel
        get() = getViewModel()


    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, CollectActivity::class.java)
            context.startActivity(intent)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        showCollectList()
    }


    private fun showCollectList() {



        val adapter = CollectAdapter(object : CollectAdapter.OnItemClickListener {
            override fun invoke(wordBean: WordList) {

            }

        })
        mViewModel.getAllCollectWords().observe(this, Observer<List<WordList>> { t -> adapter.setData(t!!) })

        rv_main.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_main.adapter = adapter


    }


}