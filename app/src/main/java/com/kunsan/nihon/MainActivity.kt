package com.kunsan.nihon

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.kunsan.nihon.adapter.WordAdapter
import com.kunsan.nihon.app.ui.CollectActivity
import com.kunsan.nihon.bean.LocalWordBean
import com.kunsan.nihon.bean.WordBean
import com.kunsan.nihon.dao.Word
import com.kunsan.nihon.utils.JsonConvertUtils
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by moge on 2018/10/24.
 */
class MainActivity: AppCompatActivity() {


    private lateinit var viewModel:WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Utils.init(this)

        LogUtils.getConfig().setSingleTagSwitch(true).setBorderSwitch(false)
        initView()

        fab.setOnClickListener { CollectActivity.launch(this) }
    }

    private fun initView() {
       viewModel = getViewModel()

       viewModel.getAllCollectWords().observe(this, Observer<List<Word>> { t ->

       })
        val bean = JsonConvertUtils.JsonToObject(this,"nihon_language.json",LocalWordBean::class.java)

        val list = bean.data
        for (i in 0 until list.size){
            list[i].wordId = i
        }

        val adapter = WordAdapter(list,object : WordAdapter.OnItemClickListener{
            override fun invoke(wordBean: WordBean) {

                Observable.just<WordBean>(wordBean)
                        .subscribeOn(Schedulers.io())
                        .subscribe { s ->
                            if (wordBean.checked){
                                viewModel.unCollectWord(s)
                            }else{
                                viewModel.collectWord(s)

                            }
                        }
            }


        })
        rv_main.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        rv_main.adapter = adapter
    }


    fun getViewModel():WordViewModel{


        return ViewModelProviders.of(this,object : ViewModelProvider.Factory{

            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val respository = WordRepository(this@MainActivity)
                return WordViewModel(respository) as T
            }
        })[WordViewModel::class.java]
    }
}