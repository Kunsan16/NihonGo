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
import com.kunsan.nihon.bean.WordBean
import com.kunsan.nihon.dao.Word
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by moge on 2018/10/27.
 */
class CollectActivity : AppCompatActivity(){



    companion object {
        fun launch(context: Context){
            val intent= Intent(context, CollectActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var viewModel:WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        showCollectList()
    }

    private fun showCollectList() {

        viewModel = getViewModel()



        viewModel.getAllCollectWords().observe(this, Observer<List<Word>> { t ->
            LogUtils.i("单词收藏进列表了")
            val adapter = CollectAdapter(t!!,object : CollectAdapter.OnItemClickListener{
                override fun invoke(wordBean: Word) {


                }


            })
            rv_main.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
            rv_main.adapter = adapter
        })

    }

    fun getViewModel(): WordViewModel {


        return ViewModelProviders.of(this,object : ViewModelProvider.Factory{

            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val respository = WordRepository(this@CollectActivity)
                return WordViewModel(respository) as T
            }
        })[WordViewModel::class.java]
    }
}